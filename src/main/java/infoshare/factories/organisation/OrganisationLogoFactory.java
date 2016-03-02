package infoshare.factories.organisation;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.organisation.OrganisationLogo;
import jdk.nashorn.internal.runtime.options.Option;

import java.util.Date;
import java.util.Map;

/**
 * Created by user9 on 2016/03/02.
 */
public class OrganisationLogoFactory {

    public static OrganisationLogo getOrganisationLogo(Map<String, String> organisationLogoVals, Option<String> size, Date date){
        OrganisationLogo organisationLogo = new OrganisationLogo.Builder()
                .org(organisationLogoVals.get("org"))
                .id(KeyGenerator.getEntityId())
                .url(organisationLogoVals.get("url"))
                .size(size)
                .description(organisationLogoVals.get("description"))
                .mime(organisationLogoVals.get("mime"))
                .date(date)
                .build();
        return organisationLogo;
    }
}
