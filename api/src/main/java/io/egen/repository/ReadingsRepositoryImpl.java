package io.egen.repository;

import io.egen.entity.Readings;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ReadingsRepositoryImpl implements ReadingsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Readings> findAll() {
        TypedQuery<Readings> query = entityManager.createNamedQuery("Readings.findAll",
                Readings.class);
        return query.getResultList();

    }

    public Readings findOne(String id) {
        return entityManager.find(Readings.class, id);
    }

    public Readings create(Readings readings) {
        entityManager.persist(readings);
        return readings;
    }

    public Readings update(Readings readings) {
        entityManager.merge(readings);
        return readings;
    }

    public void delete(Readings readings) {

        entityManager.remove(readings);

    }


    public Readings findByVin(String vin) {
        TypedQuery<Readings> query = entityManager.createNamedQuery("Readings.findByVin",Readings.class);
        query.setParameter("paramVin",vin);
        List<Readings> resultList = query.getResultList();
        if (resultList != null && resultList.size() == 1) {
            return resultList.get(0);
        } else {
            return null;
        }

    }
}
