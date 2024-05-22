package com.example.springserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.springserver.model.Device;

import java.util.Map;

@RestController 
public class MainController {

  @Autowired 
  private DeviceRepository deviceRepository;
  private final RestTemplate restTemplate = new RestTemplate();
  
  @GetMapping("/")
  public String hello() {
      return "Welcome to Device Classification Project";
  }

  @GetMapping(path="/api/devices/{id}")
  public @ResponseBody Device getDeviceById(@PathVariable Long id) {
    return deviceRepository.findById(id).orElse(null);
  }

  @PostMapping(path="/api/devices/")
  public @ResponseBody Iterable<Device> getAllDevices() {
    return deviceRepository.findAll();
  }

  @PostMapping(path="/api/devices") 
  public  ResponseEntity<Device> create_Device(@RequestBody Device device) { 
     Device new_device = device;
     deviceRepository.save(new_device);
     return ResponseEntity.status(HttpStatus.CREATED).body(new_device);
 }

 @PostMapping(path="/api/predict/{deviceId}") 
 public String predict(@PathVariable Long deviceId) { //@ResponseBody
  Device device = deviceRepository.findById(deviceId).orElse(null);//getDeviceById(deviceId); 

  try {
    // Call the service to predict the price and update the device
    if (device != null) {

      // Send specifications to the Flask microservice for prediction
      ResponseEntity<Map> response = restTemplate.postForEntity(
          "http://localhost:5000/predict",  // Base URL of the Flask microservice
          device,
          Map.class
          //Map.of("features", device),  // Request data
          //Map.class
      );

      if (response.getStatusCode().is2xxSuccessful()) {
          int predictedPrice = (int) response.getBody().get("price_range");
          device.setprice_range(predictedPrice);

          deviceRepository.save(device);
          return String.format("The pred price for %s ID device is %s", deviceId, predictedPrice ); //ResponseEntity.ok(pred_device); 
    }  
    else {
      return "prediction_failed";}
}
else {
  return String.format("This DeviceID %s May not in your database" , deviceId);}

}

  catch (Exception e) {
  return "INTERNAL_SERVER_ERROR"; 
  } 
  
}


}
