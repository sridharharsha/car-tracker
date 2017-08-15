package io.egen.repository;

import io.egen.entity.Readings;

import java.util.List;

public interface ReadingsRepository {

    List<Readings> findAll();

    Readings findOne(String id);

    Readings create(Readings readings);

    Readings update(Readings readings);

    void delete(Readings readings);

    Readings findByVin(String vin);

}
