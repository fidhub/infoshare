package infoshare.restApi;

import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.security.PasswordHash;
import infoshare.app.util.security.RolesValues;
import infoshare.app.util.security.SecurityService;
import infoshare.domain.person.Person;
import infoshare.domain.person.PersonRole;
import infoshare.factories.person.PersonFactory;
import infoshare.factories.person.PersonRoleFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by THULEH on 2016/03/23.
 */
public class CreateUserTest {
    @Test
    public void testUser() throws Exception {
        final String password = SecurityService.generateRandomPassword();
        Map<String, String> stringVals = new HashMap<>();
        stringVals.put("firstName", "sanele");
        stringVals.put("middleName", "sora");
        stringVals.put("lastName", "zulu");
        stringVals.put("authvalue", PasswordHash.createEncryptedPassword(password));
        stringVals.put("emailAddress", "213033402@mycput.ac.za");
        stringVals.put("org", "dut");

        Map<String, Boolean> boolVals = new HashMap<>();
        boolVals.put("enabled", Boolean.TRUE);
        boolVals.put("accountNonExpired", Boolean.TRUE);
        boolVals.put("accountNonLocked", Boolean.TRUE);
        boolVals.put("credentialsNonExpired", Boolean.TRUE);
        Person companyAdmin = createAccount(stringVals, boolVals);
        SecurityService.sendEmail(password, companyAdmin);
    }
    private Person createAccount(Map<String, String> stringVals, Map<String, Boolean> boolVals) {
        Person companyAdmin = PersonFactory.getPerson(stringVals, boolVals);
        try {
            PeopleFacade.personService.save(companyAdmin);
            PersonRole role = PersonRoleFactory.getPersonRole(companyAdmin.getId(), RolesValues.ORG_ADMIN.name());
            PeopleFacade.personRoleService.save(role);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return companyAdmin;
    }
}
