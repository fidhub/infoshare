package infoshare.restapi.people;

import infoshare.app.conf.RestUtil;
import infoshare.domain.person.PersonContact;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
public class PersonContactAPI {

    public static PersonContact save(PersonContact person) {
      return RestUtil.save(PersonBaseURI.PersonContact.POST, person, PersonContact.class);
    }

    public static PersonContact findById(String personId, String id) {
        String param =personId + "/" + id;
        return RestUtil.getById(PersonBaseURI.PersonContact.GET_ID, param, PersonContact.class);
    }

    public static Set<PersonContact> findALL(String org ) {
        return RestUtil.getAll(PersonBaseURI.PersonContact.GET_ALL +org , PersonContact.class);
    }
}
