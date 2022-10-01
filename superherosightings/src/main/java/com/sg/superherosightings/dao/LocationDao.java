/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Location;
import java.util.List;

/**
 *
 * @author roycerabanal
 */
public interface LocationDao {
    
    public Location getLocationByID(int locationID);
    public List<Location> getAllLocations();
    public Location addLocation(Location location);
    public void updateLocation(Location location);
    public void deleteLocationByID(int locationID);

}
