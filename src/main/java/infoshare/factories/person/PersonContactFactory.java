package infoshare.factories.person;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.person.PersonContact;

import java.util.Date;

/**
 * Created by codet on 2016/02/23.
 */
public class PersonContactFactory {

    public static PersonContact getContact(String personId, String addressTypeId, String contactValue, String status)
    {
        PersonContact contact = new PersonContact.Builder()
                .id(KeyGenerator.getEntityId())
                .personId(personId)
                .addresstypeid(addressTypeId)
                .contactvalue(contactValue)
                .state(DomainState.ACTIVE.name())
                .date(new Date())
                .status(status)
                .build();

        return contact;
    }
}
