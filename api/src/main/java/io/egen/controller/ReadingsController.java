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

@CrossOrigin(origins = {"http://mocker.egen.io","http://localhost:4200","http://localhost:8080"}, maxAge = 3600)

@RestController
@RequestMapping(value = "/readings")
public class ReadingsController {

    @Autowired
    ReadingsService service;

    @Autowired
    VehicleService vehicleService;

    RulesEngine rulesEngine = RulesEngineBuilder.aNewRulesEngine().build();

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Readings> findAll(){
        return service.findAll();
    }


//    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Readings findOne(@PathVariable("id") String readId){
//        return service.findOne(readId);
//    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Readings create(@RequestBody Readings readings) throws Exception {

        Vehicle vehicle = vehicleService.findByVin(readings.getVin());

        CarTrackerHighPriorityRule carTrackerHighPriorityRule = new CarTrackerHighPriorityRule();
        carTrackerHighPriorityRule.setInput(readings.getEngineRpm(),vehicle.getRedlineRpm());
        if(carTrackerHighPriorityRule.checkInput()){
            readings.setPriority("HIGH");
            readings.setAlertMsg(carTrackerHighPriorityRule.messageToPrint());
        }



        CarTrackerMediumPriorityRule carTrackerMediumPriorityRule = new CarTrackerMediumPriorityRule();
        carTrackerMediumPriorityRule.setInput(vehicle.getMaxFuelVolume(),readings.getFuelVolume());
        if(carTrackerMediumPriorityRule.checkInput()){
            readings.setPriority("MEDIUM");
            readings.setAlertMsg(carTrackerMediumPriorityRule.messageToPrint());
        }


        CarTrackerLowPriorityRule carTrackerLowPriorityRule = new CarTrackerLowPriorityRule();
        carTrackerLowPriorityRule.setInput(readings.getTires(),readings.getEngineCoolantLow(),readings.getCheckEngineLightOn());

        if(carTrackerLowPriorityRule.checkInput()){
            readings.setPriority("LOW");
            readings.setAlertMsg(carTrackerLowPriorityRule.messageToPrint());
        }


        rulesEngine.registerRule(carTrackerMediumPriorityRule);
        rulesEngine.registerRule(carTrackerHighPriorityRule);
        rulesEngine.registerRule(carTrackerLowPriorityRule);
        rulesEngine.fireRules();

        return service.create(readings);

    }


    @RequestMapping(method = RequestMethod.PUT)
    public Readings update(@RequestBody Readings readings){
        return service.update(readings);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void delete(@PathVariable("id") String readId){
        service.delete(readId);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Readings> findAllByVin(@PathVariable("vin") String vin){
        System.out.println(service.findAllByVin(vin));
        return service.findAllByVin(vin);
    }


}
