package infoshare.services.people.Impl;

import infoshare.domain.person.PersonContact;
import infoshare.restapi.people.PersonContactAPI;
import infoshare.services.people.PersonContactService;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
public class PersonContactServiceImpl implements PersonContactService {
    @Override
    public PersonContact save(PersonContact entity) {
        return PersonContactAPI.save(entity);
    }

    @Override
    public PersonContact update(PersonContact entity) {
        return PersonContactAPI.save(entity);
    }

    @Override
    public void delete(PersonContact entity) {
         PersonContactAPI.save(entity);
    }

    @Override
    public PersonContact findById(String personId, String id) {
        return PersonContactAPI.findById(personId,id);
    }

    @Override
    public Set<PersonContact> findAll(String personId) {
        return PersonContactAPI.findALL(personId);
    }
}
