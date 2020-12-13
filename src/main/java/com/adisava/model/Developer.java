package com.adisava.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class Developer {

    static long counter = 1;

    private long id;

    @NotBlank
    private String name;

    public void persist() {
        this.id = counter++;
    }
}
