package infoshare.factories.location;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.location.AddressType;

import java.util.Map;

/**
 * Created by user9 on 2016/03/01.
 */
public class AddressTypeFactory {
    public static AddressType getAddressType(Map<String,String> addressTypeVals){
        AddressType addressType = new AddressType.Builder()
                .id(KeyGenerator.getEntityId())
                .name(addressTypeVals.get("name"))
                .state(addressTypeVals.get("state"))
                .build();
        return addressType;
    }
}
