package infoshare.restapi.demographics;

import infoshare.app.conf.RestUtil;
import infoshare.domain.demographics.Gender;

import java.util.Set;

/**
 * Created by Songezo on 2016-03-03.
 */
public class GenderAPI {
    public static Gender save(Gender entity){
        return RestUtil.save(DemographicsBaseUrl.Gender.POST, entity, Gender.class);
    }

    public static Gender findById(String id){
        return RestUtil.getById(DemographicsBaseUrl.Gender.GET_ID, id, Gender.class);
    }

    public static Set<Gender> findAll(){
        return RestUtil.getAll(DemographicsBaseUrl.Gender.GET_ALL, Gender.class);
    }
}
