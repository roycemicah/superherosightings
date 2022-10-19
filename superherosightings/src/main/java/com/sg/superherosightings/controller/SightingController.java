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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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

    Set<ConstraintViolation<Sighting>> sightingViolations = new HashSet<>();

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
    public String addSighting(Integer locationID, Integer heroVillainID, String dateString, Sighting sighting, Model model) {

        Location location = null;
        if (locationID != null) {
            location = service.getLocationByID(locationID);
        }
        sighting.setLocation(location);

        HeroVillain heroVillain = null;
        if (heroVillainID != null) {
            heroVillain = service.getHeroVillainByID(heroVillainID);
        }
        sighting.setHeroVillain(heroVillain);

        // validates that the user is passing a correctly-formatted date
        try {
            LocalDate date = LocalDate.parse(dateString);
            sighting.setDate(date);
        } catch (DateTimeParseException ex) {

        }

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        sightingViolations = validate.validate(sighting);

        if (!sightingViolations.isEmpty()) {
            List<Sighting> sightings = service.getAllSightings();
            List<Location> locations = service.getAllLocations();
            List<HeroVillain> heroVillains = service.getAllHeroVillains();
            model.addAttribute("sightings", sightings);
            model.addAttribute("locations", locations);
            model.addAttribute("heroVillains", heroVillains);
            model.addAttribute("badSighting", sighting);
            model.addAttribute("errors", sightingViolations);
            
            return "sightings";
        }

        service.addSighting(sighting);
        return "redirect:/sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(HttpServletRequest request, Model model) {
        String sightingID = request.getParameter("sightingID");
        Sighting sighting = service.getSightingByID(Integer.parseInt(sightingID));
        List<Location> locations = service.getAllLocations();

        for (Location location : locations) {
            location.setHeroVillainsSighted(new ArrayList<>());
        }

        List<HeroVillain> heroVillains = service.getAllHeroVillains();

        for (HeroVillain heroVillain : heroVillains) {
            heroVillain.setOrganizations(new ArrayList<>());
            heroVillain.setLocations(new ArrayList<>());
        }

        model.addAttribute("sighting", sighting);
        model.addAttribute("locations", locations);
        model.addAttribute("heroVillains", heroVillains);

        return "editSighting";
    }

    @PostMapping("editSighting")
    public String updateSighting(Sighting sighting, Integer locationID, Integer heroVillainID,
            String dateString, Model model) {
        Location location = null;
        if (locationID != null) {
            location = service.getLocationByID(locationID);
        }
        sighting.setLocation(location);

        HeroVillain heroVillain = null;
        if (heroVillainID != null) {
            heroVillain = service.getHeroVillainByID(heroVillainID);
        }
        sighting.setHeroVillain(heroVillain);

        // validates that the user is passing a correctly-formatted date
        try {
            LocalDate date = LocalDate.parse(dateString);
            sighting.setDate(date);
        } catch (DateTimeParseException ex) {

        }

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        sightingViolations = validate.validate(sighting);

        if (!sightingViolations.isEmpty()) {
            List<Sighting> sightings = service.getAllSightings();
            List<Location> locations = service.getAllLocations();
            List<HeroVillain> heroVillains = service.getAllHeroVillains();
            model.addAttribute("sightings", sightings);
            model.addAttribute("locations", locations);
            model.addAttribute("heroVillains", heroVillains);
            model.addAttribute("badSighting", sighting);
            model.addAttribute("errors", sightingViolations);
            
            return "editSighting";
        }

        service.updateSighting(sighting);
        return "redirect:/sightings";
    }

    @GetMapping("sightingDetail")
    public String sightingDetail(Integer sightingID, Model model) {
        Sighting sighting = service.getSightingByID(sightingID);
        model.addAttribute("sighting", sighting);
        return "sightingDetail";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(Integer sightingID, Model model) {
        Sighting sighting = service.getSightingByID(sightingID);
        model.addAttribute("sighting", sighting);
        return "deleteSighting";
    }

    @PostMapping("deleteSighting")
    public String confirmDeleteSighting(Integer sightingID) {
        service.deleteSightingByID(sightingID);
        return "redirect:/sightings";
    }

}
