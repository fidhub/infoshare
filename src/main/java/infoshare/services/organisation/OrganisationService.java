/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoshare.services.organisation;


import infoshare.domain.organisation.Organisation;
import infoshare.services.Services;

import java.util.Set;

/**
 * @author BONGANI
 */
public interface OrganisationService extends Services<Organisation, String> {
    Set<Organisation> getActiveOrganisations();

    Set<Organisation> getRetiredOrganisations();

    String getOrganisationCode();

}
