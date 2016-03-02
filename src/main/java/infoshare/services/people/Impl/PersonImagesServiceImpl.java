package infoshare.services.people.Impl;

import infoshare.domain.person.PersonImages;
import infoshare.restapi.people.PersonImagesAPI;
import infoshare.services.people.PersonImagesService;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
public class PersonImagesServiceImpl implements PersonImagesService {
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
