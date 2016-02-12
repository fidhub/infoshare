package infoshare.services.Contact.Impl;

import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.domain.Contact;
import infoshare.services.Contact.ContactService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user9 on 2015/07/28.
 */
public class ContactServiceImpl implements ContactService {
    private static Map<String,Contact> stringMap = null;

    public ContactServiceImpl() {
        if(stringMap == null) {
            stringMap = new HashMap<>();

            Contact contact = new Contact.Builder("(+27)74-791-3196")
                    .email("thulehadebe@outlook.com")
                    .contactType("phone").id("1").build();
            stringMap.put(contact.getId(),contact);
        }
    }

    @Override
    public Contact find(String s) {
        //return stringMap.get(s);
        return RestApiConnectorClass.read(UrlPath.ContactLink.GET_ID, s, Contact.class);
    }

    @Override
    public Contact save(Contact entity) {
        return RestApiConnectorClass.create(UrlPath.ContactLink.POST,entity,Contact.class);
    }

    @Override
    public Contact merge(Contact entity) {
        stringMap.put(entity.getId(),entity);
        return stringMap.get(entity.getId());
    }

    @Override
    public void remove(Contact entity) {

    }

    @Override
    public List<Contact> findAll() {
        return new ArrayList<>(stringMap.values());
    }
}
