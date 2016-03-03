package infoshare.restapi.location;

import infoshare.app.conf.RestUtil;
import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.location.AddressType;

import java.util.Map;
import java.util.Set;

/**
 * Created by user9 on 2016/03/01.
 */
public class AddressTypeAPI {

    public static AddressType save(AddressType entity){
        return RestUtil.save(LocationBaseUrl.AddressType.POST,entity,AddressType.class);
    }
    public static AddressType findById(String id){
        return RestUtil.getById(LocationBaseUrl.AddressType.GET_ID,id,AddressType.class);
    }
    public static Set<AddressType> findAll(){
        return RestUtil.getAll(LocationBaseUrl.AddressType.GET_ALL,AddressType.class);
    }
}
