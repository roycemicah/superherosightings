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
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class OrganizationDaoDBTest {

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

    public OrganizationDaoDBTest() {
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
    public void testAddGetOrganizationByID() {
        /**
         * Organization table has a FK reference to AddressID and is a FK in the
         * composite primary key for CharacterOrganization. HeroVillain is also
         * a composite primary key Organization needs an Address Testing for
         * heroes belonging to a specific organization
        *
         */

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

        //////
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
        List<HeroVillain> members = new ArrayList<>();
        members.add(heroVillain);
        organization.setMembers(members);

        organization = organizationDao.addOrganization(organization);

        Organization organizationFromDao = organizationDao.getOrganizationByID(organization.getOrganizationID());

        assertEquals(organization, organizationFromDao);

    }

    @Test
    public void testGetAllOrganizations() {
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

        //////
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
        List<HeroVillain> members = new ArrayList<>();
        members.add(heroVillain);
        organization.setMembers(members);
        organization = organizationDao.addOrganization(organization);
        //////

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
        List<HeroVillain> members2 = new ArrayList<>();
        members2.add(heroVillain);
        members2.add(heroVillain2);
        organization2.setMembers(members2);
        organization2 = organizationDao.addOrganization(organization2);

        List<Organization> organizations = organizationDao.getAllOrganizations();

        assertEquals(organizations.size(), 2);
        assertTrue(organizations.contains(organization));
        assertTrue(organizations.contains(organization2));
    }

    @Test
    public void testUpdateOrganization() {
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

        //////
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
        
        //////
        
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
        List<HeroVillain> members = new ArrayList<>();
        members.add(heroVillain);
        organization.setMembers(members);
        organization = organizationDao.addOrganization(organization);
        
        organization.getAddress().setStreetNumber("20");
        organization.getAddress().setStreetName("Nevada Avenue");
        organization.getAddress().setCity("Las Vegas");
        organization.getAddress().setStateProvince("Nevada");
        organization.getAddress().setZipPostalCode("67688");
        organization.getAddress().setCountry("USA");
        
        organization.setName("Null Organization");
        organization.setDescription("Unknown to the public");
        organization.setPhone("18889008080");
        organization.setEmail("nullorganization@null.org");
        List<HeroVillain> members2 = new ArrayList<>();
        members2.add(heroVillain2);
        organization.setMembers(members2);
        
        organizationDao.updateOrganization(organization);
        Organization organizationFromDao = organizationDao.getOrganizationByID(organization.getOrganizationID());
        
        assertEquals(organization, organizationFromDao);
        
    }

    @Test
    public void testDeleteOrganizationByID() {
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

        //////
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
        
        //////
        
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
        List<HeroVillain> members = new ArrayList<>();
        members.add(heroVillain);
        organization.setMembers(members);
        organization = organizationDao.addOrganization(organization);
        
        //////

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
        List<HeroVillain> members2 = new ArrayList<>();
        members2.add(heroVillain);
        members2.add(heroVillain2);
        organization2.setMembers(members2);
        organization2 = organizationDao.addOrganization(organization2);
        
        organizationDao.deleteOrganizationByID(organization.getOrganizationID());
        organization = organizationDao.getOrganizationByID(organization.getOrganizationID());
        assertNull(organization);
        
        List<Organization> allOrganizations = organizationDao.getAllOrganizations();
        assertEquals(allOrganizations.size(), 1);
        assertTrue(allOrganizations.contains(organization2));
        
    }

}
