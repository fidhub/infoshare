package infoshare.services.organisation.Impl;

import infoshare.domain.organisation.OrganisationLogo;
import infoshare.restapi.organisation.OrganisationLogoAPI;
import infoshare.services.organisation.OrganisationLogoService;

import java.util.Set;

/**
 * Created by hashcode on 2016/01/06.
 */
public class OrganisationLogoServiceImpl implements OrganisationLogoService {

    @Override
    public OrganisationLogo save(OrganisationLogo organisationLogo) {
        return OrganisationLogoAPI.save(organisationLogo);
    }

    @Override
    public OrganisationLogo getOrganisationLogo(String org, String id) {
        return OrganisationLogoAPI.getOrganisationLogo(org, id);
    }

    @Override
    public Set<OrganisationLogo> getAllOrganisationLogos(String param) {
        return OrganisationLogoAPI.getAllOrganisationLogos(param);
    }
}
