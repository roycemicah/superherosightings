/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Address;
import com.sg.superherosightings.entities.HeroVillain;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superpower;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author roycerabanal
 */
@SpringBootTest
public class LocationDaoDBTest {

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

    public LocationDaoDBTest() {
    }

    @BeforeEach
    public void setUp() {
        List<HeroVillain> heroVillains = heroVillainDao.getAllHeroVillains();
        for (HeroVillain heroVillain : heroVillains) {
            heroVillainDao.deleteHeroVillainByID(heroVillain.getHeroVillainID());
        }

        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for (Superpower superpower : superpowers) {
            superpowerDao.deleteSuperpowerByID(superpower.getSuperpowerID());
        }

        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocationByID(location.getLocationID());
        }

        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting sighting : sightings) {
            sightingDao.deleteSightingByID(sighting.getSightingID());
        }

        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization organization : organizations) {
            organizationDao.deleteOrganizationByID(organization.getOrganizationID());
        }
    }

    @Test
    public void testAddGetLocationByID() {
        Superpower superpower = new Superpower();
        superpower.setName("Superspeed");
        superpower.setDescription("Telepathic speed");
        superpower = superpowerDao.addSuperpower(superpower);
        
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Superman");
        heroVillain.setIsHero(true);
        heroVillain.setDescription("A superhero with superhuman abilities");
        heroVillain.setSuperpower(superpower);
//        heroVillain.setOrganizations(organizations);
        heroVillain = heroVillainDao.addHeroVillain(heroVillain);
        
        Address locationAddress = new Address();
        locationAddress.setStreetNumber("1411");
        locationAddress.setStreetName("Lawrence West");
        locationAddress.setCity("Toronto");
        locationAddress.setStateProvince("Ontario");
        locationAddress.setZipPostalCode("M6L1A4");
        locationAddress.setCountry("Canada");
        
        Location location = new Location();
        location.setName("Metro");
        location.setDescription("The grocery store");
        location.setLatitude(43.70830846581134);
        location.setLongitude(-79.47650406649478);
        location.setAddress(locationAddress);
        location = locationDao.addLocation(location);
        
        List<HeroVillain> heroesSighted = new ArrayList<>();
        heroesSighted.add(heroVillain);
        location.setHeroVillainsSighted(heroesSighted);
        
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setHeroVillain(heroVillain);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);

        Location locationFromDao = locationDao.getLocationByID(location.getLocationID());

        assertEquals(location, locationFromDao);
    }

    @Test
    public void testGetAllLocations() {
        Address locationAddress = new Address();
        locationAddress.setStreetNumber("1411");
        locationAddress.setStreetName("Lawrence West");
        locationAddress.setCity("Toronto");
        locationAddress.setStateProvince("Ontario");
        locationAddress.setZipPostalCode("M6L1A4");
        locationAddress.setCountry("Canada");

        Location location = new Location();
        location.setName("Metro");
        location.setDescription("The grocery store");
        location.setLatitude(43.70830846581134);
        location.setLongitude(-79.47650406649478);
        location.setAddress(locationAddress);
        location = locationDao.addLocation(location);

        ///////
        Address locationAddress2 = new Address();
        locationAddress2.setStreetNumber("55");
        locationAddress2.setStreetName("Denison Road");
        locationAddress2.setCity("Toronto");
        locationAddress2.setStateProvince("Ontario");
        locationAddress2.setZipPostalCode("M9N1B7");
        locationAddress2.setCountry("Canada");

        Location location2 = new Location();
        location2.setName("Freshco");
        location2.setDescription("The other grocery store");
        location2.setLatitude(43.69825889640108);
        location2.setLongitude(-79.50510408545564);
        location2.setAddress(locationAddress2);
        location2 = locationDao.addLocation(location2);

        List<Location> locations = locationDao.getAllLocations();

        assertEquals(locations.size(), 2);
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));
    }

    @Test
    public void testUpdateLocation() {
        Address locationAddress = new Address();
        locationAddress.setStreetNumber("1411");
        locationAddress.setStreetName("Lawrence West");
        locationAddress.setCity("Toronto");
        locationAddress.setStateProvince("Ontario");
        locationAddress.setZipPostalCode("M6L1A4");
        locationAddress.setCountry("Canada");

        Location location = new Location();
        location.setName("Metro");
        location.setDescription("The grocery store");
        location.setLatitude(43.70830846581134);
        location.setLongitude(-79.47650406649478);
        location.setAddress(locationAddress);
        location = locationDao.addLocation(location);

        Location locationFromDao = locationDao.getLocationByID(location.getLocationID());
        assertEquals(location, locationFromDao);
        
        /////
        
        Address locationAddress2 = new Address();
        locationAddress2.setStreetNumber("55");
        locationAddress2.setStreetName("Denison Road");
        locationAddress2.setCity("Toronto");
        locationAddress2.setStateProvince("Ontario");
        locationAddress2.setZipPostalCode("M9N1B7");
        locationAddress2.setCountry("Canada");

        location.setName("Freshco");
        location.setDescription("The other grocery store");
        location.setLatitude(43.69825889640108);
        location.setLongitude(-79.50510408545564);
        location.setAddress(locationAddress2);
        
        locationDao.updateLocation(location);
        assertNotEquals(location, locationFromDao);

        locationFromDao = locationDao.getLocationByID(location.getLocationID());

        assertEquals(location, locationFromDao);

    }

    @Test
    public void testDeleteLocationByID() {
        Address locationAddress = new Address();
        locationAddress.setStreetNumber("1411");
        locationAddress.setStreetName("Lawrence West");
        locationAddress.setCity("Toronto");
        locationAddress.setStateProvince("Ontario");
        locationAddress.setZipPostalCode("M6L1A4");
        locationAddress.setCountry("Canada");

        Location location = new Location();
        location.setName("Metro");
        location.setDescription("The grocery store");
        location.setLatitude(43.70830846581134);
        location.setLongitude(-79.47650406649478);
        location.setAddress(locationAddress);
        location = locationDao.addLocation(location);
        
        ////
        
        Address locationAddress2 = new Address();
        locationAddress2.setStreetNumber("55");
        locationAddress2.setStreetName("Denison Road");
        locationAddress2.setCity("Toronto");
        locationAddress2.setStateProvince("Ontario");
        locationAddress2.setZipPostalCode("M9N1B7");
        locationAddress2.setCountry("Canada");
        
        Location location2 = new Location();
        location2.setName("Freshco");
        location2.setDescription("The other grocery store");
        location2.setLatitude(43.69825889640108);
        location2.setLongitude(-79.50510408545564);
        location2.setAddress(locationAddress2);
        location2 = locationDao.addLocation(location2);
        List<Location> allLocations = locationDao.getAllLocations();
        
        assertEquals(allLocations.size(), 2);
        assertTrue(allLocations.contains(location));
        assertTrue(allLocations.contains(location2));

        locationDao.deleteLocationByID(location.getLocationID());
        
        Location locationFromDao = locationDao.getLocationByID(location.getLocationID());
        assertNull(locationFromDao);
        
        allLocations = locationDao.getAllLocations();
        assertEquals(allLocations.size(), 1);
        assertTrue(allLocations.contains(location2));
    }

}