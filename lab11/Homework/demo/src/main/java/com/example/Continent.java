package com.example;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "continents")
@NamedQueries({
    @NamedQuery(name = "Continent.findAll", query = "SELECT c FROM Continent c ORDER BY c.name")
})
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "continent_seq")
    @SequenceGenerator(name = "continent_seq", sequenceName = "continents_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "continent", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Country> countries;

    public Continent() {}

    public Continent(String name) {
        this.name = name;
    }

    // Gettere și settere

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "Continent [id=" + id + ", name=" + name + "]";
    }
}
