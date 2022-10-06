/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.entities.Address;
import com.sg.superherosightings.entities.HeroVillain;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.service.SuperheroSightingsServiceLayer;
import java.util.ArrayList;
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
public class OrganizationController {
    @Autowired
    SuperheroSightingsServiceLayer service;
    
    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = service.getAllOrganizations();
        List<HeroVillain> heroVillains = service.getAllHeroVillains();
        model.addAttribute("organizations", organizations);
        model.addAttribute("heroVillains", heroVillains);
        return "organizations";
    }
    
    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request, Address address, Organization organization) {
        String[] heroVillainIDs = request.getParameterValues("heroVillainIDs");
        List<HeroVillain> members = new ArrayList<>();
        
        for(String heroVillainID : heroVillainIDs) {
            int id = Integer.parseInt(heroVillainID);
            members.add(service.getHeroVillainByID(id));
        }
        organization.setMembers(members);
        
        organization.setAddress(address);
        service.addOrganization(organization);
        return "redirect:/organizations";
    }
}
