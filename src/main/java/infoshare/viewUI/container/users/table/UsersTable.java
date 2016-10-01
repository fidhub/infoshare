package infoshare.viewUI.container.users.table;

import com.vaadin.server.Page;
import com.vaadin.ui.Button;
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
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.users.UserManagementMenu;
import infoshare.viewUI.container.users.views.ActiveUsersTab;
import infoshare.viewUI.container.users.views.UserDetails;
import infoshare.domain.organisation.Organisation;
import infoshare.domain.person.Person;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/10/22.
 */
public class UsersTable extends Table {
    private final MainLayout main;
    private ActiveUsersTab tab;
    public  Set<Person> applicants;
    public UsersTable(MainLayout main, ActiveUsersTab usersTab) {
        tab= usersTab;
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
            if (GetUserCredentials.isUserWithRole(RolesValues.ROLE_ADMIN.name())) {
                applicants = getUsers(RolesValues.ORG_ADMIN.name());
            } else if (GetUserCredentials.isUserWithRole(RolesValues.ORG_ADMIN.name())) {
                applicants = PeopleFacade.personService.getPersonByCompany(OrganisationUtil.getCompanyCode())
                        .stream()
                        .filter(person -> person.getState().equalsIgnoreCase(DomainState.ACTIVE.name()))
                        .collect(Collectors.toSet());
            } else {
                applicants = getAll();
            }
            applicants.forEach(this::loadTable);

        }catch (Exception e){
           // Notification.show("They are no users"+e.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
        }
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);


    }

    public  void loadTable(Person item){


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
                MessageBox.showPlain(Icon.WARN,
                        "Password Reset",
                        "Do you really want to RESET " + item.getFirstName() + " Account ?",
                        buttonId -> {
                            if (buttonId == ButtonId.YES) {
                                ExecutorService executorService = Executors.newSingleThreadExecutor();
                                executorService.execute(() -> resetAccount(item));
                                executorService.shutdown();
                                ScreenMessages.getMessage(" Account has been Reset")
                                        .show(Page.getCurrent());
                            }
                        },
                        ButtonId.YES,
                        ButtonId.NO);
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

    }
    private void resetAccount(Person item) {
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
       // SecurityService.sendEmail(password,person);
        getHome();
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
