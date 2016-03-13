package infoshare.factories.organasation;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.organisation.Organisation;

import java.util.Date;
import java.util.Map;

/**
 * Created by user9 on 2016/03/01.
 */
public class OrganisationFactory {

    public static Organisation getOrganisation(String name,String code , Map<String, String> details){
        Organisation organisation = new Organisation.Builder()
                .id(code)
                .name(name)
                .details(details)
                .adminattached("ROLE_ORG_ADMIN")
                .date(new Date())
                .state(DomainState.ACTIVE.name())
                .build();
        return organisation;
    }
}
