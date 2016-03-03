package infoshare.factories.demographics;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.demographics.LanguageProficiency;

import java.util.Map;

/**
 * Created by Songezo on 2016-03-02.
 */
public class LanguageProficiencyFactory {
    public static LanguageProficiency getlanguageProficiency (Map<String, String> languageProfiencyVals){
        LanguageProficiency languageProficiency = new LanguageProficiency.Builder()
                .id(KeyGenerator.getEntityId())
                .name(languageProfiencyVals.get("name"))
                .state(DomainState.ACTIVE.name())
                .build();
        return languageProficiency;
    }
}
