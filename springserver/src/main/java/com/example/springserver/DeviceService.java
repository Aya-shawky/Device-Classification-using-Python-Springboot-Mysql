
package com.example.springserver;

import org.apache.el.stream.Optional;
import org.hibernate.mapping.Map;
import org.springframework.http.ResponseEntity;

import com.example.springserver.model.Device;

//import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;  


@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;  // Repository for CRUD operations

    //@Autowired
    private RestTemplate restTemplate;  // For calling external REST services

  //  ObjectMapper objectMapper = new ObjectMapper();

    //@Transactional  // Apply transaction management


    public Device predictPrice(Device device) {
        if (device != null) {

            String flaskUrl = "http://127.0.0.1:5000/predict";  
            
             // Create request body
        Device requestBody = device;
        // Send POST request to the Flask microservice
        ResponseEntity<Map> response = restTemplate.postForEntity(
            flaskUrl,
            requestBody,
            Map.class
        );
            
            // Send the Device object as JSON
            //System.out.println("NOT NULL");
            //ResponseEntity<Map> response = restTemplate.postForEntity(flaskUrl, device, Map.class);
            //System.out.println("response DONE");
            //System.out.println(response.getBody());
            // Call the Python API to predict the price
         //   ResponseEntity<Map> response = restTemplate.postForEntity(
         //       "http://localhost:5000/predict",  // URL of the Python API
         //       Map.of("features", device),  // Request body
         //       Map.class
         //   );

            if (response.getStatusCode().is2xxSuccessful()) {
               final int price_range = response.getBody();  // Extract the predicted price .get("price_range")
                device.setprice_range(price_range);  // Update the device
                deviceRepository.save(device);  // Save the updated device
                return device;  // Return the updated device with predicted price
            } else {
                throw new RuntimeException("Prediction service failed");  // Handle errors
            }
        } else {
            throw new RuntimeException("Device not found");  // Handle missing device
        }
    }
}