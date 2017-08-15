package io.egen.repository;

import io.egen.entity.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public List<Vehicle> findAll() {
        TypedQuery<Vehicle> query = entityManager.createNamedQuery("Vehicle.findAll",
                Vehicle.class);
        return query.getResultList();
    }

    public Vehicle findOne(String id) {
        return entityManager.find(Vehicle.class, id);
    }

    public Vehicle create(Vehicle vehicle) {
        entityManager.persist(vehicle);
        return vehicle;
    }

    public Vehicle update(Vehicle vehicle) {
        entityManager.merge(vehicle);
        return vehicle;
    }

    public void delete(Vehicle vehicle) {
        entityManager.remove(vehicle);

    }

    public Vehicle findByVin(String vin) {
        TypedQuery<Vehicle> query = entityManager.createNamedQuery("Vehicle.findByVin", Vehicle.class);
        query.setParameter("paramVin",vin);
        List<Vehicle> resultList = query.getResultList();
        if (resultList != null && resultList.size() == 1) {
            return resultList.get(0);
        } else {
            return null;
        }

    }
}
