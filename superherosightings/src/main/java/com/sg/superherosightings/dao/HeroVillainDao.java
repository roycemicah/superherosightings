/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.HeroVillain;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import java.util.List;

/**
 *
 * @author roycerabanal
 */
public interface HeroVillainDao {
    
    public HeroVillain getHeroVillainByID(int heroVillainID);
    public List<HeroVillain> getAllHeroVillains();
    public HeroVillain addHeroVillain(HeroVillain heroVillain);
    public void updateHeroVillain(HeroVillain heroVillain);
    public void deleteHeroVillainByID(int heroVillainID);
    
    //The system must be able to report all of the locations where a particular superhero has been seen.
    public List<Location> getLocationsForHeroVillain(Location location);

    //The system must be able to report all of the organizations a particular superhero/villain belongs to.
    public List<Organization> getOrganizationsForHeroVillain(Organization organization);
}