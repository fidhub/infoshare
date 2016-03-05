package infoshare.factories.location;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.location.ContactType;

/**
 * Created by user9 on 2016/03/01.
 */
public class ContactTypeFactory {
    public static ContactType contactType(String name){
        ContactType contactType = new ContactType.Builder()
                .id(KeyGenerator.getEntityId())
                .name(name)
                .state(DomainState.ACTIVE.name())
                .build();
        return contactType;

    }
}
