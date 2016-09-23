package infoshare.factories.location;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.location.AddressType;

/**
 * Created by user9 on 2016/03/01.
 */
public class AddressTypeFactory {
    public static AddressType getAddressType(String name){
        AddressType addressType = new AddressType.Builder()
                .id(KeyGenerator.getEntityId())
                .name(name)
                .state(DomainState.ACTIVE.name())
                .build();
        return addressType;
    }
}
