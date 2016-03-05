package infoshare.restapi.common.location;


import infoshare.app.conf.RestUtil;
import infoshare.domain.organisation.Location;
import infoshare.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2016/03/05.
 */
public class LocationAPI {
    public static Location save(Location company) {
        RestUtil.save(CommonBaseURI.Location.POST, company, Location.class);
        return company;
    }

    public static Location findById(String id) {
        return RestUtil.getById(CommonBaseURI.Location.GET_ID, id, Location.class);

    }

    public static Set<Location> findAll() {
        return RestUtil.getAll(CommonBaseURI.Location.GETALL, Location.class);
    }

}
