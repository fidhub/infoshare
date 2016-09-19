package infoshare.services.Organisation;

import infoshare.domain.organisation.OrganisationLogo;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by user9 on 2016/03/03.
 */
@Component
public interface OrganisationLogoService {
   OrganisationLogo save(OrganisationLogo entity);
   OrganisationLogo findById(String org,String id);
   Set<OrganisationLogo> findAll(String org);
}
