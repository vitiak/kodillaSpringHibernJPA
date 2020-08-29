package com.kodilla.jpa.domainzad;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaZadaniesTestSuite {

    @PersistenceUnit
    private EntityManagerFactory emf;




    @Test
    void shouldNPlusOneProblemOccure() {
        //Given
        List<Long> savedZadanies = insertExampleData();
        EntityManager em = emf.createEntityManager();

        //When
        System.out.println("****************** BEGIN OF FETCHING *******************");
        System.out.println("*** STEP 1 – query for zadanies ***");

        TypedQuery<Zadanie> query = em.createQuery(
                "from Zadanie "
                        + " where id in (" + zadanieIds(savedZadanies) + ")",
                Zadanie.class);

        EntityGraph<Zadanie> eg = em.createEntityGraph(Zadanie.class);
        eg.addSubgraph("podzadanies");
        eg.addSubgraph("ludeks");
        query.setHint("javax.persistence.fetchgraph", eg);

        List<Zadanie> zadanies =
                query.getResultList();

        for (Zadanie zadanie : zadanies) {
            System.out.println("*** STEP 2 – read data from zadanie ***");
            System.out.println(zadanie);
            System.out.println("*** STEP 3 – read the ludek data ***");
            System.out.println(zadanie.getLudeks());

            for (Podzadanie podzadanie : zadanie.getPodzadanies()) {
                System.out.println("*** STEP 4 – read the podzadanie ***");
                System.out.println(podzadanie);
            }

        }

        System.out.println("****************** END OF FETCHING *******************");

        //Then
        //Here should be some assertions and the clean up performed

    }

    private String zadanieIds(List<Long> zadanieIds) {
        return zadanieIds.stream()
                .map(n -> "" + n)
                .collect(Collectors.joining(","));
    }

    private List<Long> insertExampleData() {
        Ludek c1 = new Ludek(null, "Name1", "Surname1");
        Ludek c2 = new Ludek(null, "Name2", "Surname2");
        Ludek c3 = new Ludek(null, "Name3", "Surname3");
        Zadanie z1 = new Zadanie(null, "Zadanie1", "Status1");
        Zadanie z2 = new Zadanie(null, "Zadanie2", "Status2");
        Podzadanie pz1 = new Podzadanie(null, "Podzadanie1", "Status3");
        Podzadanie pz2 = new Podzadanie(null, "Podzadanie2", "Status1");
        Podzadanie pz3 = new Podzadanie(null, "Podzadanie3", "Status4");
        z1.getPodzadanies().addAll(List.of(pz1, pz2));
        z2.getPodzadanies().add(pz3);
        pz1.getZadanies().addAll(List.of(z1, z2));
        z1.getLudeks().addAll(List.of(c1, c2, c3));
        z2.getLudeks().add(c3);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(pz1);
        em.persist(pz2);
        em.persist(pz3);
        em.persist(z1);
        em.persist(z2);
        em.flush();
        em.getTransaction().commit();
        em.close();

        return List.of(z1.getId(), z2.getId());
    }


}