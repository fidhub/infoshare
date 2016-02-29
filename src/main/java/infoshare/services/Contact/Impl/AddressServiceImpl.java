package infoshare.services.Contact.Impl;

import infoshare.domain.person.PersonAddress;
import infoshare.restapi.Contacts.AddressAPI;
import infoshare.services.Contact.AddressService;

import java.util.Set;

/**
 * Created by user9 on 2015/07/28.
 */
public class AddressServiceImpl implements AddressService {

    @Override
    public PersonAddress findById(String s) {
        return AddressAPI.findById(s);
    }

    @Override
    public PersonAddress save(PersonAddress entity) {
        return AddressAPI.save(entity);
    }

    @Override
    public PersonAddress update(PersonAddress entity) {
        return AddressAPI.save(entity);
    }

    @Override
    public void delete(PersonAddress entity) {
        AddressAPI.save(entity);
    }

    @Override
    public Set<PersonAddress> findAll() {
        return null;
    }
}
