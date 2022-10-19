/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.HeroVillainDaoDB.HeroVillainMapper;
import com.sg.superherosightings.dao.SuperpowerDaoDB.SuperpowerMapper;
import com.sg.superherosightings.entities.Address;
import com.sg.superherosightings.entities.HeroVillain;
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
public class OrganizationDaoDB implements OrganizationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organization getOrganizationByID(int organizationID) {
        try {
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM `Organization` "
                    + "WHERE OrganizationID = ?";
            Organization organization = jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), organizationID);
            setOrganizationAddress(organization);
            setAllOrganizationMembers(organization);
            return organization;

        } catch (DataAccessException ex) {
            return null;
        }
    }

    private void setOrganizationAddress(Organization organization) {
        int addressID = jdbc.queryForObject("SELECT AddressID FROM `Organization` WHERE OrganizationID = ?", Integer.class, organization.getOrganizationID());
        Address address = jdbc.queryForObject("SELECT * FROM Address WHERE AddressID = ?", new AddressMapper(), addressID);
        organization.setAddress(address);
    }

    // this is a list on each Organization object
    private void setAllOrganizationMembers(Organization organization) {
        final String SELECT_ALL_MEMBERS = "SELECT hv.* FROM HeroVillain hv JOIN CharacterOrganization co ON hv.HeroVillainID = co.HeroVillainID JOIN Organization o ON co.OrganizationID = o.OrganizationID WHERE o.OrganizationID = ?";
        List<HeroVillain> heroVillains = jdbc.query(SELECT_ALL_MEMBERS, new HeroVillainMapper(), organization.getOrganizationID());

        for (HeroVillain heroVillain : heroVillains) {
            try {
                Superpower superpower = jdbc.queryForObject("SELECT * FROM Superpower WHERE SuperpowerID = (SELECT SuperpowerID FROM HeroVillain WHERE HeroVillainID = ?)", new SuperpowerMapper(), heroVillain.getHeroVillainID());
                heroVillain.setSuperpower(superpower);
            } catch (DataAccessException ex) {

            }
        }

        organization.setMembers(heroVillains);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM `Organization`";
        List<Organization> organizations = jdbc.query(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper());

        for (Organization organization : organizations) {
            setOrganizationAddress(organization);
            setAllOrganizationMembers(organization);
        }

        return organizations;
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String ADD_ORGANIZATION_ADDRESS = "INSERT INTO Address (StreetNumber, StreetName, City, StateProvince, ZipPostalCode, Country) VALUES (?,?,?,?,?,?)";
        jdbc.update(ADD_ORGANIZATION_ADDRESS, organization.getAddress().getStreetNumber(), organization.getAddress().getStreetName(), organization.getAddress().getCity(),
                organization.getAddress().getStateProvince(), organization.getAddress().getZipPostalCode(), organization.getAddress().getCountry());
        int addressID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        final String ADD_ORGANIZATION = "INSERT INTO `Organization`(`Name`, `Description`, Phone, Email, AddressID) VALUES(?,?,?,?,?)";
        jdbc.update(ADD_ORGANIZATION, organization.getName(), organization.getDescription(), organization.getPhone(), organization.getEmail(), addressID);
        organization.setOrganizationID(jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));

        for (HeroVillain heroVillain : organization.getMembers()) {
            jdbc.update("INSERT INTO CharacterOrganization(HeroVillainID, OrganizationID) VALUES(?,?)", heroVillain.getHeroVillainID(), organization.getOrganizationID());
        }

        return organization;
    }

    @Override
    @Transactional
    public void updateOrganization(Organization organization) {
        final String DELETE_CHARACTER_ORGANIZATION = "DELETE FROM CharacterOrganization WHERE OrganizationID = ?";
        jdbc.update(DELETE_CHARACTER_ORGANIZATION, organization.getOrganizationID());
        int addressID = jdbc.queryForObject("SELECT AddressID FROM `Organization` WHERE OrganizationID = ?", Integer.class, organization.getOrganizationID());
        final String UPDATE_ORGANIZATION_ADDRESS = "UPDATE Address SET StreetNumber = ?, StreetName = ?, City = ?, StateProvince = ?, ZipPostalCode = ?, Country = ? WHERE AddressID = ?";
        jdbc.update(UPDATE_ORGANIZATION_ADDRESS, organization.getAddress().getStreetNumber(), organization.getAddress().getStreetName(), organization.getAddress().getCity(),
                organization.getAddress().getStateProvince(), organization.getAddress().getZipPostalCode(), organization.getAddress().getCountry(), addressID);
        final String UPDATE_ORGANIZATION = "UPDATE `Organization` SET `Name` = ?, `Description` = ?, Phone = ?, Email = ? WHERE OrganizationID = ?";
        jdbc.update(UPDATE_ORGANIZATION, organization.getName(), organization.getDescription(), organization.getPhone(), organization.getEmail(), organization.getOrganizationID());

        String ADD_CHARACTER_ORGANIZATION = "INSERT INTO CharacterOrganization(HeroVillainID, OrganizationID) VALUES(?,?)";

        for (HeroVillain heroVillain : organization.getMembers()) {
            jdbc.update(ADD_CHARACTER_ORGANIZATION, heroVillain.getHeroVillainID(), organization.getOrganizationID());
        }
    }

    @Override
    @Transactional
    public void deleteOrganizationByID(int organizationID) {
        final String DELETE_CHARACTER_ORGANIZATION = "DELETE FROM CharacterOrganization WHERE OrganizationID = ?";
        jdbc.update(DELETE_CHARACTER_ORGANIZATION, organizationID);
        int addressID = jdbc.queryForObject("SELECT AddressID FROM `Organization` WHERE OrganizationID = ?", Integer.class, organizationID);
        final String DELETE_ORGANIZATION = "DELETE FROM `Organization` WHERE OrganizationID = ?";
        jdbc.update(DELETE_ORGANIZATION, organizationID);
        jdbc.update("DELETE FROM Address WHERE AddressID = ?", addressID);
    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {

            Organization organization = new Organization();
            organization.setOrganizationID(rs.getInt("OrganizationID"));
            organization.setName(rs.getString("Name"));
            organization.setDescription(rs.getString("Description"));
            organization.setPhone(rs.getString("Phone"));
            organization.setEmail(rs.getString("Email"));

            return organization;
        }

    }

}
