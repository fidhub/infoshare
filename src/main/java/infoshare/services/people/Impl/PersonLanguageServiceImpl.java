package infoshare.services.people.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.person.PersonLanguage;
import infoshare.restapi.people.PersonLanguageAPI;
import infoshare.services.people.PersonLanguageService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
@Service
@SpringComponent
public class PersonLanguageServiceImpl implements PersonLanguageService {
    @Override
    public PersonLanguage findById(String personId, String Id) {
        return PersonLanguageAPI.findById(personId,Id);
    }

    @Override
    public PersonLanguage save(PersonLanguage entity) {
        return PersonLanguageAPI.save(entity);
    }

    @Override
    public PersonLanguage update(PersonLanguage entity) {
        return PersonLanguageAPI.save(entity);
    }

    @Override
    public void delete(PersonLanguage entity) {
        PersonLanguageAPI.save(entity);
    }

    @Override
    public Set<PersonLanguage> getAllLanguages(String personId) {
        return PersonLanguageAPI.findALL(personId);
    }
}
