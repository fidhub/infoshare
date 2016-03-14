package infoshare.services.people.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.person.PersonDemographics;
import infoshare.restapi.people.PersonDemographicsAPI;
import infoshare.services.people.PersonDemographicsService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
@Service
@SpringComponent
public class PersonDemographicsServiceImpl implements PersonDemographicsService{
    @Override
    public PersonDemographics save(PersonDemographics entity) {
        return PersonDemographicsAPI.save(entity);
    }

    @Override
    public PersonDemographics findById(String personId, String id) {
        return PersonDemographicsAPI.findById(personId,id);
    }

    @Override
    public Set<PersonDemographics> find_ALL(String personId) {
        return PersonDemographicsAPI.findALL(personId);
    }
}
