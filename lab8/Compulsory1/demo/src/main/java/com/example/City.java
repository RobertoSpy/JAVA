package com.example;

public class City {
    private int id;
    private int countryId;
    private String name;
    private boolean capital;
    private double latitude;
    private double longitude;

    public City(int id, int countryId, String name, boolean capital, double latitude, double longitude) {
        this.id = id;
        this.countryId = countryId;
        this.name = name;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public City(int countryId, String name, boolean capital, double latitude, double longitude) {
        this.countryId = countryId;
        this.name = name;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getName() {
        return name;
    }

    public boolean isCapital() {
        return capital;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
