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

    public static Organisation getOrganisation(Map<String, String> organisationVals, Map<String, String> details, Date date){
        Organisation organisation = new Organisation.Builder()
                .id(KeyGenerator.getEntityId())
                .name(organisationVals.get("name"))
                .details(details)
                .adminattached(organisationVals.get("adminattached"))
                .date(date)
                .state(DomainState.ACTIVE.name())
                .build();
        return organisation;
    }
}
