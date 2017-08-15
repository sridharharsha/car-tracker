package io.egen.service;

import io.egen.entity.Vehicle;
import io.egen.exception.ResourceNotFoundException;
import io.egen.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicalServiceImpl implements VehicleService{

    @Autowired
    VehicleRepository repository;

    @Transactional(readOnly = true)
    public List<Vehicle> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Vehicle findOne(String id) {
        Vehicle existing = repository.findOne(id);
        if(existing == null){
            throw new ResourceNotFoundException("Vehicle with id " + id + " doesn't exist.");
        }
        return existing;
    }

    @Transactional
    public void create(List<Vehicle> vehicles) {
        for(Vehicle vehicle : vehicles)
             repository.create(vehicle);
    }

    @Transactional
    public Vehicle update(Vehicle vehicle) {
        Vehicle existing = repository.findOne(vehicle.getId());
        if(existing == null){
            throw new ResourceNotFoundException("Vehicle with id " + vehicle.getId() + " doesn't exist.");
        }
        return repository.update(vehicle);
    }

    @Transactional
    public void delete(String vehId) {
        Vehicle existing = repository.findOne(vehId);
        if(existing == null){
            throw new ResourceNotFoundException("Vehicle with id " + vehId + " doesn't exist.");
        }
        repository.delete(existing);

    }

    @Transactional
    public Vehicle findByVin(String vin) {
        Vehicle existing = repository.findByVin(vin);
        if(existing == null){
            throw new ResourceNotFoundException("Vehicle with vin number " + vin + " doesn't exist.");
        }
        return existing;
    }
}
