package infoshare.services.Organisation.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.organisation.Organisation;
import infoshare.restapi.Organisation.OrganisationAPI;
import infoshare.services.Organisation.OrganisationService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/03/03.
 */
@Service
@SpringComponent
public class OrganisationServiceImpl implements OrganisationService {
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
        return null;
    }

    @Override
    public Set<Organisation> getActiveOrganisations() {
        return null;
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
