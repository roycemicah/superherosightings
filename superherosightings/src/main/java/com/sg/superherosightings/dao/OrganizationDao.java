/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Organization;
import java.util.List;

/**
 *
 * @author roycerabanal
 */
public interface OrganizationDao {
    
    public Organization getOrganizationByID(int organizationID);
    public List<Organization> getAllOrganizations();
    public Organization addOrganization(Organization organization);
    public void updateOrganization(Organization organization);
    public void deleteOrganizationByID(int organizationID);
    
}
