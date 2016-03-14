package infoshare.services.Organisation.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.organisation.OrganisationLogo;
import infoshare.restapi.Organisation.OrganisationLogoAPI;
import infoshare.services.Organisation.OrganisationLogoService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/03/03.
 */
@Service
@SpringComponent
public class OrganisationLogoServiceImpl implements OrganisationLogoService {
    @Override
    public OrganisationLogo save(OrganisationLogo entity) {
        return OrganisationLogoAPI.save(entity);
    }

    @Override
    public OrganisationLogo findById(String org, String id) {
        return OrganisationLogoAPI.findById(org, id);
    }

    @Override
    public Set<OrganisationLogo> findAll(String org) {
        return OrganisationLogoAPI.findAll(org);
    }
}
