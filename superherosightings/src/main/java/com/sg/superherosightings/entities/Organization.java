/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author roycerabanal
 */
public class Organization {
    
    int organizationID;
    
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 30, message = "Name must be less than 30 characters.")
    String name;
    
    @NotBlank(message = "Description must not be empty.")
    @Size(max = 100, message = "Description must be less than 100 characters.")
    String description;
    
    Address address;
    
    @NotBlank(message = "Phone must not be empty.")
    @Size(max = 15, message = "Phone must be less than 15 characters.")
    String phone;
    
    @NotBlank(message = "Email must not be empty.")
    @Size(max = 50, message = "Email must be less than 50 characters.")
    String email;
    
    List<HeroVillain> members = new ArrayList<>(); 

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int OrganizationID) {
        this.organizationID = OrganizationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<HeroVillain> getMembers() {
        return members;
    }

    public void setMembers(List<HeroVillain> members) {
        this.members = members;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.organizationID;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.address);
        hash = 97 * hash + Objects.hashCode(this.phone);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.members);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organization other = (Organization) obj;
        if (this.organizationID != other.organizationID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return Objects.equals(this.members, other.members);
    }

}
