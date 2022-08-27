/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author roycerabanal
 */
public class Sighting {
    
    int sightingID;
    Location location;
    List<HeroVillain> isHeroVillainInSight = new ArrayList<>();
    LocalDate date;

    public int getSightingID() {
        return sightingID;
    }

    public void setSightingID(int SightingID) {
        this.sightingID = SightingID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<HeroVillain> getIsHeroVillainInSight() {
        return isHeroVillainInSight;
    }

    public void setIsHeroVillainInSight(List<HeroVillain> isHeroVillainInSight) {
        this.isHeroVillainInSight = isHeroVillainInSight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.sightingID;
        hash = 29 * hash + Objects.hashCode(this.location);
        hash = 29 * hash + Objects.hashCode(this.isHeroVillainInSight);
        hash = 29 * hash + Objects.hashCode(this.date);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingID != other.sightingID) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.isHeroVillainInSight, other.isHeroVillainInSight)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }

}
