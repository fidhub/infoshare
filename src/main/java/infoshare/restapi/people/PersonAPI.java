package infoshare.restapi.people;


import infoshare.app.conf.RestUtil;
import infoshare.domain.Person;

import java.util.Set;

/**
 * Created by hashcode on 2015/11/17.
 */
public class PersonAPI {
    public static Person save(Person person) {
        RestUtil.save(PersonBaseURI.Person.POST, person, Person.class);
        return person;
    }

    public static Person findById(String company, String id) {
        String param = company + "/" + id;
        return RestUtil.getById(PersonBaseURI.Person.GET_ID, param, Person.class);
    }

    public static Set<Person> findAll(String param) {
        return RestUtil.getAll(PersonBaseURI.Person.GETALL + param, Person.class);
    }

    public static Person findByEmail(String param) {
        return RestUtil.getById(PersonBaseURI.Person.GET_BY_EMAIL, param, Person.class);
    }


}
