package infoshare.client.content.users.table;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.OrganisationFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.DomainState;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.app.util.security.PasswordHash;
import infoshare.app.util.security.RolesValues;
import infoshare.app.util.security.SecurityService;
import infoshare.client.content.MainLayout;
import infoshare.client.content.account.views.ManageOrganisationTab;
import infoshare.client.content.account.views.OrganisationDetails;
import infoshare.client.content.users.UserManagementMenu;
import infoshare.client.content.users.forms.UserForm;
import infoshare.client.content.users.views.ActiveUsersTab;
import infoshare.client.content.users.views.UserDetails;
import infoshare.domain.organisation.Organisation;
import infoshare.domain.person.Person;
import infoshare.domain.person.PersonRole;
import infoshare.factories.person.PersonFactory;
import infoshare.restapi.people.PersonAPI;
import org.apache.poi.ss.formula.functions.T;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/10/22.
 */
public class UsersTable extends Table {
    private final MainLayout main;
    public UsersTable(MainLayout main, ActiveUsersTab tab) {
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
            applicants = PeopleFacade.personService.getPersonByCompany(OrganisationUtil.getCompanyCode())
                    .stream()
                    .filter(person -> person.getState().equalsIgnoreCase(DomainState.ACTIVE.name()))
                    .collect(Collectors.toSet());
        } else {
            applicants = getAll();
        }
        applicants.parallelStream().forEach(item -> {
            Button details = new Button("Details");
            details.setStyleName(ValoTheme.BUTTON_LINK);
            details.setData(item.getId());
            details.addClickListener(event -> {
                tab.contentPanel.removeAllComponents();
                tab.contentPanel.addComponent(new UserDetails(main, item, "LANDING"));
            });

            Button reset = new Button("Reset Account");
            reset.setStyleName(ValoTheme.BUTTON_LINK);
            reset.setData(item.getId());
            reset.addClickListener(event -> {
                final String password = SecurityService.generateRandomPassword();
                Person person = new Person.Builder()
                        .copy(item)
                        .state(DomainState.ACTIVE.name())
                        .authvalue(PasswordHash.createEncryptedPassword(password))
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .enabled(true)
                        .build();
                PeopleFacade.personService.update(person);
                SecurityService.sendEmail(password,person);
                getHome();
            });

            Button disable = new Button("Disable");
            disable.setStyleName(ValoTheme.BUTTON_LINK);
            disable.setData(item.getId());
            disable.addClickListener(event -> {
                Person person = new Person.Builder()
                        .copy(item)
                        .state(DomainState.RETIRED.name())
                        .build();
                PeopleFacade.personService.update(person);
                getHome();
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
           // Notification.show("They are no users"+e.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
        }
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }

    private void getHome(){
        main.content.setSecondComponent(new UserManagementMenu(main,"LANDING"));
    }

    private Set<Person> getUsers(String role){
        Set<Person> persons = new HashSet<>();
        for (Organisation org : OrganisationFacade.companyService.getActiveOrganisations()) {
            for (Person person : PeopleFacade.personService.getPersonByCompany(org.getId())
                    .stream()
                    .filter(per -> per.getState().equalsIgnoreCase(DomainState.ACTIVE.name()))
                    .collect(Collectors.toSet())) {
                persons.addAll(PeopleFacade.personRoleService.findPersonRoles(person.getId())
                        .stream().filter(personRole -> personRole.getRoleId().equalsIgnoreCase(role))
                        .map(personRole -> person).collect(Collectors.toList()));
            }
        }
        return persons;
       /* return  PeopleFacade.personService.getPersonByCompany(OrganisationFacade.companyService.getActiveOrganisations().iterator().next().getId())
                .stream().filter(person ->
                        PeopleFacade.personRoleService.findPersonRoles(person.getId())
                                .iterator()
                                .next()
                                .getRoleId().equalsIgnoreCase(role))
                .collect(Collectors.toSet());*/
    }
    private Set<Person> getAll(){
        return  PeopleFacade.personService.getPersonByCompany(OrganisationUtil.getCompanyCode()).stream()
                .filter(person
                        -> GetUserCredentials.isUserWithRole(PeopleFacade.personRoleService
                        .findPersonRoles(OrganisationUtil.getPersonID())
                        .iterator().next().getRoleId()))
                .collect(Collectors.toSet()).stream()
                .filter(person -> person.getState().equalsIgnoreCase(DomainState.ACTIVE.name()))
                .collect(Collectors.toSet());
    }
}
