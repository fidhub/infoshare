package infoshare.restapi.common.location;

import hashwork.app.conf.RestUtil;
import hashwork.domain.ui.location.AddressType;
import hashwork.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/12/10.
 */
public class AddressTypeAPI {
    public static AddressType save(AddressType addressType) {
        RestUtil.save(CommonBaseURI.AddressType.POST, addressType, AddressType.class);
        return addressType;
    }

    public static AddressType findById(String id) {
        return RestUtil.getById(CommonBaseURI.AddressType.GET_ID, id, AddressType.class);

    }

    public static Set<AddressType> findAll() {
        return RestUtil.getAll(CommonBaseURI.AddressType.GETALL, AddressType.class);
    }
}
