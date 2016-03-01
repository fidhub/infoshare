package infoshare.factories.organasation;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.organisation.OrganisationLogo;
import jdk.nashorn.internal.runtime.options.Option;

import java.util.Date;
import java.util.Map;

/**
 * Created by user9 on 2016/03/01.
 */
public class OrganisationLogoFactory {

    public static OrganisationLogo getOrganisationLogo(Map<String, String> organisationLogoVals, Date date, Option<String> size){
        OrganisationLogo organisationLogo = new OrganisationLogo.Builder()
                .id(KeyGenerator.getEntityId())
                .org(organisationLogoVals.get("org"))
                .url(organisationLogoVals.get("url"))
                .size(size)
                .description(organisationLogoVals.get("description"))
                .mime(organisationLogoVals.get("mime"))
                .date(date)
                .build();
        return organisationLogo;
    }
}
