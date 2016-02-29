package infoshare.factories;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.person.PersonAddress;

import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class AddressFactory {

    public static PersonAddress getAddress(Map<String,String> addressVals){
        PersonAddress personAddress = new PersonAddress.Builder()
                .id(KeyGenerator.getEntityId())
                .addressTypeId(addressVals.get("addressTypeId"))
                .postalCode(addressVals.get("postalCode"))
                .postalCode(addressVals.get("postalCode"))
                .build();
        return personAddress;
    }
}
