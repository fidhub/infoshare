package infoshare.factories.demographics;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.demographics.Role;

import java.util.Map;

/**
 * Created by Songezo on 2016-03-02.
 */
public class RoleFactory {
    public static Role getRole (Map<String, String> roleVals){
        Role role = new Role.Builder()
                .id(KeyGenerator.getEntityId())
                .roleName(roleVals.get("name"))
                .description(roleVals.get("description"))
                .state(DomainState.ACTIVE.name())
                .build();
        return role;

    }
}
