package infoshare.services.people;

import infoshare.domain.person.PersonImages;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
public interface PersonImagesService {
    PersonImages save(PersonImages entity);
    PersonImages update(PersonImages entity);
    void delete(PersonImages entity);
    PersonImages findById(String org,String personId,String id);
    Set<PersonImages> findByPersonId(String org, String personId);
    Set<PersonImages> findAll(String org);
}
