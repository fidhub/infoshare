package infoshare.restapi.organisation;


import infoshare.app.conf.RestUtil;
import infoshare.domain.organisation.OrganisationLogo;

import java.util.Set;

/**
 * Created by hashcode on 2016/01/06.
 */
public class OrganisationLogoAPI {
    public static OrganisationLogo save(OrganisationLogo orgLogo) {
        RestUtil.save(OrganisationBaseURI.OrganisationLogo.POST, orgLogo, OrganisationLogo.class);
        return orgLogo;
    }

    public static OrganisationLogo getOrganisationLogo(String org, String id) {
        String param = org + "/" + id;
        return RestUtil.getById(OrganisationBaseURI.OrganisationLogo.GET_ID, param, OrganisationLogo.class);
    }

    public static Set<OrganisationLogo> getAllOrganisationLogos(String param) {
        return RestUtil.getAll(OrganisationBaseURI.OrganisationLogo.GETALL + param, OrganisationLogo.class);
    }
}
