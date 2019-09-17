package com.space.model;

import javax.persistence.*;
import java.io.Serializable;
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

    private void setRating() {
        rating = Math.round(((80 * speed * (isUsed ? 0.5 : 1.0)) / (3019 - prodDate.getYear() + 1)) * 100) / 100.0;
    }
}
