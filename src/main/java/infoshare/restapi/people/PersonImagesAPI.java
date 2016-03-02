package infoshare.restapi.people;

import infoshare.app.conf.RestUtil;
import infoshare.domain.person.PersonImages;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
public class PersonImagesAPI {
    public static PersonImages save(PersonImages person) {
        RestUtil.save(PersonBaseURI.PersonImages.POST, person, PersonImages.class);
        return person;
    }

    public static PersonImages findById(String org,String personId, String id) {
        String param = org+"/"+personId + "/" + id;
        return RestUtil.getById(PersonBaseURI.PersonImages.GET_ID, param, PersonImages.class);
    }

    public static Set<PersonImages> findByPersonId(String org ,String id) {
        String param = org+ "/" + id;
        return RestUtil.getAll(PersonBaseURI.PersonImages.GET_PID +param , PersonImages.class);
    }

    public static Set<PersonImages> findAll(String org) {
        return RestUtil.getAll(PersonBaseURI.PersonImages.GET_BY_ORG+org, PersonImages.class);
    }
}
