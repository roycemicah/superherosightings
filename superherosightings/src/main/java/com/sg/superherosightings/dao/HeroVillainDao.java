/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.HeroVillain;
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

}