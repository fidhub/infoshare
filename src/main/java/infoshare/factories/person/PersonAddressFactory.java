package infoshare.factories.person;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.person.PersonAddress;

import java.util.Date;

/**
 * Created by codet on 2016/02/23.
 */
public class PersonAddressFactory {

    public static PersonAddress getAddress(String personId, String description, String postalCode, String addressTypeId){
        PersonAddress personAddress = new PersonAddress.Builder()
                .id(KeyGenerator.getEntityId())
                .personId(personId)
                .description(description)
                .date(new Date())
                .state(DomainState.ACTIVE.name())
                .postalCode(postalCode)
                .addressTypeId(addressTypeId)
                .build();
        return personAddress;
    }
}
