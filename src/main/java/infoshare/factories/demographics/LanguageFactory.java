package infoshare.factories.demographics;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.demographics.Language;

import java.util.Map;

/**
 * Created by Songezo on 2016-03-02.
 */
public class LanguageFactory {
    public static Language language (Map<String, String> languageVals){
        Language language = new Language.Builder()
                .id(KeyGenerator.getEntityId())
                .name(languageVals.get("name"))
                .state(DomainState.ACTIVE.name())
                .build();
        return language;
    }
}
