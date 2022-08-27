/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author roycerabanal
 */
public final class AddressMapper implements RowMapper<Address> {

    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        Address address = new Address();
        address.setStreetNumber(rs.getString("StreetNumber"));
        address.setStreetName(rs.getString("StreetName"));
        address.setCity(rs.getString("City"));
        address.setStateProvince(rs.getString("StateProvince"));
        address.setZipPostalCode(rs.getString("ZipPostalCode"));
        address.setCountry(rs.getString("Country"));

        return address;
    }

}
