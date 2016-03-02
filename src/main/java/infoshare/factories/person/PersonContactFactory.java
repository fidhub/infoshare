package infoshare.factories.person;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.person.PersonContact;

import java.util.Date;
import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class PersonContactFactory {

    public static PersonContact getContact(Map<String, String> contactVals, Date date)
    {
        PersonContact contact = new PersonContact.Builder()
                .id(KeyGenerator.getEntityId())
                .personId(contactVals.get("personId"))
                .addresstypeid(contactVals.get("addressTypeId"))
                .contactvalue(contactVals.get("contactValue"))
                .state(DomainState.ACTIVE.name())
                .date(date)
                .status(contactVals.get("status"))
                .build();

        return contact;
    }
}
