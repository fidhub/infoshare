package infoshare.factories.common;


import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.demographics.LanguageProficiency;

/**
 * Created by zamzam on 15/08/22.
 */
public class LanguageProficiencyFactory {
    public static LanguageProficiency getLanguageProficiency(String proficiency) {
        LanguageProficiency languageProficiency = new LanguageProficiency
                .Builder()
                .id(KeyGenerator.getEntityId())
                .state(DomainState.ACTIVE.name())
                .name(proficiency).build();
        return languageProficiency;
    }
}
