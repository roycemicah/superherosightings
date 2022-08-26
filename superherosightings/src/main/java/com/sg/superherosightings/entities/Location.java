/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author roycerabanal
 */
public class Location {
    
    int LocationID;
    String name;
    String description;
    Double latitude;
    Double longitude;
    String streetNumber;
    String streetName;
    String city;
    String stateProvince;
    String zipPostalCode;
    String country;
    List<HeroVillain> heroVillainsSighted = new ArrayList<>();

    public int getLocationID() {
        return LocationID;
    }

    public void setLocationID(int LocationID) {
        this.LocationID = LocationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<HeroVillain> getHeroVillainsSighted() {
        return heroVillainsSighted;
    }

    public void setHeroVillainsSighted(List<HeroVillain> heroVillainsSighted) {
        this.heroVillainsSighted = heroVillainsSighted;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.LocationID;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.latitude);
        hash = 97 * hash + Objects.hashCode(this.longitude);
        hash = 97 * hash + Objects.hashCode(this.streetNumber);
        hash = 97 * hash + Objects.hashCode(this.streetName);
        hash = 97 * hash + Objects.hashCode(this.city);
        hash = 97 * hash + Objects.hashCode(this.stateProvince);
        hash = 97 * hash + Objects.hashCode(this.zipPostalCode);
        hash = 97 * hash + Objects.hashCode(this.country);
        hash = 97 * hash + Objects.hashCode(this.heroVillainsSighted);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.LocationID != other.LocationID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.streetNumber, other.streetNumber)) {
            return false;
        }
        if (!Objects.equals(this.streetName, other.streetName)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.stateProvince, other.stateProvince)) {
            return false;
        }
        if (!Objects.equals(this.zipPostalCode, other.zipPostalCode)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return Objects.equals(this.heroVillainsSighted, other.heroVillainsSighted);
    }
    
}
