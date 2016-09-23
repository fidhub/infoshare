package infoshare.factories.organasation;

import infoshare.domain.organisation.OrganisationLogo;

import java.util.Date;
import java.util.Map;

/**
 * Created by user9 on 2016/04/08.
 */
public class OrganisationLogoFactory {
    public static OrganisationLogo getOrganisationLogo(Map<String,String> stringMap){
       OrganisationLogo logo = new OrganisationLogo.Builder()
               .id(stringMap.get("id"))
               .url(stringMap.get("url"))
               .size(stringMap.get("size"))
               .description(stringMap.get("description"))
               .mime(stringMap.get("mime"))
               .date(new Date())
               .org(stringMap.get("org"))
               .build();
        return logo;
    }
}
