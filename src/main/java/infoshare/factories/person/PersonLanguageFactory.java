package infoshare.factories.person;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.person.PersonLanguage;

import java.util.Date;

/**
 * Created by user9 on 2016/03/02.
 */
public class PersonLanguageFactory {
    public static PersonLanguage getPersonLanguage(
            String personId,
            String languageId, String reading, String writing, String speaking){
        PersonLanguage personLanguage = new  PersonLanguage.Builder()
                .id(KeyGenerator.getEntityId())
                .personId(personId)
                .languageId(languageId)
                .reading(reading)
                .writing(writing)
                .speaking(speaking)
                .date(new Date())
                .state(DomainState.ACTIVE.name())
                .build();
        return personLanguage;
    }
}
