package com.kodilla.jpa.domainzad;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ludek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @ManyToMany(mappedBy = "ludeks")
    private Set<Podzadanie> podzadanies = new HashSet<Podzadanie>();

    @ManyToMany(mappedBy = "ludeks")
    private Set<Zadanie> zadanies = new HashSet<Zadanie>();

    public Ludek() {

    }

    public Ludek(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Set<Podzadanie> getPodzadanies() {
        return podzadanies;
    }

    public Set<Zadanie> getZadanies() {
        return zadanies;
    }

}
