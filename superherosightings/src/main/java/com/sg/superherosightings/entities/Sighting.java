/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.entities;

import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 *
 * @author roycerabanal
 */
public class Sighting {

    int sightingID;

    @NotNull(message = "Location must not be empty.")
    Location location;

    @NotNull(message = "Hero (or Villain) must not be empty.")
    HeroVillain heroVillain;

    @Past(message = "Date must be in the past.")
    @NotNull(message = "Date must not be empty.")
    LocalDate date;

    public int getSightingID() {
        return sightingID;
    }

    public void setSightingID(int sightingID) {
        this.sightingID = sightingID;
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
        hash = 43 * hash + this.sightingID;
        hash = 43 * hash + Objects.hashCode(this.location);
        hash = 43 * hash + Objects.hashCode(this.heroVillain);
        hash = 43 * hash + Objects.hashCode(this.date);
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
