package infoshare.factories.organasation;

import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.domain.organisation.OrganisationLogo;

import java.util.Date;
import java.util.Map;

/**
 * Created by user9 on 2016/04/08.
 */
public class OrganisationLogoFactory {
    /*
     private String org;
        private String id;
        private String url;
        private Option<String> size;
        private String description;
        private String mime;
        private Date date;
    * */
    public static OrganisationLogo getOrganisationLogo(Map<String,String> stringMap){
       OrganisationLogo logo = new OrganisationLogo.Builder()
               .id(stringMap.get("id"))
               .url(stringMap.get("url"))
               .size(stringMap.get("size"))
               .description(stringMap.get("description"))
               .mime(stringMap.get("mime"))
               .date(new Date())
               .org(OrganisationUtil.getCompanyCode())
               .build();
        return logo;
    }
}
