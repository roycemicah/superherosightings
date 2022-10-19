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
public class Address {
    @NotBlank(message = "Street Number must not be empty.")
    @Size(max = 10, message = "Street Number must be less than 10 characters.")
    String streetNumber;
    
    @NotBlank(message = "Street Name must not be empty.")
    @Size(max = 20, message = "Street Name must be less than 20 characters.")
    String streetName;
    
    @NotBlank(message = "City must not be empty.")
    @Size(max = 30, message = "City must be less than 30 characters.")
    String city;
    
    @NotBlank(message = "State / Province must not be empty.")
    @Size(max = 20, message = "State / Province must be less than 20 characters.")
    String stateProvince;
    
    @NotBlank(message = "ZIP / Postal Code must not be empty.")
    @Size(max = 12, message = "ZIP / Postal Code must be less than 12 characters.")
    String zipPostalCode;
    
    @NotBlank(message = "Country must not be empty.")
    @Size(max = 20, message = "Country must be less than 20 characters.")
    String country;

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.streetNumber);
        hash = 89 * hash + Objects.hashCode(this.streetName);
        hash = 89 * hash + Objects.hashCode(this.city);
        hash = 89 * hash + Objects.hashCode(this.stateProvince);
        hash = 89 * hash + Objects.hashCode(this.zipPostalCode);
        hash = 89 * hash + Objects.hashCode(this.country);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.streetNumber, other.streetNumber)) {
            return false;
        }
        if (!Objects.equals(this.streetName, other.streetName)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.stateProvince, other.stateProvince)) {
            return false;
        }
        if (!Objects.equals(this.zipPostalCode, other.zipPostalCode)) {
            return false;
        }
        return Objects.equals(this.country, other.country);
    }

}
