package infoshare.client.content.account.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.OrganisationFacade;
import infoshare.app.util.DomainState;
import infoshare.client.content.MainLayout;
import infoshare.client.content.account.AccountMenu;
import infoshare.client.content.account.forms.OrganisationAdminForm;
import infoshare.client.content.account.views.ManageOrganisationTab;
import infoshare.client.content.account.views.OrganisationDetails;
import infoshare.domain.organisation.Organisation;

import java.util.Date;
import java.util.Set;

/**
 * Created by hashcode on 2015/11/15.
 */
public class OrganisationTable extends Table {
    private final MainLayout main;


    public OrganisationTable(MainLayout main, ManageOrganisationTab tab, Set<Organisation> companies) {
        this.main = main;
        setSizeFull();
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
}