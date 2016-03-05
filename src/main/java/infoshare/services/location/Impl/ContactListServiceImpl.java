package infoshare.services.location.Impl;


import infoshare.domain.location.ContactType;
import infoshare.restapi.common.location.ContactTypeAPI;
import infoshare.services.location.ContactListService;

import java.util.Set;

/**
 * Created by garran on 2015/09/06.
 */
public class ContactListServiceImpl implements ContactListService {

    @Override
    public ContactType findById(String id) {
        return ContactTypeAPI.findById(id);
    }

    @Override
    public ContactType save(ContactType entity) {
        return ContactTypeAPI.save(entity);
    }

    @Override
    public ContactType update(ContactType entity) {
        return ContactTypeAPI.save(entity);
    }

    @Override
    public void delete(ContactType entity) {
        ContactTypeAPI.save(entity);

    }

    @Override
    public Set<ContactType> findAll() {
        return ContactTypeAPI.findAll();
    }

}
