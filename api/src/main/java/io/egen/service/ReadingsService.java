package io.egen.service;

import io.egen.entity.Readings;

import java.util.List;

public interface ReadingsService {
    List<Readings> findAll();

    Readings findOne(String id);

    Readings create(Readings readings);

    Readings update(Readings readings);

    void delete(String id);

    Readings findByVin(String vin);

}
