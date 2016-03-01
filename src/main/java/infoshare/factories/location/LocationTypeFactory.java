package infoshare.factories.location;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.location.LocationType;

import java.util.Map;

/**
 * Created by user9 on 2016/03/01.
 */
public class LocationTypeFactory {

    public static LocationType getLocationType(Map<String,String> locationTypeVals){
        LocationType locationType = new LocationType.Builder()
                .id(KeyGenerator.getEntityId())
                .name(locationTypeVals.get("name"))
                .state(locationTypeVals.get("state"))
                .code(locationTypeVals.get("code"))
                .build();
        return locationType;
    }
}
