package infoshare.services.people;

import infoshare.domain.person.PersonDemographics;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
public interface PersonDemographicsService {
    PersonDemographics save(PersonDemographics entity);
    PersonDemographics findById(String personId,String id);
    Set<PersonDemographics> find_ALL(String personId);
}
