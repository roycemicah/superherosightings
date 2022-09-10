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
public class HeroVillainDaoDBTest {

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

    public HeroVillainDaoDBTest() {
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
    public void testAddGetHeroVillainByID() {
        Superpower superpower = new Superpower();
        superpower.setName("Superspeed");
        superpower.setDescription("Telepathic speed");
        superpower = superpowerDao.addSuperpower(superpower);

        Address organizationAddress = new Address();
        organizationAddress.setStreetNumber("18");
        organizationAddress.setStreetName("California Avenue");
        organizationAddress.setCity("Los Angeles");
        organizationAddress.setStateProvince("California");
        organizationAddress.setZipPostalCode("57555");
        organizationAddress.setCountry("USA");

        Organization organization = new Organization();
        organization.setName("Crime Fighters");
        organization.setAddress(organizationAddress);
        organization.setDescription("An organization that helps to fight crime");
        organization.setPhone("18008008080");
        organization.setEmail("crimefighters@crimefighters.org");
        organization = organizationDao.addOrganization(organization);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        
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
        
        Address locationAddress2 = new Address();
        locationAddress2.setStreetNumber("299");
        locationAddress2.setStreetName("Bloor West");
        locationAddress2.setCity("Toronto");
        locationAddress2.setStateProvince("Ontario");
        locationAddress2.setZipPostalCode("M5S1W2");
        locationAddress2.setCountry("Canada");
        
        Location location2 = new Location();
        location2.setName("Varsity Center");
        location2.setDescription("UofT Sports court");
        location2.setLatitude(43.66725466105625);
        location2.setLongitude(-79.39725987338848);
        location2.setAddress(locationAddress2);
        location2 = locationDao.addLocation(location2);

        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Superman");
        heroVillain.setIsHero(true);
        heroVillain.setDescription("A superhero with superhuman abilities");
        heroVillain.setSuperpower(superpower);
        heroVillain.setOrganizations(organizations);
        heroVillain = heroVillainDao.addHeroVillain(heroVillain);
        
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setHeroVillain(heroVillain);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);
        
        List<Location> locations = new ArrayList<>();
        locations.add(location);
        heroVillain.setLocations(locations);

        HeroVillain heroVillainFromDao = heroVillainDao.getHeroVillainByID(heroVillain.getHeroVillainID());

        assertEquals(heroVillain, heroVillainFromDao);
    }

