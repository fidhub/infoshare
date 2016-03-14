package infoshare.client.content.profile.demographics.table;

import com.vaadin.ui.Table;
import infoshare.app.facade.DemographicsFacade;
import infoshare.app.facade.LocationFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.client.content.MainLayout;
import infoshare.domain.demographics.Language;
import infoshare.domain.demographics.LanguageProficiency;
import infoshare.domain.location.ContactType;
import infoshare.domain.person.PersonLanguage;

import java.util.Date;
import java.util.Set;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonLanguageTable extends Table {
    private final MainLayout main;

    public PersonLanguageTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        final String personId = GetUserCredentials.getUser().getId();
        addContainerProperty("Date Created", Date.class, null);
        addContainerProperty("Language", String.class, null);
        addContainerProperty("Reading", String.class, null);
        addContainerProperty("Writing", String.class, null);
        addContainerProperty("Speaking", String.class, null);

        Set<PersonLanguage> personLanguages = PeopleFacade.getPersonLanguageServiceInstance().getAllLanguages(personId);

        personLanguages.parallelStream().forEach(item -> {
            addItem(new Object[]{
                    item.getDate(),
                    language(item.getLanguageId()),
                    languageProficiency(item.getReading()),
                    languageProficiency(item.getWriting()),
                    languageProficiency(item.getSpeaking())
            }, item.getId());

        });
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }

    private String language(String languageId) {

        Language language = DemographicsFacade.getLanguageServiceInstance().findById(languageId);
        if (language != null)
            return language.getName();
        return "Not Set";
    }

    private String languageProficiency(String languageProficiencId) {
        LanguageProficiency language = DemographicsFacade.getLanguageProficiencyServiceInstance().findById(languageProficiencId);
        if (language != null)
            return language.getName();
        return "Type Not Set";
    }

    private String contactType(String addressTypeId) {
        ContactType contactType = LocationFacade.getContactTypeServiceInstance().findById(addressTypeId);
        if (contactType != null)
            return contactType.getName();
        return "Type Not Set";
    }

}
