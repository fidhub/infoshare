package infoshare.client.content.users.table;

import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import infoshare.app.facade.OrganisationFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.DomainState;
import infoshare.app.util.ScreenMessages;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.app.util.security.PasswordHash;
import infoshare.app.util.security.RolesValues;
import infoshare.app.util.security.SecurityService;
import infoshare.client.content.MainLayout;
import infoshare.client.content.users.UserManagementMenu;
import infoshare.client.content.users.views.UserDetails;
import infoshare.domain.organisation.Organisation;
import infoshare.domain.person.Person;
import infoshare.restapi.people.PersonAPI;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by THULEH on 2016/03/31.
 */
public class DisabledUsersTable extends Table {
    private final MainLayout main;
    public Set<Person> applicants;
    public DisabledUsersTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Last Name", String.class, null);
        addContainerProperty("First Name", String.class, null);
        addContainerProperty("Email Address", String.class, null);
        addContainerProperty("Enable Account", Button.class, null);
        try {

            if(GetUserCredentials.isUserWithRole(RolesValues.ROLE_ADMIN.name())) {
                applicants = getUsers(RolesValues.ORG_ADMIN.name());
            }else if(GetUserCredentials.isUserWithRole(RolesValues.ORG_ADMIN.name())) {
                applicants = PeopleFacade.personService.getPersonByCompany(OrganisationUtil.getCompanyCode())
                        .stream().filter(person -> person.getState().equalsIgnoreCase(DomainState.RETIRED.name()))
                        .collect(Collectors.toSet());
            } else {
                applicants = getAll();
            }
            applicants.forEach(this::loadTable);
        }catch (Exception e){
            //Notification.show("They are no users"+e.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
        }
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }
    public  void loadTable(Person item){
        Button disable = new Button("Enable");
        disable.setStyleName(ValoTheme.BUTTON_LINK);
        disable.setData(item.getId());
        disable.addClickListener(event -> {
            Person person = new Person.Builder()
                    .copy(item)
                    .state(DomainState.ACTIVE.name())
                    .build();
            PeopleFacade.personService.update(person);
            getHome();
        });

        addItem(new Object[]{
                item.getLastName(),
                item.getFirstName(),
                item.getEmailAddress(),
                disable
        }, item.getId());


    }

    private Set<Person> getUsers(String role) {
        Set<Person> persons = new HashSet<>();
        try {
            for (Organisation org : OrganisationFacade.companyService.getActiveOrganisations()) {
                for (Person person : PeopleFacade.personService.getPersonByCompany(org.getId()).stream()
                        .filter(per -> per.getState().equalsIgnoreCase(DomainState.RETIRED.name()))
                        .collect(Collectors.toSet())) {
                    persons.addAll(PeopleFacade.personRoleService.findPersonRoles(person.getId())
                            .stream().filter(personRole -> personRole.getRoleId().equalsIgnoreCase(role))
                            .map(personRole -> person).collect(Collectors.toList()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }
    private Set<Person> getAll(){
        return  PeopleFacade.personService.getPersonByCompany(OrganisationUtil.getCompanyCode()).stream()
                .filter(person
                        -> GetUserCredentials.isUserWithRole(PeopleFacade.personRoleService
                        .findPersonRoles(OrganisationUtil.getPersonID())
                        .iterator().next().getRoleId()))
                .collect(Collectors.toSet())
                .stream().filter(person -> person.getState().equalsIgnoreCase(DomainState.RETIRED.name()))
                .collect(Collectors.toSet());
    }
    private void getHome(){
        main.content.setSecondComponent(new UserManagementMenu(main,"DISABLE"));
    }
}
