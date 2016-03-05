package infoshare.client.content.profile.demographics.table;

import com.vaadin.ui.Table;
import hashwork.app.facade.DemographicsFacade;
import hashwork.app.facade.LocationFacade;
import hashwork.app.facade.PeopleFacade;
import hashwork.app.util.security.GetUserCredentials;
import hashwork.client.content.MainLayout;
import hashwork.domain.people.PersonLanguage;
import hashwork.domain.ui.demographics.Language;
import hashwork.domain.ui.demographics.LanguageProficiency;
import hashwork.domain.ui.location.ContactType;

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

        Set<PersonLanguage> personLanguages = PeopleFacade.personLanguageService.findAll(personId);

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

        Language language = DemographicsFacade.languageService.findById(languageId);
        if (language != null)
            return language.getName();
        return "Not Set";
    }

    private String languageProficiency(String languageProficiencId) {
        LanguageProficiency language = DemographicsFacade.languageProficiencyService.findById(languageProficiencId);
        if (language != null)
            return language.getName();
        return "Type Not Set";
    }

    private String contactType(String addressTypeId) {
        ContactType contactType = LocationFacade.contactListService.findById(addressTypeId);
        if (contactType != null)
            return contactType.getName();
        return "Type Not Set";
    }

}
