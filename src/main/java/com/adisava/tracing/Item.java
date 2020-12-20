package com.adisava.tracing;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Item extends PanacheEntity {

    @NotNull
    public String itemId;
    public int quantity;
    public float unitPrice;

}

