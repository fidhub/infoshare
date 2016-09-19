package infoshare.restApi;

import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.security.RolesValues;
import infoshare.domain.person.PersonRole;
import infoshare.factories.person.PersonRoleFactory;
import org.junit.Test;

/**
 * Created by THULEH on 2016/03/22.
 */
public class RoleTest {
    @Test
    public void testRole() throws Exception {
        PersonRole role = PersonRoleFactory.getPersonRole("f34a6f3ce71556e5bdd0d7fb7744d914", RolesValues.ORG_ADMIN.name());
        PeopleFacade.personRoleService.save(role);
    }
}
