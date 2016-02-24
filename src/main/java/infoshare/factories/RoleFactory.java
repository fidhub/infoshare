package infoshare.factories;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.Role;

import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class RoleFactory {

    public static Role getRole(Map<String,String> roleVlas){
        Role role = new Role.Builder()
                .id(KeyGenerator.getEntityId())
                .roleName(roleVlas.get("roleName"))
                .description(roleVlas.get("description"))
                .state(roleVlas.get("state"))
                .build();
        return role;
    }
}
