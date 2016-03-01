package infoshare.factories.person;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.person.PersonAddress;

import java.util.Date;
import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class AddressFactory {

    /*
    private String id;
    private String personId;
    private String description;
    private Date date;
    private String state;
    private String postalCode;
    private String addressTypeId;
     */
    public static PersonAddress getAddress(Map<String, String> addressVals, Date date){
        PersonAddress personAddress = new PersonAddress.Builder()
                .id(KeyGenerator.getEntityId())
                .personId(addressVals.get("personId"))
                .description(addressVals.get("description"))
                .date(date)
                .state(DomainState.ACTIVE.name())
                .postalCode(addressVals.get("postalCode"))
                .addressTypeId(addressVals.get("addressTypeId"))
                .build();
        return personAddress;
    }
}
