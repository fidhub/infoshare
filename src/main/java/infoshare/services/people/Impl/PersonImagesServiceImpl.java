package infoshare.services.people.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.person.PersonImages;
import infoshare.restapi.people.PersonImagesAPI;
import infoshare.services.people.PersonImagesService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
@Service
@SpringComponent
public class PersonImagesServiceImpl implements PersonImagesService {
    private static PersonImagesServiceImpl personImagesService =null;

    private PersonImagesServiceImpl(){}

    public  static PersonImagesServiceImpl getInstance(){
        if(personImagesService ==null) {
            return new PersonImagesServiceImpl();
        }
        return personImagesService;
    }
    @Override
    public PersonImages save(PersonImages entity) {
        return PersonImagesAPI.save(entity);
    }

    @Override
    public PersonImages update(PersonImages entity) {
        return PersonImagesAPI.save(entity);
    }

    @Override
    public void delete(PersonImages entity) {
        PersonImagesAPI.save(entity);
    }

    @Override
    public PersonImages findById(String org, String personId, String id) {
        return PersonImagesAPI.findById(org,personId,id);
    }

    @Override
    public Set<PersonImages> findByPersonId(String org, String personId) {
        return PersonImagesAPI.findByPersonId(org,personId);
    }

    @Override
    public Set<PersonImages> findAll(String org) {
        return PersonImagesAPI.findAll(org);
    }
}
