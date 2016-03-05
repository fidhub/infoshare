package infoshare.factories.location;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.location.LocationType;

/**
 * Created by user9 on 2016/03/01.
 */
public class LocationTypeFactory {

    public static LocationType getLocationType(String name, String code){
        LocationType locationType = new LocationType.Builder()
                .id(KeyGenerator.getEntityId())
                .name(name)
                .state(DomainState.ACTIVE.name())
                .code(code)
                .build();
        return locationType;
    }
}
