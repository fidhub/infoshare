package infoshare.restapi.Organisation;

import infoshare.app.conf.RestUtil;
import infoshare.domain.organisation.Organisation;

import java.util.Set;

/**
 * Created by user9 on 2016/03/03.
 */
public class OrganisationAPI {
    public static Organisation save(Organisation entity){
        return RestUtil.save(OrganisationBaseUrl.Organisation.POST,entity,Organisation.class);
    }
<<<<<<< HEAD
    public static Organisation update(Organisation entity){
        return RestUtil.save(OrganisationBaseUrl.Organisation.POST,entity,Organisation.class);
    }
=======
>>>>>>> e5d284111b73c308d9cc6b33b7bed19f1a4d34b9

    public static Organisation findById(String org){
        return RestUtil.getById(OrganisationBaseUrl.Organisation.GET_ID,org,Organisation.class);
    }
    public static Set<Organisation> findAll(){
        return RestUtil.getAll(OrganisationBaseUrl.Organisation.GET_ALL,Organisation.class);
    }
}
