package infoshare.restapi.people;


import infoshare.app.conf.RestUtil;
import infoshare.domain.person.PersonAddress;
import infoshare.domain.person.PersonRole;

import java.util.Set;

/**
 * Created by hashcode on 2015/11/17.
 */
public class PersonAddressAPI {
    public static PersonAddress save(PersonAddress personAddress) {
        RestUtil.save(PersonBaseURI.PersonAddress.POST, personAddress, PersonAddress.class);
        return personAddress;
    }

    public static PersonAddress findById(String personId, String Id) {
        String param = personId + "/" + Id;
        return RestUtil.getById(PersonBaseURI.PersonAddress.GET_ID, param, PersonAddress.class);
    }

    public static Set<PersonAddress> findAll(String personId) {
        return RestUtil.getAll(PersonBaseURI.PersonAddress.GETALL + personId, PersonAddress.class);
    }

}
