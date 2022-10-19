/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.entities.Address;
import com.sg.superherosightings.entities.HeroVillain;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Superpower;
import com.sg.superherosightings.service.SuperheroSightingsServiceLayer;
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
public class OrganizationController {

    @Autowired
    SuperheroSightingsServiceLayer service;

    Set<ConstraintViolation<Organization>> organizationViolations = new HashSet<>();
    Set<ConstraintViolation<Address>> addressViolations = new HashSet<>();
    
    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = service.getAllOrganizations();
        List<HeroVillain> heroVillains = service.getAllHeroVillains();
        model.addAttribute("organizations", organizations);
        model.addAttribute("heroVillains", heroVillains);
        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request, Address address, Organization organization, Model model) {
        String[] heroVillainIDs = request.getParameterValues("heroVillainIDs");
        List<HeroVillain> members = new ArrayList<>();

        if (heroVillainIDs != null) {
            for (String heroVillainID : heroVillainIDs) {
                int id = Integer.parseInt(heroVillainID);
                members.add(service.getHeroVillainByID(id));
            }
        }

        organization.setMembers(members);
        organization.setAddress(address);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        organizationViolations = validate.validate(organization);
        addressViolations = validate.validate(address);
        
        if (!organizationViolations.isEmpty() || !addressViolations.isEmpty()) {
            List<Organization> organizations = service.getAllOrganizations();
            List<HeroVillain> heroVillains = service.getAllHeroVillains();
            model.addAttribute("organizationErrors", organizationViolations);
            model.addAttribute("addressErrors", addressViolations);
            model.addAttribute("organizations", organizations);
            model.addAttribute("heroVillains", heroVillains);
            model.addAttribute("badOrganization", organization);
            
            return "organizations";
        }

        service.addOrganization(organization);
        return "redirect:/organizations";
    }

    @GetMapping("editOrganization")
    public String editOrganization(Integer organizationID, Model model) {
        Organization organization = service.getOrganizationByID(organizationID);
        List<HeroVillain> heroVillains = service.getAllHeroVillains();
        List<Superpower> superpowers = service.getAllSuperpowers();

        for (HeroVillain heroVillain : heroVillains) {
            heroVillain.setOrganizations(new ArrayList<>());
            heroVillain.setLocations(new ArrayList<>());
        }
        model.addAttribute("heroVillains", heroVillains);
        model.addAttribute("organization", organization);
        model.addAttribute("superpowers", superpowers);
        return "editOrganization";
    }

    @PostMapping("editOrganization")
    public String updateOrganization(Organization organization, Address address, HttpServletRequest request, Model model) {
        String[] heroVillainIDs = request.getParameterValues("heroVillainIDs");
        List<HeroVillain> members = new ArrayList<>();

        if (heroVillainIDs != null) {
            for (String heroVillainID : heroVillainIDs) {
                int id = Integer.parseInt(heroVillainID);
                members.add(service.getHeroVillainByID(id));
            }
        }

        organization.setMembers(members);
        organization.setAddress(address);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        organizationViolations = validate.validate(organization);
        addressViolations = validate.validate(address);
        
        if (!organizationViolations.isEmpty() || !addressViolations.isEmpty()) {
            List<Organization> organizations = service.getAllOrganizations();
            List<HeroVillain> heroVillains = service.getAllHeroVillains();
            model.addAttribute("organizationErrors", organizationViolations);
            model.addAttribute("addressErrors", addressViolations);
            model.addAttribute("organizations", organizations);
            model.addAttribute("heroVillains", heroVillains);
            model.addAttribute("badOrganization", organization);
            
            return "editOrganization";
        }
        
        service.updateOrganization(organization);
        return "redirect:/organizations";
    }

    @GetMapping("organizationDetail")
    public String organizationDetail(Integer organizationID, Model model) {
        Organization organization = service.getOrganizationByID(organizationID);
        model.addAttribute("organization", organization);
        return "organizationDetail";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(Integer organizationID, Model model) {
        Organization organization = service.getOrganizationByID(organizationID);
        model.addAttribute("organization", organization);
        return "deleteOrganization";
    }

    @PostMapping("deleteOrganization")
    public String confirmDeleteOrganization(Integer organizationID) {
        service.deleteOrganizationByID(organizationID);
        return "redirect:/organizations";
    }
}
