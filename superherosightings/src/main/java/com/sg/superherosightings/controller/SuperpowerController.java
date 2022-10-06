/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.controller;

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
    public String editSuperpower(Integer id, Model model) {
        Superpower superpower = service.getSuperpowerByID(id);
        model.addAttribute("superpowers", superpower);
        return "editSuperpower";
    }
    
    @PostMapping("editSuperpower")
    public String updateSuperpower(Superpower superpower, Model model) {
        model.addAttribute("superpower", superpower);
        service.updateSuperpower(superpower);
        return "redirect:/superpowers";
    }
    
    @GetMapping("deleteSuperpower")
    public String deleteSuperpower(Superpower superpower) {
        service.deleteSuperpowerByID(superpower.getSuperpowerID());
        return "redirect:/superpowers";
    }
    
}
