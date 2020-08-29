package com.kodilla.jpa.domainzad;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Zadanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nazwa;
    private String status;

    @ManyToMany
    @JoinColumn(name = "ludek_id")
    private Set<Ludek> ludeks = new HashSet<Ludek>(); ;

    @ManyToMany(mappedBy = "zadanies")
    private Set<Podzadanie> podzadanies = new HashSet<Podzadanie>();

    public Zadanie() {

    }

    public Zadanie(Long id, String nazwa, String status) {
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

    public Set<Podzadanie> getPodzadanies() {
        return podzadanies;
    }

}
