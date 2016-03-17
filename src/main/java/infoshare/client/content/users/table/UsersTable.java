package infoshare.client.content.users.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.app.util.security.RolesValues;
import infoshare.client.content.MainLayout;
import infoshare.domain.person.Person;

import java.util.Set;

/**
 * Created by hashcode on 2015/10/22.
 */
public class UsersTable extends Table {
    private final MainLayout main;


    public UsersTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        addContainerProperty("Last Name", String.class, null);
        addContainerProperty("First Name", String.class, null);
        addContainerProperty("Email Address", String.class, null);
        addContainerProperty("Enabled", Boolean.class, null);
        addContainerProperty("Details", Button.class, null);
        addContainerProperty("Reset Credentials", Button.class, null);
        addContainerProperty("Disable Account", Button.class, null);

        Set<Person> applicants;
        if(GetUserCredentials.isUserWithRole(RolesValues.ROLE_ADMIN.name())) {

            applicants = PeopleFacade.personService.getPersonsWithRole(OrganisationUtil.getPersonID(), RolesValues.ORG_ADMIN.name());
        }else if(GetUserCredentials.isUserWithRole(RolesValues.ORG_ADMIN.name())) {
            applicants = PeopleFacade.personService.getPersonByCompany(OrganisationUtil.getCompanyCode());
        } else if(GetUserCredentials.isUserWithRole(RolesValues.ROLE_EDITOR.name())) {
            applicants = PeopleFacade.personService.getPersonsWithRole(OrganisationUtil.getPersonID(), RolesValues.ROLE_EDITOR.name());
        } else {
            applicants = PeopleFacade.personService.getPersonsWithRole(OrganisationUtil.getPersonID(), RolesValues.ROLE_PUBLISHER.name());
        }

        applicants.parallelStream().forEach(item -> {
            Button details = new Button("Details");
            details.setStyleName(ValoTheme.BUTTON_LINK);
            details.setData(item.getId());
            details.addClickListener(event -> {

            });

            Button reset = new Button("Reset Account");
            details.setStyleName(ValoTheme.BUTTON_LINK);
            details.setData(item.getId());
            details.addClickListener(event -> {

            });

            Button disable = new Button("Disable");
            details.setStyleName(ValoTheme.BUTTON_LINK);
            details.setData(item.getId());
            details.addClickListener(event -> {

            });

            addItem(new Object[]{
                    item.getLastName(),
                    item.getFirstName(),
                    item.getEmailAddress(),
                    item.getEnabled(),
                    details,
                    reset,
                    disable
            }, item.getId());

        });

        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }
}
