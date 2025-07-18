package com.example;
import java.util.Objects;
import java.util.*;

public class Location implements Comparable<Location> {
    private String name;
    private LocationType type;

    public Location(String name, LocationType type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public LocationType getType(){
        return type;
    }
//aici fac override pe sortare dupa nume
    @Override
    public int compareTo(Location other){
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Location{name='" + name + "', type=" + type + "}";
    }

}
