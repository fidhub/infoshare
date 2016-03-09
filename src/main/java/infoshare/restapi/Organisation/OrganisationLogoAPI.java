package infoshare.restapi.Organisation;

import infoshare.app.conf.RestUtil;
import infoshare.domain.organisation.OrganisationLogo;

import java.util.Set;

/**
 * Created by user9 on 2016/03/03.
 */
public class OrganisationLogoAPI {
    public static OrganisationLogo save(OrganisationLogo entity){
        return RestUtil.save(OrganisationBaseUrl.OrganisationLogo.POST, entity, OrganisationLogo.class);
    }
    public static OrganisationLogo findById(String org,String id){
        return RestUtil.getById(OrganisationBaseUrl.OrganisationLogo.GET_ID,org+"/"+id,OrganisationLogo.class);
    }
    public static Set<OrganisationLogo> findAll(String org){
        return RestUtil.getAll(OrganisationBaseUrl.OrganisationLogo.GET_ALL+org,OrganisationLogo.class);
    }
}
