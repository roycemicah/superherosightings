/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Superpower;
import java.util.List;

/**
 *
 * @author roycerabanal
 */
public interface SuperpowerDao {
    
    public Superpower getSuperpowerByID(int superpowerID);
    public List<Superpower> getAllSuperpowers();
    public Superpower addSuperpower(Superpower superpower);
    public void updateSuperpower(Superpower superpower);
    public void deleteSuperpowerByID(int superpowerID);
    
}
