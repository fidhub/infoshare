package infoshare.services.people;

import infoshare.domain.person.PersonLanguage;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by user9 on 2016/03/02.
 */
@Component
public interface PersonLanguageService {
    PersonLanguage findById(String personId,String Id);
    PersonLanguage save(PersonLanguage entity);
    PersonLanguage update(PersonLanguage entity);
    void delete(PersonLanguage entity);
    Set<PersonLanguage> getAllLanguages(String personId);
}
