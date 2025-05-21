package com.example;

public class CountryDTO {
    private Integer id;
    private String name;
    private String code;
    private String continentName;

    public CountryDTO(Integer id, String name, String code, String continentName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.continentName = continentName;
    }

    // Gettere și settere
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getContinentName() { return continentName; }
    public void setContinentName(String continentName) { this.continentName = continentName; }
}
