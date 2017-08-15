package io.egen.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "Vehicle.findAll",
                query = "SELECT veh FROM Vehicle veh "),
        @NamedQuery(name = "Vehicle.findByVin",
                query = "SELECT veh FROM Vehicle veh WHERE veh.vin=:paramVin")
})
public class Vehicle {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(unique = true)
    private String vin;

    private String make;
    private String model;
    private Integer year;
    private Integer redlineRpm;
    private Integer maxFuelVolume;
    private Date lastServiceDate;


    public Vehicle(){

        this.id = UUID.randomUUID().toString();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRedlineRpm() {
        return redlineRpm;
    }

    public void setRedlineRpm(Integer redlineRpm) {
        this.redlineRpm = redlineRpm;
    }

    public Integer getMaxFuelVolume() {
        return maxFuelVolume;
    }

    public void setMaxFuelVolume(Integer maxFuelVolume) {
        this.maxFuelVolume = maxFuelVolume;
    }

    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", vin='" + vin + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", redlineRpm=" + redlineRpm +
                ", maxFuelVolume=" + maxFuelVolume +
                ", lastServiceDate=" + lastServiceDate +
                '}';
    }
}
