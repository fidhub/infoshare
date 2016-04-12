package infoshare.client.content.profile.demographics.table;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.DemographicsFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.client.content.MainLayout;
import infoshare.domain.demographics.Gender;
import infoshare.domain.person.PersonDemographics;

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
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        final String personId = GetUserCredentials.getUser().getId();

        addContainerProperty("Date Created", Date.class, null);
        addContainerProperty("Gender", String.class, null);
        addContainerProperty("Date Of Birth", Date.class, null);
        addContainerProperty("Marital StatusId", String.class, null);
        addContainerProperty("Number Of Dependencies", Integer.class, null);


        Set<PersonDemographics> personDemographicses = PeopleFacade.personDemographicsService.find_ALL(personId);

        personDemographicses.parallelStream().forEach(item -> {
            addItem(new Object[]{
                    item.getDate(),
                    gender(item.getGenderId()),
                    item.getDateOfBirth(),
                    item.getMaritalStatusId(),
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



}
