package infoshare.factories.common;


import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.demographics.Role;

/**
 * Created by zamzam on 15/08/22.
 */
public class RolesListFactory {
    public static Role getRolesList(String roleName, String description) {
        Role role = new Role
                .Builder()
                .id(roleName)//TODO
                .roleName(roleName)
                .state(DomainState.ACTIVE.name())
                .description(description)
                .build();
        return role;
    }
}
