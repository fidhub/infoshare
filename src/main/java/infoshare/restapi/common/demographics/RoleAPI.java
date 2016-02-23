package infoshare.restapi.common.demographics;



import infoshare.app.conf.RestUtil;
import infoshare.domain.Role;
import infoshare.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/11/21.
 */
public class RoleAPI {
    public static Role save(Role role) {
        RestUtil.save(CommonBaseURI.Role.POST, role, Role.class);
        return role;
    }

    public static Role findById(String id) {
        return RestUtil.getById(CommonBaseURI.Role.GET_ID, id, Role.class);

    }

    public static Set<Role> findAll() {
        return RestUtil.getAll(CommonBaseURI.Role.GETALL, Role.class);
    }

}
