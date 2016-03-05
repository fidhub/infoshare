package infoshare.client.content.profile.demographics.table;

import com.vaadin.ui.Table;
import hashwork.app.facade.DemographicsFacade;
import hashwork.app.facade.PeopleFacade;
import hashwork.app.util.security.GetUserCredentials;
import hashwork.client.content.MainLayout;
import hashwork.domain.people.PersonDemographics;
import hashwork.domain.ui.demographics.Gender;
import hashwork.domain.ui.demographics.MaritalStatus;

import java.util.Date;
import java.util.Set;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonDemographicsTable extends Table {
    private final MainLayout main;

    public PersonDemographicsTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        final String personId = GetUserCredentials.getUser().getId();

        addContainerProperty("Date Created", Date.class, null);
        addContainerProperty("Gender", String.class, null);
        addContainerProperty("Date of Birth", Date.class, null);
        addContainerProperty("Marital Status", String.class, null);
        addContainerProperty("Dependants", Integer.class, null);

        Set<PersonDemographics> personDemographicses = PeopleFacade.personDemographicsService.findAll(personId);

        personDemographicses.parallelStream().forEach(item -> {
            addItem(new Object[]{
                    item.getDate(),
                    gender(item.getGenderId()),
                    item.getDateOfBirth(),
                    maritalStatus(item.getMaritalStatusId()),
                    item.getNumberOfDependencies()
            }, item.getId());

        });
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }

    private String gender(String genderId) {
        Gender gender = DemographicsFacade.genderListService.findById(genderId);
        if (gender != null)
            return gender.getName();
        return "Type Not Set";

    }

    private String maritalStatus(String maritalStatusId) {
        MaritalStatus maritalStatus = DemographicsFacade.maritalStatusListService.findById(maritalStatusId);
        if (maritalStatus != null)
            return maritalStatus.getName();
        return "Type Not Set";
    }

}
