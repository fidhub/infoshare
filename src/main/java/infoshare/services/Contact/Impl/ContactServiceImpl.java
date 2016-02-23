package infoshare.services.Contact.Impl;

import infoshare.domain.Contact;
import infoshare.restapi.Contacts.ContactAPI;
import infoshare.services.Contact.ContactService;

import java.util.Set;

/**
 * Created by user9 on 2015/07/28.
 */
public class ContactServiceImpl implements ContactService {


    @Override
    public Contact findById(String s) {
        return ContactAPI.findById(s);
    }

    @Override
    public Contact save(Contact entity) {
        return ContactAPI.save(entity);
    }

    @Override
    public Contact update(Contact entity) {
        return ContactAPI.save(entity);
    }

    @Override
    public void delete(Contact entity) {
         ContactAPI.save(entity);
    }

    @Override
    public Set<Contact> findAll() {
        return null;
    }
}
