package infoshare.restapi.organisation;


import infoshare.app.conf.RestUtil;
import infoshare.domain.organisation.Organisation;

import java.util.Set;

/**
 * Created by hashcode on 2015/11/15.
 */
public class OrganisationAPI {

    public static Organisation save(Organisation organisation) {
        RestUtil.save(OrganisationBaseURI.Organisation.POST, organisation, Organisation.class);
        return organisation;
    }

    public static Organisation findById(String id) {
        return RestUtil.getById(OrganisationBaseURI.Organisation.GET_ID, id, Organisation.class);

    }

    public static Set<Organisation> findAll() {
        return RestUtil.getAll(OrganisationBaseURI.Organisation.GETALL, Organisation.class);
    }
}
