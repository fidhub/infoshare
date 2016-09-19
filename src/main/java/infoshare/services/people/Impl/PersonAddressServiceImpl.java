package infoshare.services.people.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.person.PersonAddress;
import infoshare.restapi.people.PersonAddressAPI;
import infoshare.services.people.PersonAddressService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
@Service
@SpringComponent
public class PersonAddressServiceImpl implements PersonAddressService {
    private static PersonAddressServiceImpl personAddressService=null;

    private PersonAddressServiceImpl(){}

    public  static PersonAddressServiceImpl getInstance(){
        if(personAddressService==null) {
            return new PersonAddressServiceImpl();
        }
        return personAddressService;
    }
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
