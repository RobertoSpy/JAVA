package com.example;

import jakarta.persistence.*;

@Entity
@Table(name = "cities")
@NamedQueries({
    @NamedQuery(name = "City.findByName", query = "SELECT c FROM City c WHERE LOWER(c.name) LIKE LOWER(:name)")
})
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cities_seq")
    @SequenceGenerator(name = "cities_seq", sequenceName = "cities_seq", allocationSize = 1)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country_id", nullable = false)
    private int countryId;

    @Column(name = "capital")
    @Convert(converter = CapitalConverter.class) 
    private boolean capital;

    private double latitude;
    private double longitude;

    

    public City() {}

    public City(int id, String name, int countryId, boolean capital, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public City(String name, int countryId, boolean capital, double latitude, double longitude) {
        this.name = name;
        this.countryId = countryId;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Gettere și settere

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
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
        return name + " (" + (capital ? "Capital" : "City") + "), Latitude: " + latitude + ", Longitude: " + longitude;
    }
}
