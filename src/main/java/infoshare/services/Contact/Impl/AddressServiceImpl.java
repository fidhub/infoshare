package infoshare.services.Contact.Impl;

import infoshare.domain.Address;
import infoshare.restapi.Contacts.AddressAPI;
import infoshare.services.Contact.AddressService;

import java.util.Set;

/**
 * Created by user9 on 2015/07/28.
 */
public class AddressServiceImpl implements AddressService {

    @Override
    public Address findById(String s) {
        return AddressAPI.findById(s);
    }

    @Override
    public Address save(Address entity) {
        return AddressAPI.save(entity);
    }

    @Override
    public Address update(Address entity) {
        return AddressAPI.save(entity);
    }

    @Override
    public void delete(Address entity) {
        AddressAPI.save(entity);
    }

    @Override
    public Set<Address> findAll() {
        return null;
    }
}
