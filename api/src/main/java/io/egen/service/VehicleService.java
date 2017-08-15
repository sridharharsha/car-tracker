package io.egen.service;

import io.egen.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> findAll();

    Vehicle findOne(String id);

    void create(List<Vehicle> vehicles);

    Vehicle update(Vehicle vehicle);

    void delete(String vehId);

    Vehicle findByVin(String vin);


}
