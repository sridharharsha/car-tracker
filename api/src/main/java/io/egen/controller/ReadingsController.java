package io.egen.controller;


import io.egen.entity.Readings;
import io.egen.entity.Vehicle;
import io.egen.rules.CarTrackerHighPriorityRule;
import io.egen.rules.CarTrackerLowPriorityRule;
import io.egen.rules.CarTrackerMediumPriorityRule;
import io.egen.service.ReadingsService;
import io.egen.service.VehicleService;
import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://mocker.egen.io", maxAge = 3600)
@RestController
@RequestMapping(value = "/readings")
public class ReadingsController {

    @Autowired
    ReadingsService service;

    @Autowired
    VehicleService vehicleService;

    Set<String> dbSavedReadingsSet = new HashSet<>();

    RulesEngine rulesEngine = RulesEngineBuilder.aNewRulesEngine().build();



    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Readings> findAll(){
        return service.findAll();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Readings findOne(@PathVariable("id") String readId){
        return service.findOne(readId);
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Readings create(@RequestBody Readings readings){

        Vehicle vehicle = vehicleService.findByVin(readings.getVin());

        CarTrackerHighPriorityRule carTrackerHighPriorityRule = new CarTrackerHighPriorityRule();
        carTrackerHighPriorityRule.setInput(readings.getEngineRpm(),vehicle.getRedlineRpm());

        CarTrackerMediumPriorityRule carTrackerMediumPriorityRule = new CarTrackerMediumPriorityRule();
        carTrackerMediumPriorityRule.setInput(vehicle.getMaxFuelVolume(),readings.getFuelVolume());

        CarTrackerLowPriorityRule carTrackerLowPriorityRule = new CarTrackerLowPriorityRule();
        carTrackerLowPriorityRule.setInput(readings.getTires(),readings.getEngineCoolantLow(),readings.getCheckEngineLightOn());

        rulesEngine.registerRule(carTrackerMediumPriorityRule);
        rulesEngine.registerRule(carTrackerHighPriorityRule);
        rulesEngine.registerRule(carTrackerLowPriorityRule);
        rulesEngine.fireRules();


        if(dbSavedReadingsSet.size() > 0 && dbSavedReadingsSet.contains(readings.getVin())){
            Readings existing = service.findByVin(readings.getVin());
            update(existing);
        }else{
            dbSavedReadingsSet.add(readings.getVin());
            return service.create(readings);
        }

        return null;

    }


    @RequestMapping(method = RequestMethod.PUT)
    public Readings update(@RequestBody Readings readings){
        return service.update(readings);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void delete(@PathVariable("id") String readId){
        service.delete(readId);

    }

}
