/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.HeroVillain;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superpower;
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
public class SuperpowerDaoDBTest {
    
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
    
    public SuperpowerDaoDBTest() {
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
    public void testAddGetSuperpowerByID() {
        Superpower superpower = new Superpower();
        superpower.setName("Superspeed");
        superpower.setDescription("Telepathic speed");
        superpower = superpowerDao.addSuperpower(superpower);
        
        Superpower superpowerFromDao = superpowerDao.getSuperpowerByID(superpower.getSuperpowerID());
        
        assertEquals(superpower, superpowerFromDao);
    }

    @Test
    public void testGetAllSuperpowers() {
        Superpower superpower = new Superpower();
        superpower.setName("Superspeed");
        superpower.setDescription("Telepathic speed");
        superpower = superpowerDao.addSuperpower(superpower);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Superspeed 2");
        superpower2.setDescription("Telepathic speed 2");
        superpower2 = superpowerDao.addSuperpower(superpower2);
        
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        
        assertEquals(2, superpowers.size());
        assertTrue(superpowers.contains(superpower));
        assertTrue(superpowers.contains(superpower2));
    }

    @Test
    public void testUpdateSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("Superspeed");
        superpower.setDescription("Telepathic speed");
        superpower = superpowerDao.addSuperpower(superpower);
        
        Superpower superpowerFromDao = superpowerDao.getSuperpowerByID(superpower.getSuperpowerID());
        assertEquals(superpower, superpowerFromDao);
        
        superpower.setName("New Superpower");
        superpowerDao.updateSuperpower(superpower);
        
        assertNotEquals(superpower, superpowerFromDao);
        
        superpowerFromDao = superpowerDao.getSuperpowerByID(superpower.getSuperpowerID());
        
        assertEquals(superpower, superpowerFromDao);
    }

    @Test
    public void testDeleteSuperpowerByID() {
        Superpower superpower = new Superpower();
        superpower.setName("Superspeed");
        superpower.setDescription("Telepathic speed");
        superpower = superpowerDao.addSuperpower(superpower);
        
        Superpower superpowerFromDao = superpowerDao.getSuperpowerByID(superpower.getSuperpowerID());
        assertEquals(superpower, superpowerFromDao);
        
        superpowerDao.deleteSuperpowerByID(superpower.getSuperpowerID());
        
        superpowerFromDao = superpowerDao.getSuperpowerByID(superpower.getSuperpowerID());
        assertNull(superpowerFromDao);
    }
    
}
