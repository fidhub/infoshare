package infoshare.factories;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.Contact;

import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class ContactFactory {

    public static Contact getContact(Map<String,String> contactVals)
    {
        Contact contact = new Contact.Builder(contactVals.get("phone"))
                .id(KeyGenerator.getEntityId())
                .email(contactVals.get("email"))
                .contactType(contactVals.get("contactType"))
                .build();

        return contact;
    }
}
