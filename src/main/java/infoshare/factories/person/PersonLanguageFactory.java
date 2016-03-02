package infoshare.factories.person;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.person.PersonLanguage;

import java.util.Date;
import java.util.Map;

/**
 * Created by user9 on 2016/03/02.
 */
public class PersonLanguageFactory {
    public static PersonLanguage getPersonLanguage(Map<String, String> personLanguageVals, Date date){
        PersonLanguage personLanguage = new  PersonLanguage.Builder()
                .id(KeyGenerator.getEntityId())
                .personId(personLanguageVals.get("PersonId"))
                .languageId(personLanguageVals.get("languageId"))
                .reading(personLanguageVals.get("reading"))
                .writing(personLanguageVals.get("writing"))
                .speaking(personLanguageVals.get("speaking"))
                .date(date)
                .state(DomainState.ACTIVE.name())
                .build();
        return personLanguage;
    }
}
