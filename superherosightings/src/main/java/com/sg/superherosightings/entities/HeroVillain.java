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
public class HeroVillain {
    
    int heroVillainID;
    String name;
    boolean IsHero;
    String description;
    Superpower superpower;
    List<Location> locations = new ArrayList<>();
    List<Organization> organizations = new ArrayList<>();

    public int getHeroVillainID() {
        return heroVillainID;
    }

    public void setHeroVillainID(int HeroVillainID) {
        this.heroVillainID = HeroVillainID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsHero() {
        return IsHero;
    }

    public void setIsHero(boolean IsHero) {
        this.IsHero = IsHero;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Superpower getSuperpower() {
        return superpower;
    }

    public void setSuperpower(Superpower superpower) {
        this.superpower = superpower;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.heroVillainID;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + (this.IsHero ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + Objects.hashCode(this.superpower);
        hash = 79 * hash + Objects.hashCode(this.locations);
        hash = 79 * hash + Objects.hashCode(this.organizations);
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
        final HeroVillain other = (HeroVillain) obj;
        if (this.heroVillainID != other.heroVillainID) {
            return false;
        }
        if (this.IsHero != other.IsHero) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.superpower, other.superpower)) {
            return false;
        }
        if (!Objects.equals(this.locations, other.locations)) {
            return false;
        }
        return Objects.equals(this.organizations, other.organizations);
    }
   
}
