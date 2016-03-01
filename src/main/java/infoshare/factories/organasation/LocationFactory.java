package infoshare.factories.organasation;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.organisation.Location;

import java.util.Date;
import java.util.Map;

/**
 * Created by user9 on 2016/03/01.
 */
public class LocationFactory {

    public static Location getLocation(Map<String, String> locationVals, Date date){
        Location location = new Location.Builder()
                .id(KeyGenerator.getEntityId())
                .org(locationVals.get("org"))
                .name(locationVals.get("name"))
                .locationtypeid(locationVals.get("locationTypeId"))
                .code(locationVals.get("code"))
                .latitude(locationVals.get("latitude"))
                .longitude(locationVals.get("longitude"))
                .parentid(locationVals.get("parentId"))
                .state(locationVals.get("state"))
                .date(date)
                .build();
        return location;
    }
}
