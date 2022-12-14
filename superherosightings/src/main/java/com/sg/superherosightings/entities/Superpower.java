/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.entities;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author roycerabanal
 */
public class Superpower {
    
    int superpowerID;
    
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 20, message = "Name must be less than 20 characters.")
    String name;
    
    @NotBlank(message = "Description must not be empty.")
    @Size(max = 100, message = "Description must be less than 100 characters.")
    String description;

    public int getSuperpowerID() {
        return superpowerID;
    }

    public void setSuperpowerID(int SuperpowerID) {
        this.superpowerID = SuperpowerID;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.superpowerID;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.description);
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
        final Superpower other = (Superpower) obj;
        if (this.superpowerID != other.superpowerID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.description, other.description);
    } 
    
}