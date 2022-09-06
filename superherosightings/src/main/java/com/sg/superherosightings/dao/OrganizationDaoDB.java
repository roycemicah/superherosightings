/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.HeroVillainDaoDB.HeroVillainMapper;
import com.sg.superherosightings.entities.HeroVillain;
import com.sg.superherosightings.entities.Organization;
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
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM SuperheroSightings.`Organization` "
                    + "WHERE OrganizationID = ?";
            Organization organization = jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), organizationID);
            setAllOrganizationMembers(organization);
            return organization;

        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<Organization> getAllOrganizations() {
        final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM SuperheroSightings.`Organization`";
            List<Organization> organizations = jdbc.query(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper());
            
            for(Organization organization : organizations) {
                setAllOrganizationMembers(organization);
            }
            
            return organizations;
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String ADD_ORGANIZATION = "INSERT INTO `Organization`(`Name`, `Description`, Phone, Email, AddressID) VALUES(?,?,?,?,?)";
        jdbc.update(ADD_ORGANIZATION, organization.getOrganizationID());
        organization.setOrganizationID(jdbc.queryForObject("SELECT LAST_INSERT_ID", Integer.class));
        return organization;
    }

    @Override
    public void updateOrganization(Organization organization) {
        final String DELETE_CHARACTER_ORGANIZATION = "DELETE FROM CharacterOrganization WHERE OrganizationID = ?";
        jdbc.update(DELETE_CHARACTER_ORGANIZATION, organization.getOrganizationID());
        final String UPDATE_ORGANIZATION = "UPDATE `Organization` SET `Name` = ?, `Description` = ?, Phone = ?, Email = ?, AddressID = ? WHERE OrganizationID = ?";
        jdbc.update(UPDATE_ORGANIZATION, organization.getName(), organization.getDescription(), organization.getPhone(), organization.getEmail(), organization.getAddress());

        String ADD_CHARACTER_ORGANIZATION = "INSERT INTO CharacterOrganization(HeroVillainID, OrganizationID) VALUES(?,?)";

        for (HeroVillain heroVillain : organization.getMembers()) {
            jdbc.update(ADD_CHARACTER_ORGANIZATION, heroVillain.getHeroVillainID(), organization.getOrganizationID());
        }
    }

    @Override
    public void deleteOrganizationByID(int OrganizationID) {
        final String DELETE_CHARACTER_ORGANIZATION = "DELETE FROM CharacterOrganization WHERE OrganizationID = ?";
        jdbc.update(DELETE_CHARACTER_ORGANIZATION, OrganizationID);
        final String DELETE_ORGANIZATION = "DELETE FROM `Organization` WHERE OrganizationID = ?";
        jdbc.update(DELETE_ORGANIZATION, OrganizationID);
    }

    private void setAllOrganizationMembers(Organization organization) {
        final String SELECT_ALL_MEMBERS = "SELECT hv.* FROM HeroVillain hv JOIN CharacterOrganization co ON hv.HeroVillainID = co.HeroVillainID JOIN Organization o ON co.OrganizationID = o.OrganizationID WHERE o.OrganizationID = ?";
        List<HeroVillain> heroVillains = jdbc.query(SELECT_ALL_MEMBERS, new HeroVillainMapper(), organization.getOrganizationID());
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
