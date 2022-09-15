/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.LocationDaoDB.LocationMapper;
import com.sg.superherosightings.dao.OrganizationDaoDB.OrganizationMapper;
import com.sg.superherosightings.dao.SuperpowerDaoDB.SuperpowerMapper;
import com.sg.superherosightings.entities.Address;
import com.sg.superherosightings.entities.HeroVillain;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author roycerabanal
 */
@Repository
public class HeroVillainDaoDB implements HeroVillainDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public HeroVillain getHeroVillainByID(int heroVillainID) {
        String SELECT_HERO_BY_ID = "SELECT * FROM HeroVillain "
                + "WHERE HeroVillainID = ?";
        try {
            HeroVillain heroVillain = jdbc.queryForObject(SELECT_HERO_BY_ID, new HeroVillainMapper(), heroVillainID);
            // two private methods
            setHeroVillainSuperpower(heroVillain);
            setHeroVillainOrganizations(heroVillain);
            setLocationsForHeroVillain(heroVillain);
            return heroVillain;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    // setting object thus void
    private void setHeroVillainSuperpower(HeroVillain heroVillain) {
        String SELECT_HERO_VILLAIN_SUPERPOWER = "SELECT * FROM Superpower WHERE SuperpowerID = ( SELECT SuperpowerID FROM HeroVillain WHERE HeroVillainID = ?)";

        try {
            Superpower superpower = jdbc.queryForObject(SELECT_HERO_VILLAIN_SUPERPOWER, new SuperpowerMapper(), heroVillain.getHeroVillainID());
            heroVillain.setSuperpower(superpower);
        } catch (DataAccessException ex) {
            return;
        }
    }

    private void setHeroVillainOrganizations(HeroVillain heroVillain) {
        String SELECT_HEROVILLAIN_ORGANIZATIONS = "SELECT * FROM `Organization` o JOIN CharacterOrganization co ON o.OrganizationID = co.OrganizationID JOIN HeroVillain hv ON hv.HeroVillainID = co.HeroVillainID WHERE co.HeroVillainID = ?";
        List<Organization> organizations = jdbc.query(SELECT_HEROVILLAIN_ORGANIZATIONS, new OrganizationMapper(), heroVillain.getHeroVillainID());

        for (Organization organization : organizations) {
            String SELECT_ORGANIZATION_ADDRESS = "SELECT * FROM Address WHERE AddressID = (SELECT AddressID FROM `Organization` WHERE OrganizationID = ?)";
            Address address = jdbc.queryForObject(SELECT_ORGANIZATION_ADDRESS, new AddressMapper(), organization.getOrganizationID());
            organization.setAddress(address);
        }

        heroVillain.setOrganizations(organizations);
    }

    private void setLocationsForHeroVillain(HeroVillain heroVillain) {
        String SELECT_HEROVILLAIN_LOCATIONS = "SELECT l.* FROM Location l JOIN Sighting s ON l.LocationID = s.LocationID JOIN HeroVillain hv ON s.HeroVillainID = hv.HeroVillainID WHERE hv.HeroVillainID = ? GROUP BY l.LocationID";
        List<Location> heroVillainLocations = jdbc.query(SELECT_HEROVILLAIN_LOCATIONS, new LocationMapper(), heroVillain.getHeroVillainID());

        for (Location location : heroVillainLocations) {
            Address address = jdbc.queryForObject("SELECT * FROM Address WHERE AddressID = (SELECT AddressID FROM Location WHERE LocationID = ?)", new AddressMapper(), location.getLocationID());
            location.setAddress(address);
        }
        
        heroVillain.setLocations(heroVillainLocations);
    }

    @Override
    public List<HeroVillain> getAllHeroVillains() {
        String SELECT_HEROES = "SELECT * FROM HeroVillain";
        List<HeroVillain> heroes = jdbc.query(SELECT_HEROES, new HeroVillainMapper());

        for (HeroVillain hero : heroes) {
            setHeroVillainSuperpower(hero);
            setHeroVillainOrganizations(hero);
            setLocationsForHeroVillain(hero);
        }
        return heroes;
    }

    @Override
    @Transactional
    public HeroVillain addHeroVillain(HeroVillain heroVillain) {
        String ADD_HEROVILLAIN = "INSERT INTO HeroVillain(`Name`, IsHero, `Description`, SuperpowerID) VALUES(?,?,?,?)";
        jdbc.update(ADD_HEROVILLAIN, heroVillain.getName(), heroVillain.isIsHero(), heroVillain.getDescription(), heroVillain.getSuperpower().getSuperpowerID());
        heroVillain.setHeroVillainID(jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
        String ADD_HEROVILLAIN_ORGANIZATION = "INSERT INTO CharacterOrganization(HeroVillainID, OrganizationID) VALUES(?,?)";

        for (Organization organization : heroVillain.getOrganizations()) {
            jdbc.update(ADD_HEROVILLAIN_ORGANIZATION, heroVillain.getHeroVillainID(), organization.getOrganizationID());
        }

        return heroVillain;
    }

    @Override
    @Transactional
    public void updateHeroVillain(HeroVillain heroVillain) {
        String DELETE_HEROVILLAIN_ORGANIZATIONS = "DELETE FROM CharacterOrganization WHERE HeroVillainID = ?";
        jdbc.update(DELETE_HEROVILLAIN_ORGANIZATIONS, heroVillain.getHeroVillainID());
        String UPDATE_HEROVILLAIN = "UPDATE HeroVillain SET `Name` = ?, IsHero = ?, `Description` = ?, SuperpowerID = ? WHERE HeroVillainID = ?";
        jdbc.update(UPDATE_HEROVILLAIN, heroVillain.getName(), heroVillain.isIsHero(), heroVillain.getDescription(), heroVillain.getSuperpower().getSuperpowerID(), heroVillain.getHeroVillainID());

        String ADD_HEROVILLAIN_ORGANIZATION = "INSERT INTO CharacterOrganization(HeroVillainID, OrganizationID) VALUES(?,?)";

        for (Organization organization : heroVillain.getOrganizations()) {
            jdbc.update(ADD_HEROVILLAIN_ORGANIZATION, heroVillain.getHeroVillainID(), organization.getOrganizationID());
        }
    }

    @Override
    @Transactional
    public void deleteHeroVillainByID(int heroVillainID) {
        String DELETE_HEROVILLAIN_ORGANIZATION = "DELETE FROM CharacterOrganization WHERE HeroVillainID = ?";
        jdbc.update(DELETE_HEROVILLAIN_ORGANIZATION, heroVillainID);
        String DELETE_HEROVILLAIN_SIGHTINGS = "DELETE FROM Sighting WHERE HeroVillainID = ?";
        jdbc.update(DELETE_HEROVILLAIN_SIGHTINGS, heroVillainID);
        String DELETE_HEROVILLAIN = "DELETE FROM HeroVillain WHERE HeroVillainID = ?";
        jdbc.update(DELETE_HEROVILLAIN, heroVillainID);
    }

    public static final class HeroVillainMapper implements RowMapper<HeroVillain> {

        @Override
        public HeroVillain mapRow(ResultSet rs, int rowNum) throws SQLException {
            HeroVillain heroVillain = new HeroVillain();
            heroVillain.setHeroVillainID(rs.getInt("HeroVillainID"));
            heroVillain.setName(rs.getString("Name"));
            heroVillain.setIsHero(rs.getBoolean("IsHero"));
            heroVillain.setDescription(rs.getString("Description"));
            return heroVillain;
        }

    }
}
