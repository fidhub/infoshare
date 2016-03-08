package infoshare.restapi.common.demographics;


import infoshare.app.conf.RestUtil;
import infoshare.domain.demographics.Gender;
import infoshare.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/11/10.
 */

public class GenderAPI {

    public static Gender save(Gender gender) {
        RestUtil.save(CommonBaseURI.Gender.POST, gender, Gender.class);
        return gender;
    }

    public static Gender findById(String id) {
        return RestUtil.getById(CommonBaseURI.Gender.GET_ID, id, Gender.class);

    }

    public static Set<Gender> findAll() {
        return RestUtil.getAll(CommonBaseURI.Gender.GETALL, Gender.class);
    }

}
