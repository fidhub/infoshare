package infoshare.restapi.demographics;

import infoshare.app.conf.RestUtil;
import infoshare.domain.demographics.Role;

import java.util.Set;

/**
 * Created by Songezo on 2016-03-03.
 */
public class RoleAPI {
    public static Role save(Role entity){
        return RestUtil.save(DemographicsBaseUrl.Roles.POST, entity, Role.class);
    }

    public static Role findById(String id){
        return RestUtil.getById(DemographicsBaseUrl.Roles.GET_ID, id, Role.class);
    }

    public static Set<Role> findAll(){
        return RestUtil.getAll(DemographicsBaseUrl.Roles.GET_ALL, Role.class);
    }
}
