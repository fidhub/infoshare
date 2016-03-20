package infoshare.client.content.users.table;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.OrganisationFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.app.util.security.RolesValues;
import infoshare.client.content.MainLayout;
import infoshare.domain.organisation.Organisation;
import infoshare.domain.person.Person;
import infoshare.domain.person.PersonRole;
import org.apache.poi.ss.formula.functions.T;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/10/22.
 */
public class UsersTable extends Table {
    private final MainLayout main;


    public UsersTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Last Name", String.class, null);
        addContainerProperty("First Name", String.class, null);
        addContainerProperty("Email Address", String.class, null);
        addContainerProperty("Enabled", Boolean.class, null);
        addContainerProperty("Details", Button.class, null);
        addContainerProperty("Reset Credentials", Button.class, null);
        addContainerProperty("Disable Account", Button.class, null);
        try {
        Set<Person> applicants;
        if(GetUserCredentials.isUserWithRole(RolesValues.ROLE_ADMIN.name())) {

                applicants = getUsers(RolesValues.ORG_ADMIN.name());

        }else if(GetUserCredentials.isUserWithRole(RolesValues.ORG_ADMIN.name())) {
            applicants = PeopleFacade.personService.getPersonByCompany(OrganisationUtil.getCompanyCode());
        } else {
            applicants = PeopleFacade.personService.getPersonByCompany(OrganisationUtil.getCompanyCode()).stream()
                    .filter(person -> GetUserCredentials.isUserWithRole(PeopleFacade.personRoleService
                            .findPersonRoles(OrganisationUtil.getPersonID())
                            .iterator().next().getRoleId()))
                    .collect(Collectors.toSet());
        }
        applicants.parallelStream().forEach(item -> {
            Button details = new Button("Details");
            details.setStyleName(ValoTheme.BUTTON_LINK);
            //details.setStyleName(ValoTheme.BUTTON_BORDERLESS);
            //details.setStyleName(ValoTheme.BUTTON_FRIENDLY);
            details.setData(item.getId());
           // details.setIcon(FontAwesome.ENVELOPE_O);
            details.addClickListener(event -> {

            });

            Button reset = new Button("Reset Account");
            reset.setStyleName(ValoTheme.BUTTON_LINK);
            //reset.setStyleName(ValoTheme.BUTTON_FRIENDLY);
            //reset.setStyleName(ValoTheme.BUTTON_BORDERLESS);
            //reset.setIcon(FontAwesome.REFRESH);
            reset.setData(item.getId());
            reset.addClickListener(event -> {

            });

            Button disable = new Button("Disable");
            disable.setStyleName(ValoTheme.BUTTON_LINK);
            //disable.setStyleName(ValoTheme.BUTTON_BORDERLESS);
            //disable.setIcon(FontAwesome.REMOVE);
            //disable.setStyleName(ValoTheme.BUTTON_DANGER);

            disable.setData(item.getId());
            disable.addClickListener(event -> {

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
        }catch (Exception e){
            Notification.show("They are no users", Notification.Type.HUMANIZED_MESSAGE);}
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }

    private Set<Person> getUsers(String role){
        return PeopleFacade.personService.getPersonByCompany(OrganisationUtil.getCompanyCode());
       /* return PeopleFacade.personService.getPersonByCompany(OrganisationFacade.companyService.getActiveOrganisations().iterator().next().getId())
                .stream().filter(per-> PeopleFacade.personRoleService.findPersonRoles(per.getId()).contains(role))
                .collect(Collectors.toSet());*/
    }
}
