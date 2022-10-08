/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.entities.HeroVillain;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Superpower;
import com.sg.superherosightings.service.SuperheroSightingsServiceLayer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author roycerabanal
 */
@Controller
public class HeroVillainController {

    @Autowired
    SuperheroSightingsServiceLayer service;

    @GetMapping("heroVillains")
    public String displayHeroVillains(Model model) {
        List<HeroVillain> heroVillains = service.getAllHeroVillains();
        List<Superpower> superpowers = service.getAllSuperpowers();
        List<Organization> organizations = service.getAllOrganizations();
        model.addAttribute("heroVillains", heroVillains);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("organizations", organizations);
        return "heroVillains";
    }

    @PostMapping("addHeroVillain")
    public String addHeroVillain(HeroVillain heroVillain, HttpServletRequest request, @RequestParam("file") MultipartFile image) {
        String[] organizationIDs = request.getParameterValues("organizationIDs");
        String superpowerID = request.getParameter("superpowerID");
        List<Organization> organizations = new ArrayList<>();

        try {
            byte[] imageBytes = image.getBytes();
            heroVillain.setImage(imageBytes);
        } catch (IOException ex) {

        }

        if (organizationIDs != null) {
            for (String id : organizationIDs) {
                //convert to int
                int integerID = Integer.parseInt(id);
                organizations.add(service.getOrganizationByID(integerID));
            }
        }

        heroVillain.setOrganizations(organizations);

        Superpower superpower;
        superpower = service.getSuperpowerByID(Integer.parseInt(superpowerID));

        heroVillain.setSuperpower(superpower);

        service.addHeroVillain(heroVillain);
        return "redirect:/heroVillains";
    }
    
    @GetMapping("editHeroVillain")
    public String editHeroVillain(Integer heroVillainID, Model model) {
        HeroVillain heroVillain = service.getHeroVillainByID(heroVillainID);
        List<Organization> organizations = service.getAllOrganizations();
        List<Superpower> superpowers = service.getAllSuperpowers();
        
        // empties members' value in the organization list
        for(Organization organization : organizations) {
            organization.setMembers(new ArrayList<>());
        }
        
        model.addAttribute("heroVillain", heroVillain);
        model.addAttribute("organizations", organizations);
        model.addAttribute("superpowers", superpowers);
        return "editHeroVillain";
    }

    @GetMapping("heroVillainImage/{heroVillainID}")
    public void displayHeroVillainImage(HttpServletResponse response, @PathVariable Integer heroVillainID) throws IOException {
        HeroVillain heroVillain = service.getHeroVillainByID(heroVillainID);
        StreamUtils.copy(heroVillain.getImage(), response.getOutputStream());
    }

    @GetMapping("deleteHeroVillain")
    public String deleteHeroVillain(HeroVillain heroVillain) {
        service.deleteHeroVillainByID(heroVillain.getHeroVillainID());
        return "redirect:/heroVillains";
    }
}
