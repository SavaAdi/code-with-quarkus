package com.adisava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Coder {

    private String name;

    @JsonProperty("favLanguage")
    private String favoriteLanguage;
    private int age;
}
