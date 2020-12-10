package com.adisava.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Developer {

    static long counter = 1;

    private long id;
    private String name;

    public void persist() {
        this.id = counter++;
    }
}
