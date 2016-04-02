package infoshare.client.content.account.table;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.OrganisationFacade;
import infoshare.app.util.DomainState;
import infoshare.client.content.MainLayout;
import infoshare.client.content.account.AccountMenu;
import infoshare.client.content.account.forms.OrganisationForm;
import infoshare.client.content.account.model.OrganisationModel;
import infoshare.client.content.account.views.DisabledOrganisationTab;
import infoshare.domain.organisation.Organisation;

import java.util.Date;
import java.util.Set;

/**
 * Created by hashcode on 2015/11/30.
 */
public class DisabledOrganisationTable extends Table {
    private final MainLayout main;
    public final BeanItem<OrganisationModel> item;
    private final OrganisationModel bean;
    public final FieldGroup binder;
    public DisabledOrganisationTable(MainLayout main, DisabledOrganisationTab tab, Set<Organisation> companies) {
        this.main = main;
        bean = new OrganisationModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Date", Date.class, null);
        addContainerProperty("Name", String.class, null);
        addContainerProperty("Code", String.class, null);
        addContainerProperty("Details", Button.class, null);
        addContainerProperty("Enable", Button.class, null);

        companies.parallelStream().forEach(item -> {
            Button details = new Button("Details");
            details.setStyleName(ValoTheme.BUTTON_LINK);
            details.setData(item.getId());
            details.addClickListener(event -> {
                tab.contentPanel.removeAllComponents();
                tab.contentPanel.addComponent(details(item.getId()));
            });

            Button enable = new Button("Enable");
            enable.setStyleName(ValoTheme.BUTTON_LINK);
            enable.setData(item.getId());
            enable.addClickListener(event -> {
                Organisation company = OrganisationFacade.companyService.findById(item.getId());
                Organisation updatedCompany = new Organisation
                        .Builder()
                        .copy(company)
                        .state(DomainState.ACTIVE.name())
                        .build();
                OrganisationFacade.companyService.update(updatedCompany);
                getHome();

            });

            addItem(new Object[]{
                    item.getDate(),
                    item.getName(),
                    item.getId(),
                    details,
                    enable
            }, item.getId());

        });

        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }

    private void getHome() {
        main.content.setSecondComponent(new AccountMenu(main, "DISABLED"));
    }

    private VerticalLayout details(String orgId){
        VerticalLayout layout = new VerticalLayout();
        Organisation organisation = OrganisationFacade.companyService.findById(orgId);
        final OrganisationModel model = getModel(organisation);
        Label heading = new Label("<center><h2>Details for " + organisation.getName() + "</H2></center>", ContentMode.HTML);
        OrganisationForm form = new OrganisationForm();
        form.binder.setItemDataSource(new BeanItem<>(model));
        form.binder.setReadOnly(true);
        form.cancel.setVisible(false);
        form.save.setVisible(false);

        Button enable = new Button("Enable");
        enable.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        enable.addStyleName(ValoTheme.BUTTON_SMALL);
        enable.setSizeFull();
        enable.addClickListener(clickEvent -> {
            Organisation org = new Organisation.Builder()
                    .copy(organisation)
                    .state(DomainState.ACTIVE.name())
                    .build();
            OrganisationFacade.companyService.update(org);
            getHome();
        });
        Button cancel = new Button("Cancel");
        cancel.addStyleName(ValoTheme.BUTTON_PRIMARY);
        cancel.addStyleName(ValoTheme.BUTTON_SMALL);
        cancel.setSizeFull();
        cancel.addClickListener(clickEvent -> getHome());

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.setSpacing(true);
        horizontalLayout.addComponent(enable);
        horizontalLayout.addComponent(cancel);

        layout.addComponent(heading);
        layout.addComponent(form);
        layout.addComponent(horizontalLayout);
        return layout;
    }
    private OrganisationModel getModel(Organisation company) {
        OrganisationModel model = new OrganisationModel();
        model.setAddress(company.getDetails().get("address"));
        model.setContactphone(company.getDetails().get("contactphone"));
        model.setEmail(company.getDetails().get("email"));
        model.setState(company.getState());
        model.setCode(company.getId());
        model.setName(company.getName());
        return model;
    }
}