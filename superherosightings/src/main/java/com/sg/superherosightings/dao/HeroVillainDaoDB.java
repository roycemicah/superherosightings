/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.OrganizationDaoDB.OrganizationMapper;
import com.sg.superherosightings.dao.SuperpowerDaoDB.SuperpowerMapper;
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
        String SELECT_HERO_BY_ID = "SELECT * FROM SuperheroSightings.HeroVillain "
                + "WHERE HeroVillainID = ?";
        try {
            HeroVillain heroVillain = jdbc.queryForObject(SELECT_HERO_BY_ID, new HeroVillainMapper(), heroVillainID);
            // two private methods
            setHeroVillainSuperpower(heroVillain);
            setHeroVillainOrganizations(heroVillain);
            return heroVillain;
        } catch(DataAccessException ex) {
            return null;
        }
    }
    
    // setting object thus void
    private void setHeroVillainSuperpower(HeroVillain heroVillain) {
        String SELECT_HERO_VILLAIN_SUPERPOWER = "SELECT * FROM SuperheroSightings.Superpower WHERE SuperpowerID = ( SELECT SuperpowerID FROM HeroVillain WHERE HeroVillainID = ?)";
        
        try {
            Superpower superpower = jdbc.queryForObject(SELECT_HERO_VILLAIN_SUPERPOWER, new SuperpowerMapper(), heroVillain.getHeroVillainID());
            heroVillain.setSuperpower(superpower);
        } catch (DataAccessException ex) {
            return;
        }
    }

    @Override
    public List<HeroVillain> getAllHeroVillains() {
        String SELECT_HEROES = "SELECT * FROM SuperheroSightings.HeroVillain";
        List<HeroVillain> heroes = jdbc.query(SELECT_HEROES, new HeroVillainMapper());
        
        for(HeroVillain hero : heroes) {
           setHeroVillainSuperpower(hero);
           setHeroVillainOrganizations(hero);
        }
        return heroes;
    }
    
    private void setHeroVillainOrganizations(HeroVillain heroVillain) {
        String SELECT_HEROVILLAIN_ORGANIZATIONS = "SELECT * FROM `Organization` o JOIN CharacterOrganization co ON o.OrganizationID = co.OrganizationID JOIN HeroVillain hv ON hv.HeroVillainID = co.HeroVillainID WHERE hv.HeroVillainID = ?";
        List<Organization> organizations = jdbc.query(SELECT_HEROVILLAIN_ORGANIZATIONS, new OrganizationMapper());
        heroVillain.setOrganizations(organizations);
    }

    @Override
    public HeroVillain addHeroVillain(HeroVillain heroVillain) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateHeroVillain(HeroVillain heroVillain) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteHeroVillainByID(int heroVillainID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Location> getLocationsForHeroVillain(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Organization> getOrganizationsForHeroVillain(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private static final class HeroVillainMapper implements RowMapper<HeroVillain> {

        @Override
        public HeroVillain mapRow(ResultSet rs, int rowNum) throws SQLException {
            HeroVillain heroVillain = new HeroVillain();
            heroVillain.setHeroVillainID(rs.getInt("HeroVillainID"));
            heroVillain.setName(rs.getString("Name"));
            heroVillain.setIsHero(rs.getBoolean("IsHero"));
            heroVillain.setDescription(rs.getString("Description"));
            //heroVillain.setSuperpowers(rs.getInt("SuperpowerID"));
            return heroVillain;
        }
        
    }
}
