package infoshare.services.Organisation.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.app.util.DomainState;
import infoshare.domain.organisation.Organisation;
import infoshare.restapi.Organisation.OrganisationAPI;
import infoshare.services.Organisation.OrganisationService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by user9 on 2016/03/03.
 */
@Service
@SpringComponent
public class OrganisationServiceImpl implements OrganisationService {

    private static OrganisationServiceImpl organisationService =null;

    private OrganisationServiceImpl(){}

    public  static OrganisationServiceImpl getInstance(){
        if(organisationService ==null) {
            return new OrganisationServiceImpl();
        }
        return organisationService;
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
    public Set<Organisation> getRetiredOrganisations() {
        return OrganisationAPI.findAll().stream().filter(cont->cont.getState().equalsIgnoreCase(DomainState.RETIRED.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Organisation> getActiveOrganisations() {
        return OrganisationAPI.findAll().stream().filter(cont->cont.getState().equalsIgnoreCase(DomainState.ACTIVE.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(Organisation company) {

    }

    @Override

    public Organisation findById(String org) {
        return OrganisationAPI.findById(org);
    }

    @Override
    public Set<Organisation> findAll() {
        return OrganisationAPI.findAll();
    }
}
