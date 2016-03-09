package infoshare.services.Contact.Impl;

import infoshare.domain.location.ContactType;
import infoshare.restapi.Contacts.ContactAPI;
import infoshare.services.Contact.ContactService;

import java.util.Set;

/**
 * Created by user9 on 2015/07/28.
 */
public class ContactServiceImpl implements ContactService {


    @Override
    public ContactType findById(String s) {
        return ContactAPI.findById(s);
    }

    @Override
    public ContactType save(ContactType entity) {
        return ContactAPI.save(entity);
    }

    @Override
    public ContactType update(ContactType entity) {
        return ContactAPI.save(entity);
    }

    @Override
    public void delete(ContactType entity) {
         ContactAPI.save(entity);
    }

    @Override
    public Set<ContactType> findAll() {
        return null;
    }
}
