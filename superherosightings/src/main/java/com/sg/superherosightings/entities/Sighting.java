/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author roycerabanal
 */
public class Sighting {
    
    int sightingID;
    Location location;
    HeroVillain heroVillain;
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

    public HeroVillain getHeroVillain() {
        return heroVillain;
    }

    public void setHeroVillain(HeroVillain heroVillain) {
        this.heroVillain = heroVillain;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.sightingID;
        hash = 79 * hash + Objects.hashCode(this.location);
        hash = 79 * hash + Objects.hashCode(this.heroVillain);
        hash = 79 * hash + Objects.hashCode(this.date);
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
        if (!Objects.equals(this.heroVillain, other.heroVillain)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }

}
