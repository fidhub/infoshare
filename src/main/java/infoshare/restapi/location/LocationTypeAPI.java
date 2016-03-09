package infoshare.restapi.location;

import infoshare.app.conf.RestUtil;
<<<<<<< HEAD
import infoshare.domain.location.LocationType;

=======
import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.location.AddressType;
import infoshare.domain.location.LocationType;

import java.util.Map;
>>>>>>> e5d284111b73c308d9cc6b33b7bed19f1a4d34b9
import java.util.Set;

/**
 * Created by user9 on 2016/03/01.
 */
public class LocationTypeAPI {
    public static LocationType save(LocationType entity){
        return RestUtil.save(LocationBaseUrl.LocationType.POST, entity, LocationType.class);
    }
    public static LocationType findById(String id){
        return RestUtil.getById(LocationBaseUrl.LocationType.GET_ID,id,LocationType.class);
    }
    public static Set<LocationType> findAll(){
        return RestUtil.getAll(LocationBaseUrl.LocationType.GET_ALL,LocationType.class);
    }
}
