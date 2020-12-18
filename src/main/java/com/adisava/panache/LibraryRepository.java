package com.adisava.panache;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;

@ApplicationScoped
public class LibraryRepository implements PanacheRepository<Library> {
    public Library findByName(String name) {
        return find("SELECT l FROM Library l " +
                        "left join fetch l.inventory where l.name = :name ",
                Parameters.with("name", name)).firstResult();
    }

    @Override
    public PanacheQuery<Library> findAll() {
        return find("from Library l left join fetch l.inventory");
    }

    @Override
    public PanacheQuery<Library> findAll(Sort sort) {
        return find("from Library l left join fetch l.inventory",
                sort, Collections.emptyMap());
    }
}

