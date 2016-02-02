package com.pccoe.emergency;

/**
 * Created by Aky Chordiya on 02/02/2016.
 */
public class Emergency {

    private String name;
    private Integer number;

    public Emergency(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
