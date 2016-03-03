package infoshare.services.Organisation;

import infoshare.app.conf.RestUtil;
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
}
