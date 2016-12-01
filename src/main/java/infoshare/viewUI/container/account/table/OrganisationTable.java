package infoshare.viewUI.container.account.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.OrganisationFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.DomainState;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.domain.person.Person;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.account.AccountMenu;
import infoshare.viewUI.container.account.forms.OrganisationAdminForm;
import infoshare.viewUI.container.account.views.ManageOrganisationTab;
import infoshare.viewUI.container.account.views.OrganisationDetails;
import infoshare.domain.organisation.Organisation;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/11/15.
 */
public class OrganisationTable extends Table {
    private final MainLayout main;


    public OrganisationTable(MainLayout main, ManageOrganisationTab tab, Set<Organisation> companies) {
        this.main = main;
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Date", Date.class, null);
        addContainerProperty("Name", String.class, null);
        addContainerProperty("Code", String.class, null);
        addContainerProperty("Add Admin", Button.class, null);
        addContainerProperty("Details", Button.class, null);
        addContainerProperty("Disable", Button.class, null);

        companies.parallelStream().forEach(item -> {
            Button addAdmin = new Button("Add Admin");
            addAdmin.setStyleName(ValoTheme.BUTTON_LINK);
            addAdmin.setData(item.getId());
            if (DomainState.WITH_ADMIN.name().equals(item.getAdminattached())) {
                addAdmin.setEnabled(false);
                addAdmin.setDescription(item.getName() + " Has an Admin Account");
            }

            addAdmin.addClickListener(event -> {
                tab.contentPanel.removeAllComponents();
                tab.contentPanel.addComponent(new OrganisationAdminForm(main, item));

            });

            Button details = new Button("Details");
            details.setStyleName(ValoTheme.BUTTON_LINK);
            details.setData(item.getId());
            details.addClickListener(event -> {
                tab.contentPanel.removeAllComponents();
                tab.contentPanel.addComponent(new OrganisationDetails(main, item, "LANDING"));

            });

            Button disable = new Button("Disable");
            disable.setStyleName(ValoTheme.BUTTON_LINK);
            disable.setData(item.getId());
            disable.addClickListener(event -> {
                Organisation company = OrganisationFacade.companyService.findById(item.getId());
                Organisation updatedCompany = new Organisation
                        .Builder()
                        .copy(company)
                        .state(DomainState.RETIRED.name())
                        .build();
                OrganisationFacade.companyService.update(updatedCompany);
                getHome();
            });

            addItem(new Object[]{
                    item.getDate(),
                    item.getName(),
                    item.getId(),
                    addAdmin,
                    details,
                    disable
            }, item.getId());

        });

        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }

    private void getHome() {
        main.content.setSecondComponent(new AccountMenu(main, "LANDING"));
    }

    public Set<Person> getUsers(String role){
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
    public Set<Person> getAll(){
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