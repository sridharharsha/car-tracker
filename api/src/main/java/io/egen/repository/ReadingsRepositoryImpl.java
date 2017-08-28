package io.egen.repository;

import io.egen.entity.Readings;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class ReadingsRepositoryImpl implements ReadingsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Readings> findAll() {
        List<Readings> resultList = new ArrayList<>();
        TypedQuery<Readings> query = entityManager.createNamedQuery("Readings.findAll",
                Readings.class);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, -2);
        Date twoHourBack = cal.getTime();

        for(Readings readings : query.getResultList()){
            Date readingsDate = readings.getTimestamp();
            if(readingsDate.after(twoHourBack) && readingsDate.before(new Date())){
                resultList.add(readings);
            }
        }



        return resultList;

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


    public List<Readings> findAllByVin(String vin) {
        TypedQuery<Readings> query = entityManager.createNamedQuery("Readings.findAllByVin",Readings.class);
        query.setParameter("paramVin",vin);
        List<Readings> resultList = query.getResultList();
        if (resultList != null && resultList.size() != 0) {
            return resultList;
        } else {
            return null;
        }

    }
}
