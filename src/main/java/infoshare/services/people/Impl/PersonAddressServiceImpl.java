package infoshare.services.people.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.person.PersonAddress;
import infoshare.domain.person.PersonContact;
import infoshare.restapi.people.PersonAddressAPI;
import infoshare.restapi.people.PersonBaseURI;
import infoshare.restapi.people.PersonContactAPI;
import infoshare.services.people.PersonAddressService;
import infoshare.services.people.PersonContactService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
@Service
@SpringComponent
public class PersonAddressServiceImpl implements PersonAddressService {
    @Override
    public PersonAddress save(PersonAddress entity) {
        return PersonAddressAPI.save(entity);
    }

    @Override
    public PersonAddress update(PersonAddress entity) {
        return PersonAddressAPI.save(entity);
    }

    @Override
    public void delete(PersonAddress entity) {
        PersonAddressAPI.save(entity);
    }

    @Override
    public PersonAddress findById(String personId, String id) {
        return PersonAddressAPI.findById(personId,id);
    }

    @Override
    public Set<PersonAddress> findAll(String personId) {
        return PersonAddressAPI.findAll(personId);
    }
}
