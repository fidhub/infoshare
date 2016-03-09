package infoshare.services.Organisation;

<<<<<<< HEAD
=======
import infoshare.app.conf.RestUtil;
>>>>>>> e5d284111b73c308d9cc6b33b7bed19f1a4d34b9
import infoshare.domain.organisation.Organisation;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by user9 on 2016/03/03.
 */
@Component
public interface OrganisationService {
    Organisation save(Organisation entity);
    Organisation findById(String org);
    Set<Organisation> findAll();
<<<<<<< HEAD

    //TODO  end point for this
    Organisation update(Organisation entity);
    Set<Organisation> getRetiredOrganisations();
    Set<Organisation> getActiveOrganisations();
    void delete(Organisation company);
=======
>>>>>>> e5d284111b73c308d9cc6b33b7bed19f1a4d34b9
}
