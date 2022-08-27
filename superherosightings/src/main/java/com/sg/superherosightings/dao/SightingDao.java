/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author roycerabanal
 */
public interface SightingDao {
    
    public Sighting getSightingByID(int sightingID);
    public List<Sighting> getAllSightings();
    public Sighting addSighting(Sighting sighting);
    public void updateSighting(Sighting sighting);
    public void deleteSightingByID(int sightingID);
    
    //The system must be able to report all of the locations where a particular superhero has been seen.
    List<Sighting> getSightingsByHeroVillain(int heroVillainID);
    
    //The system must be able to report all of the locations where a particular superhero has been seen.
    List<Sighting> getSightingsByLocation(Location location);
    
    //The system must be able to report all sightings (hero and location) for a particular date.
    List<Sighting> getSightingsByDate(LocalDate date);

}

//A user must be able to record a superhero/supervillain sighting for a particular location and date.

//The system must be able to report all of the superheroes sighted at a particular location.

//The system must be able to report all of the locations where a particular superhero has been seen.
//The system must be able to report all sightings (hero and location) for a particular date.
