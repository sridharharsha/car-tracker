package io.egen.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "Readings.findAll",
                query = "SELECT read FROM Readings read WHERE read.priority = 'HIGH'"),
        @NamedQuery(name = "Readings.findAllByVin",
                query = "SELECT read FROM Readings read WHERE read.vin=:paramVin AND read.priority is not null")
})

public class Readings {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;


    private String vin;

    private Double latitude;
    private Double longitude;
    private Date timestamp;
    private Integer fuelVolume;
    private Integer speed;
    private Integer engineHp;
    private Boolean checkEngineLightOn;
    private Boolean engineCoolantLow;
    private Boolean cruiseControlOn;
    private Integer engineRpm;
    private String priority;
    private String alertMsg;

    @OneToOne(cascade = CascadeType.ALL)
    private Tires tires;

    public Readings(){
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(Integer fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getEngineHp() {
        return engineHp;
    }

    public void setEngineHp(Integer engineHp) {
        this.engineHp = engineHp;
    }

    public Boolean getCheckEngineLightOn() {
        return checkEngineLightOn;
    }

    public void setCheckEngineLightOn(Boolean checkEngineLightOn) {
        this.checkEngineLightOn = checkEngineLightOn;
    }

    public Boolean getEngineCoolantLow() {
        return engineCoolantLow;
    }

    public void setEngineCoolantLow(Boolean engineCoolantLow) {
        this.engineCoolantLow = engineCoolantLow;
    }

    public Boolean getCruiseControlOn() {
        return cruiseControlOn;
    }

    public void setCruiseControlOn(Boolean cruiseControlOn) {
        this.cruiseControlOn = cruiseControlOn;
    }

    public Integer getEngineRpm() {
        return engineRpm;
    }

    public void setEngineRpm(Integer engineRpm) {
        this.engineRpm = engineRpm;
    }

    public Tires getTires() {
        return tires;
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAlertMsg() {
        return alertMsg;
    }

    public void setAlertMsg(String alertMsg) {
        this.alertMsg = alertMsg;
    }

    @Override
    public String toString() {
        return "Readings{" +
                "id='" + id + '\'' +
                ", vin='" + vin + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                ", fuelVolume=" + fuelVolume +
                ", speed=" + speed +
                ", engineHp=" + engineHp +
                ", checkEngineLightOn=" + checkEngineLightOn +
                ", engineCoolantLow=" + engineCoolantLow +
                ", cruiseControlOn=" + cruiseControlOn +
                ", engineRpm=" + engineRpm +
                ", tires=" + tires +
                ", priority=" + priority +
                '}';
    }
}
