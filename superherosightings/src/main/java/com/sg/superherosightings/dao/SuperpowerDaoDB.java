/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

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
public class SuperpowerDaoDB implements SuperpowerDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Superpower getSuperpowerByID(int superpowerID) {
        try {
            final String SELECT_SUPERPOWER_BY_ID = "SELECT * FROM SuperheroSightings.Superpower"
                    + " WHERE SuperpowerID = ?";
            Superpower superpower = jdbc.queryForObject(SELECT_SUPERPOWER_BY_ID, new SuperpowerMapper(), superpowerID);
            return superpower;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        final String SELECT_SUPERPOWERS = "SELECT * FROM SuperheroSightings.Superpower";
        return jdbc.query(SELECT_SUPERPOWERS, new SuperpowerMapper());
    }

    @Override
    @Transactional
    public Superpower addSuperpower(Superpower superpower) {
        final String ADD_SUPERPOWER = "INSERT INTO SuperheroSightings.Superpower(`Name`, `Description`) VALUES(?,?)";
        jdbc.update(ADD_SUPERPOWER, superpower.getName(), superpower.getDescription());
        int superpowerID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superpower.setSuperpowerID(superpowerID);
        return superpower;
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        String UPDATE_SUPERPOWER = "UPDATE SuperheroSightings.Superpower SET `Name` = ?, `Description` = ? WHERE SuperpowerID = ?";
        jdbc.update(UPDATE_SUPERPOWER, superpower.getName(), superpower.getDescription(), superpower.getSuperpowerID());
    }

    @Override
    @Transactional
    public void deleteSuperpowerByID(int superpowerID) {
        jdbc.update("UPDATE SuperheroSightings.HeroVillain SET SuperpowerID = null WHERE SuperpowerID = ?", superpowerID);
        String DELETE_SUPERPOWER = "DELETE FROM SuperheroSightings.Superpower WHERE SuperpowerID = ?";
        jdbc.update(DELETE_SUPERPOWER, superpowerID);
    }

    public static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int rowNum) throws SQLException {
            Superpower superpower = new Superpower();
            superpower.setSuperpowerID(rs.getInt("SuperpowerID"));
            superpower.setName(rs.getString("Name"));
            superpower.setDescription(rs.getString("Description"));

            return superpower;
        }

    }

}
