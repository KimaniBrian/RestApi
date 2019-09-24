package com.galvanive.guitar.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Guitar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long guitarid;
     private String brand;
     private String model;
     private int strings;

    public Guitar(String brand, String model, int strings) {
        this.guitarid = guitarid;
        this.brand = brand;
        this.model = model;
        this.strings = strings;
    }

    public Guitar() {
    }

    public long getGuitarid() {
        return guitarid;
    }

    public void setGuitarid(long guitarid) {
        this.guitarid = guitarid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getStrings() {
        return strings;
    }

    public void setStrings(int strings) {
        this.strings = strings;
    }
}
