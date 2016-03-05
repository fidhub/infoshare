package infoshare.services.organisation;

import infoshare.domain.organisation.OrganisationLogo;

import java.util.Set;

/**
 * Created by hashcode on 2016/01/06.
 */
public interface OrganisationLogoService {
    OrganisationLogo save(OrganisationLogo OrganisationLogo);

    OrganisationLogo getOrganisationLogo(String company, String id);

    Set<OrganisationLogo> getAllOrganisationLogos(String param);
}
