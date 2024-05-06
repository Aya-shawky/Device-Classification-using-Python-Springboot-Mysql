package com.example.springserver;

import org.springframework.data.repository.CrudRepository;

import com.example.springserver.model.Device;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface DeviceRepository extends CrudRepository<Device, Long> {

}