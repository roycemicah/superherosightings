/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.HeroVillainDaoDB.HeroVillainMapper;
import com.sg.superherosightings.dao.LocationDaoDB.LocationMapper;
import com.sg.superherosightings.dao.SuperpowerDaoDB.SuperpowerMapper;
import com.sg.superherosightings.entities.Address;
import com.sg.superherosightings.entities.HeroVillain;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superpower;
import java.sql.Date;
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
public class SightingDaoDB implements SightingDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sighting getSightingByID(int sightingID) {
        String SELECT_SIGHTING_BY_ID = "SELECT SightingID, `Date` FROM Sighting WHERE SightingID = ?";

        try {
            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingID);
            setHeroSighted(sighting);
            setSightingLocation(sighting);
            return sighting;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private void setHeroSighted(Sighting sighting) {
        String SELECT_SIGHTING_HERO = "SELECT * FROM HeroVillain WHERE HeroVillainID = (SELECT HeroVillainID FROM Sighting WHERE SightingID = ?)";
        HeroVillain hero = jdbc.queryForObject(SELECT_SIGHTING_HERO, new HeroVillainMapper(), sighting.getSightingID());

        try {
            Superpower superpower = jdbc.queryForObject("SELECT * FROM Superpower WHERE SuperpowerID = (SELECT SuperpowerID FROM HeroVillain WHERE HeroVillainID = ?)", new SuperpowerMapper(), hero.getHeroVillainID());
            hero.setSuperpower(superpower);
        } catch (DataAccessException ex) {

        }

        sighting.setHeroVillain(hero);
    }

    private void setSightingLocation(Sighting sighting) {
        String SELECT_SIGHTING_LOCATION = "SELECT * FROM Location WHERE LocationID = (SELECT LocationID FROM Sighting WHERE SightingID = ?)";
        Location location = jdbc.queryForObject(SELECT_SIGHTING_LOCATION, new LocationMapper(), sighting.getSightingID());
        int addressID = jdbc.queryForObject("SELECT AddressID FROM Location WHERE LocationID = ?", Integer.class, location.getLocationID());
        Address address = jdbc.queryForObject("SELECT * FROM Address WHERE AddressID = ?", new AddressMapper(), addressID);
        location.setAddress(address);
        sighting.setLocation(location);
    }

    @Override
    public List<Sighting> getAllSightings() {
        String SELECT_ALL_SIGHTINGS = "SELECT SightingID, `Date` FROM Sighting";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());

        for (Sighting sighting : sightings) {
            setHeroSighted(sighting);
            setSightingLocation(sighting);
        }

        return sightings;
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        String ADD_SIGHTING = "INSERT INTO Sighting(`Date`, LocationID, HeroVillainID) VALUES(?,?,?)";
        jdbc.update(ADD_SIGHTING, Date.valueOf(sighting.getDate()), sighting.getLocation().getLocationID(), sighting.getHeroVillain().getHeroVillainID());
        int sightingID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingID(sightingID);
        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        String UPDATE_SIGHTING = "UPDATE Sighting SET `Date` = ?, LocationID = ?, HeroVillainID = ? WHERE SightingID = ?";
        jdbc.update(UPDATE_SIGHTING, Date.valueOf(sighting.getDate()), sighting.getLocation().getLocationID(), sighting.getHeroVillain().getHeroVillainID(), sighting.getSightingID());
    }

    @Override
    public void deleteSightingByID(int sightingID) {
        String DELETE_SIGHTING = "DELETE FROM Sighting WHERE SightingID = ?";
        jdbc.update(DELETE_SIGHTING, sightingID);
    }

    @Override
    public List<Sighting> getSightingsOrderedByDate() {
        String SELECT_ALL_SIGHTINGS_ORDERED_BY_DATE = "SELECT SightingID, `Date` FROM Sighting ORDER BY `Date` DESC";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS_ORDERED_BY_DATE, new SightingMapper());

        for (Sighting sighting : sightings) {
            setHeroSighted(sighting);
            setSightingLocation(sighting);
        }

        return sightings;
    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("SightingID"));
            sighting.setDate(rs.getDate("Date").toLocalDate());
            return sighting;
        }

    }
}
