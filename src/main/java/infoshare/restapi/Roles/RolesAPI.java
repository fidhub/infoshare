package infoshare.restapi.Roles;

import infoshare.app.conf.RestUtil;
import infoshare.domain.Role;

import java.util.Set;

/**
 * Created by user9 on 2016/02/23.
 */
public class RolesAPI {

    public static Role save(Role role){
        return RestUtil.save(RolesBaseUrl.Role.POST, role, Role.class);
    }
    public static Role findById(String id){
        return RestUtil.getById(RolesBaseUrl.Role.GET,id,Role.class);
    }
    public static Role update(Role role){
        return RestUtil.update(RolesBaseUrl.Role.PUT,role);
    }
    public static Set<Role> findAll(){
        return RestUtil.getAll(RolesBaseUrl.Role.GET_ALL,Role.class);
    }
}
