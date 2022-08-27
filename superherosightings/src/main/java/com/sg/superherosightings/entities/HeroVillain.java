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
    List<Superpower> superpowers = new ArrayList<>();
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

    public List<Superpower> getSuperpowers() {
        return superpowers;
    }

    public void setSuperpowers(List<Superpower> superpowers) {
        this.superpowers = superpowers;
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
        hash = 97 * hash + this.heroVillainID;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + (this.IsHero ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.superpowers);
        hash = 97 * hash + Objects.hashCode(this.locations);
        hash = 97 * hash + Objects.hashCode(this.organizations);
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
        if (!Objects.equals(this.superpowers, other.superpowers)) {
            return false;
        }
        if (!Objects.equals(this.locations, other.locations)) {
            return false;
        }
        return Objects.equals(this.organizations, other.organizations);
    }
            
}
