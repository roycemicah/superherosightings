/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.service.SuperheroSightingsServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author roycerabanal
 */
@Controller
public class IndexController {
    @Autowired
    SuperheroSightingsServiceLayer service;
    
    @GetMapping("/")
    public String goHome(Model model) {
        List<Sighting> sightings = service.get10MostRecentSightings();
        model.addAttribute("sightings", sightings);
        return "index";
    }
}
