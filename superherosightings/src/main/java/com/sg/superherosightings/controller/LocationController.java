/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.entities.Address;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.service.SuperheroSightingsServiceLayer;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author roycerabanal
 */
@Controller
public class LocationController {
    @Autowired
    SuperheroSightingsServiceLayer service;
    
    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = service.getAllLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }
    
    @PostMapping("addLocation")
    public String addLocation(Location location, Address address) {
        location.setAddress(address);
        service.addLocation(location);
        return "redirect:/locations";
    }
    
    @GetMapping("locationImage/{locationID}")
    public void displayLocationImage(HttpServletResponse response, @PathVariable Integer locationID) throws IOException {
        Location location = service.getLocationByID(locationID);
        StreamUtils.copy(location.getImage(), response.getOutputStream());
    }
    
}
