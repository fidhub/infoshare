package infoshare.factories.organisation;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.organisation.Location;

import java.util.Date;
import java.util.Map;

/**
 * Created by user9 on 2016/03/02.
 */
public class LocationFactory {
    public static Location getLocation(Map<String, String> locationVals, Date date){
        Location location = new Location.Builder()
                .org(locationVals.get("org"))
                .id(KeyGenerator.getEntityId())
                .locationtypeid(locationVals.get("locationTypeId"))
                .code(locationVals.get("code"))
                .latitude(locationVals.get("latitude"))
                .longitude(locationVals.get("longitude"))
                .parentid(locationVals.get("parentId"))
                .state(DomainState.ACTIVE.name())
                .date(date)
                .build();
        return location;
    }
}
