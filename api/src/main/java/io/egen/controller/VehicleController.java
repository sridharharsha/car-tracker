package io.egen.controller;

import io.egen.entity.Vehicle;
import io.egen.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://mocker.egen.io", maxAge = 3600)
@RestController
@RequestMapping(value = "/vehicles")
public class VehicleController {

    @Autowired
    VehicleService service;

    HashMap<String,Vehicle> dbSavedVehiclesMap = new HashMap<String, Vehicle>();

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> findAll(){
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle findOne(@PathVariable("id") String vehId){
        return service.findOne(vehId);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void create(@RequestBody List<Vehicle> vehicles){

        List<Vehicle> removeList = new ArrayList<>();

        if(dbSavedVehiclesMap.size() > 0){

            vehicles.forEach(vehicle -> {
                if (dbSavedVehiclesMap.containsKey(vehicle.getVin())) {

                    update(dbSavedVehiclesMap.get(vehicle.getVin()));
                    removeList.add(vehicle);

                }

            });

        }

        if(removeList.size() > 0){
            vehicles.removeAll(removeList);

        }
        service.create(vehicles);

        List<Vehicle> savedVehiclesList = service.findAll();
        savedVehiclesList.forEach(vehicle -> dbSavedVehiclesMap.put(vehicle.getVin(),vehicle));


    }

    @RequestMapping(method = RequestMethod.PUT)
    public Vehicle update(@RequestBody Vehicle vehicle){

        return service.update(vehicle);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void delete(@PathVariable("id") String vehId){
        service.delete(vehId);

    }



}
