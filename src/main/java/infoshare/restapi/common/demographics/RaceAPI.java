package infoshare.restapi.common.demographics;


import infoshare.app.conf.RestUtil;
import infoshare.domain.demographics.Race;
import infoshare.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/12/10.
 */
public class RaceAPI {
    public static Race save(Race race) {
        RestUtil.save(CommonBaseURI.Race.POST, race, Race.class);
        return race;
    }

    public static Race findById(String id) {
        return RestUtil.getById(CommonBaseURI.Race.GET_ID, id, Race.class);

    }

    public static Set<Race> findAll() {
        return RestUtil.getAll(CommonBaseURI.Race.GETALL, Race.class);
    }
}
