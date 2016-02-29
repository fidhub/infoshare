package infoshare.restapi.people;


import infoshare.app.conf.RestUtil;
import infoshare.domain.person.PersonRole;

import java.util.Set;

/**
 * Created by hashcode on 2015/11/17.
 */
public class PersonRoleAPI {
    public static PersonRole save(PersonRole personRole) {
        RestUtil.save(PersonBaseURI.PersonRole.POST, personRole, PersonRole.class);
        return personRole;
    }

    public static PersonRole findById(String personId, String roleId) {
        String param = personId + "/" + roleId;
        return RestUtil.getById(PersonBaseURI.PersonRole.GET_ID, param, PersonRole.class);
    }

    public static Set<PersonRole> findAll(String personId) {
        return RestUtil.getAll(PersonBaseURI.PersonRole.GETALL + personId, PersonRole.class);
    }

}
