package infoshare.restapi.people;

import infoshare.app.conf.RestUtil;
import infoshare.domain.person.PersonLanguage;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
public class PersonLanguageAPI {

    public static PersonLanguage save(PersonLanguage person) {
        RestUtil.save(PersonBaseURI.PersonLanguage.POST, person, PersonLanguage.class);
        return person;
    }

    public static PersonLanguage findById(String personId, String id) {
        String param =personId + "/" + id;
        return RestUtil.getById(PersonBaseURI.PersonLanguage.GET_ID, param, PersonLanguage.class);
    }

    public static Set<PersonLanguage> findALL(String personId ) {

        return RestUtil.getAll(PersonBaseURI.PersonLanguage.GET_ALL +personId , PersonLanguage.class);
    }

}
