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
    public static Organisation update(Organisation entity){
        return RestUtil.save(OrganisationBaseUrl.Organisation.POST,entity,Organisation.class);
    }

    public static Organisation findById(String org){
        return RestUtil.getById(OrganisationBaseUrl.Organisation.GET_ID,org,Organisation.class);
    }
    public static Set<Organisation> findAll(){
        return RestUtil.getAll(OrganisationBaseUrl.Organisation.GET_ALL,Organisation.class);
    }
}
