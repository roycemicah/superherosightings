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
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author roycerabanal
 */
@SpringBootTest
public class SightingDaoDBTest {
    
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
    
    public SightingDaoDBTest() {
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
    public void testAddGetSightingByID() {
        Superpower superpower = new Superpower();
        superpower.setName("Superspeed");
        superpower.setDescription("Telepathic speed");
        superpower = superpowerDao.addSuperpower(superpower);
        
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Superman");
        heroVillain.setIsHero(true);
        heroVillain.setDescription("A superhero with superhuman abilities");
        heroVillain.setSuperpower(superpower);
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
        
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setHeroVillain(heroVillain);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);
        
        Sighting sightingFromDao = sightingDao.getSightingByID(sighting.getSightingID());
        
        assertEquals(sighting, sightingFromDao);
        
    }

    @Test
    public void testGetAllSightings() {
        Superpower superpower = new Superpower();
        superpower.setName("Superspeed");
        superpower.setDescription("Telepathic speed");
        superpower = superpowerDao.addSuperpower(superpower);
        
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Superman");
        heroVillain.setIsHero(true);
        heroVillain.setDescription("A superhero with superhuman abilities");
        heroVillain.setSuperpower(superpower);
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
        
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setHeroVillain(heroVillain);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);
        
        ///////
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Superstrength");
        superpower2.setDescription("Superhuman strength");
        superpower2 = superpowerDao.addSuperpower(superpower2);

        HeroVillain heroVillain2 = new HeroVillain();
        heroVillain2.setName("Hulk");
        heroVillain2.setIsHero(true);
        heroVillain2.setDescription("Super strong giant green man");
        heroVillain2.setSuperpower(superpower2);
        heroVillain2 = heroVillainDao.addHeroVillain(heroVillain2);
        
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
        
        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.now());
        sighting2.setHeroVillain(heroVillain2);
        sighting2.setLocation(location2);
        sighting2 = sightingDao.addSighting(sighting2);
        
        List<Sighting> sightings = sightingDao.getAllSightings();
        
        assertEquals(sightings.size(), 2);
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
        
    }

    @Test
    public void testUpdateSighting() {
        Superpower superpower = new Superpower();
        superpower.setName("Superspeed");
        superpower.setDescription("Telepathic speed");
        superpower = superpowerDao.addSuperpower(superpower);
        
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Superman");
        heroVillain.setIsHero(true);
        heroVillain.setDescription("A superhero with superhuman abilities");
        heroVillain.setSuperpower(superpower);
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

        ////////
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Superstrength");
        superpower2.setDescription("Superhuman strength");
        superpower2 = superpowerDao.addSuperpower(superpower2);

        HeroVillain heroVillain2 = new HeroVillain();
        heroVillain2.setName("Hulk");
        heroVillain2.setIsHero(true);
        heroVillain2.setDescription("Super strong giant green man");
        heroVillain2.setSuperpower(superpower2);
        heroVillain2 = heroVillainDao.addHeroVillain(heroVillain2);
        
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
        
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setHeroVillain(heroVillain);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);
        
        Sighting sightingFromDao = sightingDao.getSightingByID(sighting.getSightingID());
        assertEquals(sighting, sightingFromDao);
                
        sighting.setHeroVillain(heroVillain2);
        sighting.setLocation(location2);
        sighting.setDate(LocalDate.now().minusDays(5));
        
        sightingDao.updateSighting(sighting);
        assertNotEquals(sighting, sightingFromDao);
        
        sightingFromDao = sightingDao.getSightingByID(sighting.getSightingID());
        
        assertEquals(sighting, sightingFromDao);
        
    }

    @Test
    public void testDeleteSightingByID() {
        Superpower superpower = new Superpower();
        superpower.setName("Superspeed");
        superpower.setDescription("Telepathic speed");
        superpower = superpowerDao.addSuperpower(superpower);
        
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Superman");
        heroVillain.setIsHero(true);
        heroVillain.setDescription("A superhero with superhuman abilities");
        heroVillain.setSuperpower(superpower);
        heroVillain = heroVillainDao.addHeroVillain(heroVillain);

        ///////
        
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
        
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setHeroVillain(heroVillain);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);
        
        ////////////
        Superpower superpower2 = new Superpower();
        superpower2.setName("Invisibility");
        superpower2.setDescription("Disappear into thin air");
        superpower2 = superpowerDao.addSuperpower(superpower2);
        
        HeroVillain heroVillain2 = new HeroVillain();
        heroVillain2.setName("Mr. Untraceable");
        heroVillain2.setIsHero(false);
        heroVillain2.setDescription("Refuses to fight in person");
        heroVillain2.setSuperpower(superpower2);
        heroVillain2 = heroVillainDao.addHeroVillain(heroVillain2);
        
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
        
        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.now());
        sighting2.setHeroVillain(heroVillain2);
        sighting2.setLocation(location2);
        sighting2 = sightingDao.addSighting(sighting2);
        List<Sighting> allSightings = sightingDao.getAllSightings();
        
        assertEquals(allSightings.size(), 2);
        assertTrue(allSightings.contains(sighting));
        assertTrue(allSightings.contains(sighting2));
        
        sightingDao.deleteSightingByID(sighting.getSightingID());
        
        Sighting sightingFromDao = sightingDao.getSightingByID(sighting.getSightingID());
        assertNull(sightingFromDao);
        
        allSightings = sightingDao.getAllSightings();
        assertEquals(allSightings.size(), 1);
        assertTrue(allSightings.contains(sighting2));
    }
    
    @Test
    public void getSightingsOrderedByDate() {
        Superpower superpower = new Superpower();
        superpower.setName("Superspeed");
        superpower.setDescription("Telepathic speed");
        superpower = superpowerDao.addSuperpower(superpower);
        
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Superman");
        heroVillain.setIsHero(true);
        heroVillain.setDescription("A superhero with superhuman abilities");
        heroVillain.setSuperpower(superpower);
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
        
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now().minusDays(5));
        sighting.setHeroVillain(heroVillain);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);
        
        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.now());
        sighting2.setHeroVillain(heroVillain);
        sighting2.setLocation(location);
        sighting2 = sightingDao.addSighting(sighting2);
        
        List<Sighting> sightings = sightingDao.getSightingsOrderedByDate();
        assertEquals(sightings.size(), 2);
        assertTrue(sightings.get(0).equals(sighting2));
        assertTrue(sightings.get(1).equals(sighting));
    }

}
