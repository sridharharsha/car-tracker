package io.egen.repository;

import io.egen.entity.Vehicle;

import java.util.List;

public interface VehicleRepository {

    List<Vehicle> findAll();

    Vehicle findOne(String id);

    Vehicle create(Vehicle vehicle);

    Vehicle update(Vehicle vehicle);

    void delete(Vehicle vehicle);

    Vehicle findByVin(String vin);

}
