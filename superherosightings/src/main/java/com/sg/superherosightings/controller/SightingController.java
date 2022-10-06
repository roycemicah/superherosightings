/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.entities.HeroVillain;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.service.SuperheroSightingsServiceLayer;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
public class SightingController {
    @Autowired
    SuperheroSightingsServiceLayer service;
    
    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Sighting> sightings = service.getAllSightings();
        List<Location> locations = service.getAllLocations();
        List<HeroVillain> heroVillains = service.getAllHeroVillains();
        model.addAttribute("sightings", sightings);
        model.addAttribute("locations", locations);
        model.addAttribute("heroVillains", heroVillains);
        return "sightings";
    }
    
    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request, Sighting sighting) {
        String locationID = request.getParameter("locationID");
        String heroVillainID = request.getParameter("heroVillainID");
        String dateString = request.getParameter("dateString");
        Location location = service.getLocationByID(Integer.parseInt(locationID));
        HeroVillain heroVillain = service.getHeroVillainByID(Integer.parseInt(heroVillainID));
        LocalDate date = LocalDate.parse(dateString);
                
        sighting.setLocation(location);
        sighting.setHeroVillain(heroVillain);
        sighting.setDate(date);
        
        service.addSighting(sighting);
        return "redirect:/sightings";
    }
}
