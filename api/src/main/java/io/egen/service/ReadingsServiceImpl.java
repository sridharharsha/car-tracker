package io.egen.service;

import io.egen.entity.Readings;
import io.egen.exception.ResourceNotFoundException;
import io.egen.repository.ReadingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReadingsServiceImpl implements ReadingsService{

    @Autowired
    ReadingsRepository repository;

    @Transactional(readOnly = true)
    public List<Readings> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Readings findOne(String id) {
        Readings existing = repository.findOne(id);
        if(existing == null){
            throw new ResourceNotFoundException("Readings with id " + id + " doesn't exist.");
        }
        return existing;
    }

    @Transactional
    public Readings create(Readings readings) {
        return repository.create(readings);
    }

    @Transactional
    public Readings update(Readings readings) {
        Readings existing = repository.findOne(readings.getId());
        if(existing == null){
            throw new ResourceNotFoundException("Readings with id " + readings.getId() + " doesn't exist.");
        }
        return repository.update(readings);
    }

    @Transactional
    public void delete(String id) {
        Readings existing = repository.findOne(id);
        if(existing == null){
            throw new ResourceNotFoundException("Readings with id " + id + " doesn't exist.");
        }
        repository.delete(existing);


    }

    @Transactional
    public Readings findByVin(String vin) {
        Readings existing = repository.findByVin(vin);
        if(existing == null){
            throw new ResourceNotFoundException("Readings with vin number " + vin + " doesn't exist.");
        }
        return existing;

    }
}
