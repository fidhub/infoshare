package infoshare.restapi.people;

import infoshare.app.conf.RestUtil;
import infoshare.domain.person.PersonDemographics;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
public class PersonDemographicsAPI {
    public static PersonDemographics save(PersonDemographics person) {
        return RestUtil.save(PersonBaseURI.PersonDemographics.POST, person, PersonDemographics.class);
    }

    public static PersonDemographics findById(String personId, String id) {
        String param =personId + "/" + id;
        return RestUtil.getById(PersonBaseURI.PersonDemographics.GET_ID, param, PersonDemographics.class);
    }

    public static Set<PersonDemographics> findALL(String personId ) {
        return RestUtil.getAll(PersonBaseURI.PersonDemographics.GET_ALL +personId , PersonDemographics.class);
    }
}
