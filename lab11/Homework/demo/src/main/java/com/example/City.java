package com.example;

import jakarta.persistence.*;
@NamedQueries({
    @NamedQuery(name = "City.findByName", query = "SELECT c FROM City c WHERE c.name = :name")
})
@Entity
@Table(name = "cities")

public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_seq")
@SequenceGenerator(name = "city_seq", sequenceName = "cities_seq", allocationSize = 1)
    private Integer id;
    
    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "capital")
    private boolean capital;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

 
    public City() {}

  
    public City(String name, Country country, boolean capital) {
        this.name = name;
        this.country = country;
        this.capital = capital;
    }

    
    public City(String name, Country country, boolean capital, double latitude, double longitude) {
        this.name = name;
        this.country = country;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "City [id=" + id + ", name=" + name + ", country=" + country.getName() + ", capital=" + capital + "]";
    }
}
