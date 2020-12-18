package com.adisava.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;


@Entity
public class Inventory extends PanacheEntity {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_book_id", nullable = false)
    public Book book;

    @Column(nullable = false)
    public Status status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_library_id", nullable = false)
    public Library library;
}

