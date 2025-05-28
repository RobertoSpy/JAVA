package com.example;


public class CityDTO {
    private Integer id;           // nu Long
    private String name;
    private Integer countryId;    // nu Long
    private String countryName;
    private boolean capital;
    private double latitude;
    private double longitude;

    public CityDTO() {}

    public CityDTO(Integer id, String name, Integer countryId, String countryName, boolean capital, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
        this.countryName = countryName;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getteri și setteri (Integer, nu Long)
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getCountryId() { return countryId; }
    public void setCountryId(Integer countryId) { this.countryId = countryId; }

    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }

    public boolean isCapital() { return capital; }
    public void setCapital(boolean capital) { this.capital = capital; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    @Override
public String toString() {
    return "CityDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", countryId=" + countryId +
            ", countryName='" + countryName + '\'' +
            ", capital=" + capital +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
}

}
