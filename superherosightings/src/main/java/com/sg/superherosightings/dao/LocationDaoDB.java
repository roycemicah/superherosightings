/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.HeroVillainDaoDB.HeroVillainMapper;
import com.sg.superherosightings.entities.Address;
import com.sg.superherosightings.entities.HeroVillain;
import com.sg.superherosightings.entities.Location;
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
public class LocationDaoDB implements LocationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location getLocationByID(int locationID) {
        try {
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM Location WHERE LocationID = ?";
            Location location = jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), locationID);
            // return address of location
            setLocationAddress(location);
            setHeroesSighted(location);
            return location;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private void setLocationAddress(Location location) {
        String SELECT_ADDRESS_FOR_LOCATION = "SELECT * FROM Address WHERE AddressID = (SELECT AddressID FROM Location WHERE LocationID = ?)";
        Address address = jdbc.queryForObject(SELECT_ADDRESS_FOR_LOCATION, new AddressMapper(), location.getLocationID());
        location.setAddress(address);
    }

    private void setHeroesSighted(Location location) {
        String SELECT_HEROES_SIGHTED = "SELECT hv.* FROM HeroVillain hv JOIN Sighting s ON hv.HeroVillainID = s.HeroVillainID JOIN Location l ON s.LocationID = l.locationID WHERE l.LocationID = ? GROUP BY hv.HeroVillainID";
        List<HeroVillain> heroVillains = jdbc.query(SELECT_HEROES_SIGHTED, new HeroVillainMapper(), location.getLocationID());
        location.setHeroVillainsSighted(heroVillains);
    }

    @Override
    public List<Location> getAllLocations() {
        final String SELECT_LOCATION_BY_ID = "SELECT * FROM Location";
        List<Location> locations = jdbc.query(SELECT_LOCATION_BY_ID, new LocationMapper());

        for (Location location : locations) {
            setLocationAddress(location);
            setHeroesSighted(location);
        }

        return locations;
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        String ADD_ADDRESS = "INSERT INTO Address(StreetNumber, StreetName, City, StateProvince, ZipPostalCode, Country) VALUES(?,?,?,?,?,?)";
        jdbc.update(ADD_ADDRESS, location.getAddress().getStreetNumber(), location.getAddress().getStreetName(), location.getAddress().getCity(), location.getAddress().getStateProvince(),
                location.getAddress().getZipPostalCode(), location.getAddress().getCountry());
        int addressID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        String ADD_LOCATION = "INSERT INTO Location(`Name`, `Description`, Latitude, Longitude, AddressID) VALUES (?,?,?,?,?)";
        jdbc.update(ADD_LOCATION, location.getName(), location.getDescription(), location.getLatitude(), location.getLongitude(), addressID);
        int locationID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocationID(locationID);
        return location;
    }

    @Override
    @Transactional
    public void updateLocation(Location location) {
        int addressID = jdbc.queryForObject("SELECT AddressID FROM Location WHERE LocationID = ?", Integer.class);
        String UPDATE_ADDRESS = "UPDATE Address SET StreetNumber = ?, StreetName = ?, City = ?, StateProvince = ?, ZipPostalCode = ?, Country = ? WHERE AddressID = ?";
        jdbc.update(UPDATE_ADDRESS, location.getAddress().getStreetNumber(), location.getAddress().getStreetName(), location.getAddress().getCity(), location.getAddress().getStateProvince(),
                location.getAddress().getZipPostalCode(), location.getAddress().getCountry(), addressID);
        String UPDATE_LOCATION = "UPDATE Location SET `Name` = ?, `Description` = ?, Latitude = ?, Longitude = ? WHERE LocationID = ?";
        jdbc.update(UPDATE_LOCATION, location.getName(), location.getDescription(), location.getLatitude(), location.getLongitude());
    }

    @Override
    @Transactional
    public void deleteLocationByID(int locationID) {
        String DELETE_SIGHTING_LOCATION = "DELETE FROM Sighting WHERE LocationID = ?";
        jdbc.update(DELETE_SIGHTING_LOCATION, locationID);
        // must delete the address data that doesn't need to be there, which is from the locationID
        int addressID = jdbc.queryForObject("SELECT AddressID FROM Location WHERE LocationID = ?", Integer.class, locationID);
        String DELETE_LOCATION_ID = "DELETE FROM Location WHERE LocationID = ?";
        jdbc.update(DELETE_LOCATION_ID, locationID);
        jdbc.update("DELETE FROM Address WHERE AddressID = ?", addressID);
    }

    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();
            location.setLocationID(rs.getInt("LocationID"));
            location.setName(rs.getString("Name"));
            location.setDescription(rs.getString("Description"));
            location.setLatitude(rs.getDouble("Latitude"));
            location.setLongitude(rs.getDouble("Longitude"));

            return location;
        }

    }

}
