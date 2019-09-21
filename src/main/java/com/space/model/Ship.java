package com.space.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "ship")
public class Ship implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String planet;
    @Enumerated(EnumType.STRING)
    private ShipType shipType;
    private Date prodDate;
    private boolean isUsed;
    private double speed;
    private int crewSize;
    private double rating;
//    private final int currentYear = 3019;


    public Ship() {
    }

    public Ship(String name, String planet, ShipType shipType, Date prodDate, boolean isUsed, double speed, int crewSize) {
        this.name = name;
        this.planet = planet;
        this.shipType = shipType;
        this.prodDate = prodDate;
        this.isUsed = isUsed;
        this.speed = speed;
        this.crewSize = crewSize;

        setRating();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    public double getRating() {
        return rating;
    }

    public void setRating() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(prodDate);
        rating = Math.round(((80 * speed * (isUsed ? 0.5 : 1.0)) / (3019 - calendar.get(Calendar.YEAR) + 1)) * 100) / 100.0;
    }
}
