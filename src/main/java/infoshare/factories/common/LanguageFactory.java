package infoshare.factories.common;


import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.demographics.Language;

/**
 * Created by zamzam on 15/08/22.
 */
public class LanguageFactory {
    public static Language getLanguage(String name) {
        Language language = new Language.Builder()
                .id(KeyGenerator.getEntityId())
                .state(DomainState.ACTIVE.name())
                .name(name).build();
        return language;
    }
}
