/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoshare.services.organisation.Impl;


import infoshare.app.util.DomainState;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.domain.organisation.Organisation;
import infoshare.restapi.organisation.OrganisationAPI;
import infoshare.services.organisation.OrganisationService;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author BONGANI
 */

public class OrganisationServiceImpl implements OrganisationService {


    @Override
    public Organisation findById(String id) {
        return OrganisationAPI.findById(id);
    }

    @Override
    public Organisation save(Organisation entity) {
        return OrganisationAPI.save(entity);
    }

    @Override
    public Organisation update(Organisation entity) {
        return OrganisationAPI.save(entity);
    }

    @Override
    public void delete(Organisation entity) {
        OrganisationAPI.save(entity);
    }

    @Override
    public Set<Organisation> findAll() {
        return OrganisationAPI.findAll();
    }

    @Override
    public Set<Organisation> getActiveOrganisations() {
        return findAll()
                .parallelStream()
                .filter(company -> company.getState().equalsIgnoreCase(DomainState.ACTIVE.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Organisation> getRetiredOrganisations() {
        return findAll()
                .parallelStream()
                .filter(company -> company.getState().equalsIgnoreCase(DomainState.RETIRED.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getOrganisationCode() {
        return GetUserCredentials.getUser().getOrg();
    }
}
