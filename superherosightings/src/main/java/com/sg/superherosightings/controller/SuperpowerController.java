/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.entities.HeroVillain;
import com.sg.superherosightings.entities.Superpower;
import com.sg.superherosightings.service.SuperheroSightingsServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author roycerabanal
 */
@Controller
public class SuperpowerController {

    @Autowired
    SuperheroSightingsServiceLayer service;
    
    @GetMapping("superpowers")    
    public String displaySuperpowers(Model model) {
        List<Superpower> superpowers = service.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        return "superpowers";
    }
    
    @PostMapping("addSuperpower")
    public String addSuperpower(Superpower superpower, Model model) {
        model.addAttribute("superpowers", superpower);
        service.addSuperpower(superpower);
        return "redirect:/superpowers";
    }
    
    @GetMapping("editSuperpower")
    public String editSuperpower(Integer superpowerID, Model model) {
        Superpower superpower = service.getSuperpowerByID(superpowerID);
        model.addAttribute("superpower", superpower);
        return "editSuperpower";
    }
    
    @PostMapping("editSuperpower")
    public String updateSuperpower(Superpower superpower) {
        
        service.updateSuperpower(superpower);
        return "redirect:/superpowers";
    }
    
    @GetMapping("superpowerDetail")
    public String superpowerDetail(Integer superpowerID, Model model){
        Superpower superpower = service.getSuperpowerByID(superpowerID);
        List<HeroVillain> heroVillains = service.getHeroVillainsBySuperpowerID(superpowerID);
        model.addAttribute("superpower", superpower);
        model.addAttribute("heroVillains", heroVillains);
        return "superpowerDetail";
    }
    
    @GetMapping("deleteSuperpower")
    public String deleteSuperpower(Integer superpowerID, Model model) {
        Superpower superpower = service.getSuperpowerByID(superpowerID);
        model.addAttribute("superpower", superpower);
        return "deleteSuperpower";
    }
    
    @PostMapping("deleteSuperpower")
    public String confirmDeleteSuperpower(Integer superpowerID) {
        service.deleteSuperpowerByID(superpowerID);
        return "redirect:/superpowers";
    }
    
}
