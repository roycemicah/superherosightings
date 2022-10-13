/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.HeroVillainDao;
import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperpowerDao;
import com.sg.superherosightings.entities.HeroVillain;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superpower;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author roycerabanal
 */
@Service
public class SuperheroSightingsServiceLayerImpl implements SuperheroSightingsServiceLayer {
    
    @Autowired
    HeroVillainDao heroVillainDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Override
    public Superpower getSuperpowerByID(int superpowerID) {
        return superpowerDao.getSuperpowerByID(superpowerID);
    }
    
    @Override
    public List<Superpower> getAllSuperpowers() {
        return superpowerDao.getAllSuperpowers();
    }
    
    @Override
    public Superpower addSuperpower(Superpower superpower) {
        return superpowerDao.addSuperpower(superpower);
    }
    
    @Override
    public void updateSuperpower(Superpower superpower) {
        superpowerDao.updateSuperpower(superpower);
    }
    
    @Override
    public void deleteSuperpowerByID(int superpowerID) {
        superpowerDao.deleteSuperpowerByID(superpowerID);
    }
    
    @Override
    public HeroVillain getHeroVillainByID(int heroVillainID) {
        return heroVillainDao.getHeroVillainByID(heroVillainID);
    }
    
    @Override
    public List<HeroVillain> getAllHeroVillains() {
        return heroVillainDao.getAllHeroVillains();
    }
    
    @Override
    public HeroVillain addHeroVillain(HeroVillain heroVillain) {
        return heroVillainDao.addHeroVillain(heroVillain);
    }
    
    @Override
    public void updateHeroVillain(HeroVillain heroVillain) {
        heroVillainDao.updateHeroVillain(heroVillain);
    }
    
    @Override
    public void deleteHeroVillainByID(int heroVillainID) {
        heroVillainDao.deleteHeroVillainByID(heroVillainID);
    }

    @Override
    public Organization getOrganizationByID(int organizationID) {
        return organizationDao.getOrganizationByID(organizationID);
    }
    
    @Override
    public List<Organization> getAllOrganizations() {
        return organizationDao.getAllOrganizations();
    }
    
    @Override
    public Organization addOrganization(Organization organization) {
        return organizationDao.addOrganization(organization);
    }
    
    @Override
    public void updateOrganization(Organization organization) {
        organizationDao.updateOrganization(organization);
    }
    
    @Override
    public void deleteOrganizationByID(int organizationID) {
        organizationDao.deleteOrganizationByID(organizationID);
    }
    
    @Override
    public Sighting getSightingByID(int sightingID) {
        return sightingDao.getSightingByID(sightingID);
    }
    
    @Override
    public List<Sighting> getAllSightings() {
        return sightingDao.getAllSightings();
    }
    
    @Override
    public Sighting addSighting(Sighting sighting) {
        return sightingDao.addSighting(sighting);
    }
    
    @Override
    public void updateSighting(Sighting sighting) {
        sightingDao.updateSighting(sighting);
    }
    
    @Override
    public void deleteSightingByID(int sightingID) {
        sightingDao.deleteSightingByID(sightingID);
    }
    
    @Override
    public Location getLocationByID(int locationID) {
        return locationDao.getLocationByID(locationID);
    }
    
    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }
    
    @Override
    public Location addLocation(Location location) {
        location.setImage(getLocationImageFromGoogle(location));
        return locationDao.addLocation(location);
    }

    // retrieving location image from (Google) Static Maps API
    private byte[] getLocationImageFromGoogle(Location location) {
        final String API_KEY = "";
        InputStream image;
        URL url;
        //taking the coordinates from location and doing the API call to Google
        byte[] imageBytes = null;
        
        try {
            url = new URL("https://maps.googleapis.com/maps/api/staticmap?center=" + location.getLatitude() + "%2C%20" + location.getLongitude() + "&zoom=15&size=1200x600&key=" + API_KEY + "&markers=color:red%7Clabel:" + location.getName().substring(0, 1) + "%7C" + location.getLatitude() + "%2C%20" + location.getLongitude());
            image = url.openStream();
            imageBytes = image.readAllBytes();
        } catch (IOException ex) {
            
        }
        return imageBytes;
    }
    
    @Override
    public void updateLocation(Location location) {
        location.setImage(getLocationImageFromGoogle(location));
        locationDao.updateLocation(location);
    }
    
    @Override
    public void deleteLocationByID(int locationID) {
        locationDao.deleteLocationByID(locationID);
    }

    @Override
    public List<HeroVillain> getHeroVillainsBySuperpowerID(int superpowerID) {
        return heroVillainDao.getHeroVillainsBySuperpowerID(superpowerID);
    }
    
}
