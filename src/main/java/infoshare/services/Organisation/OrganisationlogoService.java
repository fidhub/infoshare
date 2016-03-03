package infoshare.services.Organisation;

import infoshare.app.conf.RestUtil;
import infoshare.domain.organisation.OrganisationLogo;
import infoshare.restapi.Organisation.OrganisationBaseUrl;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by user9 on 2016/03/03.
 */
@Component
public interface OrganisationlogoService {
   OrganisationLogo save(OrganisationLogo entity);
   OrganisationLogo findById(String org,String id);
   Set<OrganisationLogo> findAll(String org);
}