    @Test
    public void testGetAllHeroVillains() {
        Superpower superpower = new Superpower();
        superpower.setName("Superspeed");
        superpower.setDescription("Telepathic speed");
        superpower = superpowerDao.addSuperpower(superpower);

        Address organizationAddress = new Address();
        organizationAddress.setStreetNumber("18");
        organizationAddress.setStreetName("California Avenue");
        organizationAddress.setCity("Los Angeles");
        organizationAddress.setStateProvince("California");
        organizationAddress.setZipPostalCode("57555");
        organizationAddress.setCountry("USA");

        Organization organization = new Organization();
        organization.setName("Crime Fighters");
        organization.setAddress(organizationAddress);
        organization.setDescription("An organization that helps to fight crime");
        organization.setPhone("18008008080");
        organization.setEmail("crimefighters@crimefighters.org");
        organization = organizationDao.addOrganization(organization);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Superman");
        heroVillain.setIsHero(true);
        heroVillain.setDescription("Superhuman abilities");
        heroVillain.setSuperpower(superpower);
        heroVillain.setOrganizations(organizations);
        heroVillain = heroVillainDao.addHeroVillain(heroVillain);
        
        /////
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Invisibility");
        superpower2.setDescription("Disappear into thin air");
        superpower2 = superpowerDao.addSuperpower(superpower2);

        Address organizationAddress2 = new Address();
        organizationAddress2.setStreetNumber("180");
        organizationAddress2.setStreetName("San Francisco Avenue");
        organizationAddress2.setCity("San Francisco");
        organizationAddress2.setStateProvince("California");
        organizationAddress2.setZipPostalCode("57556");
        organizationAddress2.setCountry("USA");

        Organization organization2 = new Organization();
        organization2.setName("Elusive Organization");
        organization2.setAddress(organizationAddress2);
        organization2.setDescription("An organization nobody knows about");
        organization2.setPhone("18008008081");
        organization2.setEmail("elusiveorganization@elusive.org");
        organization2 = organizationDao.addOrganization(organization2);
        
        List<Organization> organizations2 = new ArrayList<>();
        organizations2.add(organization2);
        
        HeroVillain heroVillain2 = new HeroVillain();
        heroVillain2.setName("Mr. Untraceable");
        heroVillain2.setIsHero(false);
        heroVillain2.setDescription("Refuses to fight in person");
        heroVillain2.setSuperpower(superpower2);
        heroVillain2.setOrganizations(organizations2);
        heroVillain2 = heroVillainDao.addHeroVillain(heroVillain2);
        
        /////
        
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
        
        Address locationAddress2 = new Address();
        locationAddress2.setStreetNumber("299");
        locationAddress2.setStreetName("Bloor West");
        locationAddress2.setCity("Toronto");
        locationAddress2.setStateProvince("Ontario");
        locationAddress2.setZipPostalCode("M5S1W2");
        locationAddress2.setCountry("Canada");
        
        Location location2 = new Location();
        location2.setName("Varsity Center");
        location2.setDescription("UofT Sports court");
        location2.setLatitude(43.66725466105625);
        location2.setLongitude(-79.39725987338848);
        location2.setAddress(locationAddress2);
        location2 = locationDao.addLocation(location2);
        
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setHeroVillain(heroVillain);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);
        
        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.now());
        sighting2.setHeroVillain(heroVillain2);
        sighting2.setLocation(location2);
        sighting2 = sightingDao.addSighting(sighting2);
        
        List<Location> locations = new ArrayList<>();
        locations.add(location);
        heroVillain.setLocations(locations);
        
        List<Location> locations2 = new ArrayList<>();
        locations2.add(location2);
        heroVillain2.setLocations(locations2);
        
        List<HeroVillain> heroVillains = heroVillainDao.getAllHeroVillains();
        
        assertEquals(heroVillains.size(), 2);
        assertTrue(heroVillains.contains(heroVillain));
        assertTrue(heroVillains.contains(heroVillain2));
    }

    @Test
    public void testUpdateHeroVillain() {
        Superpower superpower = new Superpower();
        superpower.setName("Superspeed");
        superpower.setDescription("Telepathic speed");
        superpower = superpowerDao.addSuperpower(superpower);

        Address organizationAddress = new Address();
        organizationAddress.setStreetNumber("18");
        organizationAddress.setStreetName("California Avenue");
        organizationAddress.setCity("Los Angeles");
        organizationAddress.setStateProvince("California");
        organizationAddress.setZipPostalCode("57555");
        organizationAddress.setCountry("USA");

        Organization organization = new Organization();
        organization.setName("Crime Fighters");
        organization.setAddress(organizationAddress);
        organization.setDescription("An organization that helps to fight crime");
        organization.setPhone("18008008080");
        organization.setEmail("crimefighters@crimefighters.org");
        organization = organizationDao.addOrganization(organization);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        
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
        
        Address locationAddress2 = new Address();
        locationAddress2.setStreetNumber("299");
        locationAddress2.setStreetName("Bloor West");
        locationAddress2.setCity("Toronto");
        locationAddress2.setStateProvince("Ontario");
        locationAddress2.setZipPostalCode("M5S1W2");
        locationAddress2.setCountry("Canada");
        
        Location location2 = new Location();
        location2.setName("Varsity Center");
        location2.setDescription("UofT Sports court");
        location2.setLatitude(43.66725466105625);
        location2.setLongitude(-79.39725987338848);
        location2.setAddress(locationAddress2);
        location2 = locationDao.addLocation(location2);

        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Superman");
        heroVillain.setIsHero(true);
        heroVillain.setDescription("A superhero with superhuman abilities");
        heroVillain.setSuperpower(superpower);
        heroVillain.setOrganizations(organizations);
        heroVillain = heroVillainDao.addHeroVillain(heroVillain);
        
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setHeroVillain(heroVillain);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);
        
        List<Location> locations = new ArrayList<>();
        locations.add(location);
        heroVillain.setLocations(locations);

        HeroVillain heroVillainFromDao = heroVillainDao.getHeroVillainByID(heroVillain.getHeroVillainID());
        
        assertEquals(heroVillain, heroVillainFromDao);
        
        ///////
        
        Address organizationAddress2 = new Address();
        organizationAddress2.setStreetNumber("170");
        organizationAddress2.setStreetName("Evil Street");
        organizationAddress2.setCity("Las Vegas");
        organizationAddress2.setStateProvince("Nevada");
        organizationAddress2.setZipPostalCode("65655");
        organizationAddress2.setCountry("USA");

        Organization organization2 = new Organization();
        organization2.setName("Hitman Crew");
        organization2.setAddress(organizationAddress2);
        organization2.setDescription("Organized group of assassins");
        organization2.setPhone("18008889999");
        organization2.setEmail("hitmancrew@hitman.org");
        organization2 = organizationDao.addOrganization(organization2);
        
        List<Organization> organizations2 = new ArrayList<>();
        organizations2.add(organization2);
        heroVillain.setOrganizations(organizations2);
        
        heroVillain.setDescription("A supervillain who used to be a superhero");
        heroVillain.setIsHero(false);
        heroVillainDao.updateHeroVillain(heroVillain);
        heroVillainFromDao = heroVillainDao.getHeroVillainByID(heroVillain.getHeroVillainID());
        
        assertEquals(heroVillain, heroVillainFromDao);
    }

    @Test
    public void testDeleteHeroVillainByID() {
        Superpower superpower = new Superpower();
        superpower.setName("Superspeed");
        superpower.setDescription("Telepathic speed");
        superpower = superpowerDao.addSuperpower(superpower);

        Address organizationAddress = new Address();
        organizationAddress.setStreetNumber("18");
        organizationAddress.setStreetName("California Avenue");
        organizationAddress.setCity("Los Angeles");
        organizationAddress.setStateProvince("California");
        organizationAddress.setZipPostalCode("57555");
        organizationAddress.setCountry("USA");

        Organization organization = new Organization();
        organization.setName("Crime Fighters");
        organization.setAddress(organizationAddress);
        organization.setDescription("An organization that helps to fight crime");
        organization.setPhone("18008008080");
        organization.setEmail("crimefighters@crimefighters.org");
        organization = organizationDao.addOrganization(organization);
        
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setName("Superman");
        heroVillain.setIsHero(true);
        heroVillain.setDescription("Superhuman abilities");
        heroVillain.setSuperpower(superpower);
        heroVillain.setOrganizations(organizations);
        heroVillain = heroVillainDao.addHeroVillain(heroVillain);
        
        /////
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Invisibility");
        superpower2.setDescription("Disappear into thin air");
        superpower2 = superpowerDao.addSuperpower(superpower2);

        Address organizationAddress2 = new Address();
        organizationAddress2.setStreetNumber("180");
        organizationAddress2.setStreetName("San Francisco Avenue");
        organizationAddress2.setCity("San Francisco");
        organizationAddress2.setStateProvince("California");
        organizationAddress2.setZipPostalCode("57556");
        organizationAddress2.setCountry("USA");

        Organization organization2 = new Organization();
        organization2.setName("Elusive Organization");
        organization2.setAddress(organizationAddress2);
        organization2.setDescription("An organization nobody knows about");
        organization2.setPhone("18008008081");
        organization2.setEmail("elusiveorganization@elusive.org");
        organization2 = organizationDao.addOrganization(organization2);
        
        List<Organization> organizations2 = new ArrayList<>();
        organizations2.add(organization2);
        
        HeroVillain heroVillain2 = new HeroVillain();
        heroVillain2.setName("Mr. Untraceable");
        heroVillain2.setIsHero(false);
        heroVillain2.setDescription("Refuses to fight in person");
        heroVillain2.setSuperpower(superpower2);
        heroVillain2.setOrganizations(organizations2);
        heroVillain2 = heroVillainDao.addHeroVillain(heroVillain2);
        
        /////
        
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
        
        Address locationAddress2 = new Address();
        locationAddress2.setStreetNumber("299");
        locationAddress2.setStreetName("Bloor West");
        locationAddress2.setCity("Toronto");
        locationAddress2.setStateProvince("Ontario");
        locationAddress2.setZipPostalCode("M5S1W2");
        locationAddress2.setCountry("Canada");
        
        Location location2 = new Location();
        location2.setName("Varsity Center");
        location2.setDescription("UofT Sports court");
        location2.setLatitude(43.66725466105625);
        location2.setLongitude(-79.39725987338848);
        location2.setAddress(locationAddress2);
        location2 = locationDao.addLocation(location2);
        
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setHeroVillain(heroVillain);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);
        
        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.now());
        sighting2.setHeroVillain(heroVillain2);
        sighting2.setLocation(location2);
        sighting2 = sightingDao.addSighting(sighting2);
        
        List<Location> locations = new ArrayList<>();
        locations.add(location);
        heroVillain.setLocations(locations);
        
        List<Location> locations2 = new ArrayList<>();
        locations2.add(location2);
        heroVillain2.setLocations(locations2);
        
        List<HeroVillain> heroVillains = heroVillainDao.getAllHeroVillains();
        
        assertEquals(heroVillains.size(), 2);
        assertTrue(heroVillains.contains(heroVillain));
        assertTrue(heroVillains.contains(heroVillain2));
        
        heroVillainDao.deleteHeroVillainByID(heroVillain.getHeroVillainID());
        heroVillains = heroVillainDao.getAllHeroVillains();
        
        assertEquals(heroVillains.size(), 1);
        assertTrue(heroVillains.contains(heroVillain2));
        
        List<Sighting> sightings = sightingDao.getAllSightings();
        assertEquals(sightings.size(), 1);
    }

}