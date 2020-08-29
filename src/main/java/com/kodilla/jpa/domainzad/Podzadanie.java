package com.kodilla.jpa.domainzad;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Podzadanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;
    private String status;

    @ManyToMany
    @JoinColumn(name = "ludek_id")
    private Set<Ludek> ludeks = new HashSet<Ludek>(); ;

    @ManyToMany
    @JoinColumn(name = "zadanie_id")
    private Set<Zadanie> zadanies = new HashSet<Zadanie>(); ;

    public Podzadanie() {

    }

    public Podzadanie(Long id, String nazwa, String status) {
        this.id = id;
        this.nazwa = nazwa;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getStatus() {
        return status;
    }

    public Set<Ludek> getLudeks() {
        return ludeks;
    }

    public Set<Zadanie> getZadanies() {
        return zadanies;
    }

}
