package infoshare.factories;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.Address;

import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class AddressFactory {

    public static Address getAddress(Map<String,String> addressVals){
        Address address = new Address.Builder(addressVals.get("physicalAddress"))
                .id(KeyGenerator.getEntityId())
                .postalAddress(addressVals.get("postalAddress"))
                .postalCode(addressVals.get("postalCode"))
                .addressType(addressVals.get("addressType"))
                .build();
        return address;
    }
}
