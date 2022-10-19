/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author roycerabanal
 */
public class Location {
    
    int locationID;
    
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 30, message = "Name must be less than 30 characters.")
    String name;
    
    @NotBlank(message = "Description must not be empty.")
    @Size(max = 100, message = "Description must be less than 100 characters.")
    String description;
    
    @NotNull(message = "Latitude must not be empty.")
    @Min(value = -90, message = "Latitude must be greater than or equal to -90.")
    @Max(value = 90, message = "Latitude must be less than or equal to 90.")
    Double latitude;
    
    @NotNull(message = "Longitude must not be empty.")
    @Min(value = -180, message = "Longitude must be greater than or equal to -180.")
    @Max(value = 180, message = "Longitude must be less than or equal to 180.")
    Double longitude;
    
    Address address;
    List<HeroVillain> heroVillainsSighted = new ArrayList<>();
    byte[] image;

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int LocationID) {
        this.locationID = LocationID;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<HeroVillain> getHeroVillainsSighted() {
        return heroVillainsSighted;
    }

    public void setHeroVillainsSighted(List<HeroVillain> heroVillainsSighted) {
        this.heroVillainsSighted = heroVillainsSighted;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.locationID;
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.description);
        hash = 43 * hash + Objects.hashCode(this.latitude);
        hash = 43 * hash + Objects.hashCode(this.longitude);
        hash = 43 * hash + Objects.hashCode(this.address);
        hash = 43 * hash + Objects.hashCode(this.heroVillainsSighted);
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
        if (this.locationID != other.locationID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return Objects.equals(this.heroVillainsSighted, other.heroVillainsSighted);
    }
    
}
