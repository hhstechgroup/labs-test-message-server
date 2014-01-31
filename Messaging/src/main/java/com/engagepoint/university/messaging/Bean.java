package com.engagepoint.university.messaging;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class Bean implements Serializable {

    //private String colors;

    //private String manufacturers;

    private List<Car> carsSmall;

    public Bean() {
        carsSmall = new ArrayList<Car>();

        populateRandomCars(carsSmall, 9);
    }

    private void populateRandomCars(List<Car> list, int size) {
        for(int i = 0 ; i < size ; i++)
            list.add(new Car(getRandomId(), getRandomTo(), getRandomFrom(), getRandomManufacturer(), getRandomText()));
    }

    public List<Car> getCarsSmall() {
        return carsSmall;
    }

    private int getRandomId() {
        return (int) (Math.random() * 100);
    }

    private String getRandomTo() {
        return ""+Math.random()+"qqq1";
    }

    private String getRandomFrom() {
        return ""+Math.random()+"qqq2";
    }

    private String getRandomManufacturer() {
        return ""+Math.random()+"qqq3";
    }

    private String getRandomText() {
        return ""+Math.random()+"qqq4";
    }
}