package edu.itstep.taxi.entity;

import edu.itstep.taxi.entity.parent.BaseEntity;
import edu.itstep.taxi.entity.parent.LogEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data
@Entity
@Table
public class Car extends LogEntity implements Serializable {

    @Column(length = 100)
    private String brand;

    @Column(length = 100)
    private String model;

    @Column
    private short year;

    @Column
    private String number;

    @Column(length = 50)
    private String equipment;

    @Column(name = "consumption")
    private byte fuelConsumption;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "car_driver",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id"),
            foreignKey = @ForeignKey(name = "car_driver_ibfk_1"))
    private Set<Driver> drivers;

    @Column
    private int cost;
}
