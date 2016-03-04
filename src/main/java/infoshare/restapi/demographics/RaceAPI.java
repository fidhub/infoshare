package infoshare.restapi.demographics;

import infoshare.app.conf.RestUtil;
import infoshare.domain.demographics.Race;

import java.util.Set;

/**
 * Created by Songezo on 2016-03-03.
 */
public class RaceAPI {
    public static Race save(Race entity){
        return RestUtil.save(DemographicsBaseUrl.Race.POST, entity, Race.class);
    }

    public static Race findById(String id){
        return RestUtil.getById(DemographicsBaseUrl.Race.GET_ID, id, Race.class);
    }

    public static Set<Race> findAll(){
        return RestUtil.getAll(DemographicsBaseUrl.Race.GET_ALL, Race.class);
    }
}
