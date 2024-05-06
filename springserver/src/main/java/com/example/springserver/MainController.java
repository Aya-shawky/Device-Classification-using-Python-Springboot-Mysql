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

import com.example.springserver.model.Device;
//import com.example.springserver.DeviceService;



@RestController 
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
  
  
  @GetMapping("/")
  public String hello() {
      return "NEW hello world";
  }

  @Autowired 
  private DeviceRepository deviceRepository;
 
  @Autowired 
  private DeviceService deviceService;

  @GetMapping(path="/api/devices/{id}")
  public @ResponseBody Device getDeviceById(@PathVariable Long id) {
    return deviceRepository.findById(id).orElse(null);
  }

  @PostMapping(path="/api/devices/")
  public @ResponseBody Iterable<Device> getAllDevices() {
    // This returns a JSON or XML with the devices
    return deviceRepository.findAll();
  }

  @PostMapping(path="/api/devices") 
  public  ResponseEntity<Device> create_Device(@RequestBody Device device) { //@ResponseBody
     System.out.println(device);
     Device n = device;
     System.out.println(n.getBattery_power());
     deviceRepository.save(n);
     System.out.println(n.getId());
     return ResponseEntity.status(HttpStatus.CREATED).body(n); //ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(result);
 }

 @PostMapping(path="/api/predict/{deviceId}") 
 public  String predict(@PathVariable Long deviceId) { //@ResponseBody
  Device device = deviceRepository.findById(deviceId).orElse(null);//getDeviceById(deviceId); 
  try {
    // Call the service to predict the price and update the device
    Device pred_device = deviceService.predictPrice(device);
    System.out.println(pred_device);
    return "pred_device"; //ResponseEntity.ok(pred_device);  // Return the updated device with predicted price
  } 
  catch (Exception e) {
  System.err.println(device);
  return "device"; //ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());  // Better error response

  }

  
}


}