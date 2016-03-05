package infoshare.restapi.common.location;


import infoshare.app.conf.RestUtil;
import infoshare.domain.location.LocationType;
import infoshare.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/12/10.
 */
public class LocationTypeAPI {
    public static LocationType save(LocationType locationType) {
        RestUtil.save(CommonBaseURI.LocationType.POST, locationType, LocationType.class);
        return locationType;
    }

    public static LocationType findById(String id) {
        return RestUtil.getById(CommonBaseURI.LocationType.GET_ID, id, LocationType.class);

    }

    public static Set<LocationType> findAll() {
        return RestUtil.getAll(CommonBaseURI.LocationType.GETALL, LocationType.class);
    }
}
