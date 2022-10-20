/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.entities.HeroVillain;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superpower;
import java.util.List;

/**
 *
 * @author roycerabanal
 */
public interface SuperheroSightingsServiceLayer {
    
    public Superpower getSuperpowerByID(int superpowerID);
    public List<Superpower> getAllSuperpowers();
    public Superpower addSuperpower(Superpower superpower);
    public void updateSuperpower(Superpower superpower);
    public void deleteSuperpowerByID(int superpowerID);
    
    public HeroVillain getHeroVillainByID(int heroVillainID);
    public List<HeroVillain> getAllHeroVillains();
    public HeroVillain addHeroVillain(HeroVillain heroVillain);
    public void updateHeroVillain(HeroVillain heroVillain);
    public void deleteHeroVillainByID(int heroVillainID);
    public List<HeroVillain> getHeroVillainsBySuperpowerID(int superpowerID);
    
    public Organization getOrganizationByID(int organizationID);
    public List<Organization> getAllOrganizations();
    public Organization addOrganization(Organization organization);
    public void updateOrganization(Organization organization);
    public void deleteOrganizationByID(int organizationID);
    
    public Sighting getSightingByID(int sightingID);
    public List<Sighting> getAllSightings();
    public Sighting addSighting(Sighting sighting);
    public void updateSighting(Sighting sighting);
    public void deleteSightingByID(int sightingID);
    public List<Sighting> get10MostRecentSightings();
    
    public Location getLocationByID(int locationID);
    public List<Location> getAllLocations();
    public Location addLocation(Location location);
    public void updateLocation(Location location);
    public void deleteLocationByID(int locationID);
}
