package infoshare.client.content.account.views;

import com.vaadin.data.Property;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import infoshare.app.facade.OrganisationFacade;
import infoshare.client.content.MainLayout;
import infoshare.client.content.account.AccountMenu;
import infoshare.client.content.account.model.OrganisationModel;
import infoshare.client.content.account.table.DisabledOrganisationTable;
import infoshare.domain.organisation.Organisation;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hashcode on 2015/11/30.
 */
public class DisabledOrganisationTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;


    private DisabledOrganisationTable table;
    private final HorizontalLayout headerBar = new HorizontalLayout();
    public final VerticalLayout contentPanel = new VerticalLayout();

    private final TextField orgSearchBox = new TextField("Organisation Search");
    Set<Organisation> companies = OrganisationFacade.companyService.getRetiredOrganisations();

    public DisabledOrganisationTab(MainLayout main) {
        contentPanel.setSizeFull();

        orgSearchBox.setInputPrompt("Use Name of the Organisation to Search");
        this.main = main;

        table = new DisabledOrganisationTable(main, this, companies);
        headerBar.setSizeFull();
        headerBar.addComponent(orgSearchBox);
        headerBar.setExpandRatio(orgSearchBox, 1);
        headerBar.setComponentAlignment(orgSearchBox, Alignment.MIDDLE_LEFT);
        headerBar.setComponentAlignment(orgSearchBox, Alignment.MIDDLE_LEFT);
        orgSearchBox.setWidth("400px");
        addComponent(headerBar);
        addComponent(new Label("<HR/>", ContentMode.HTML));
        contentPanel.removeAllComponents();
        contentPanel.addComponent(table);
        addComponent(contentPanel);
        setSizeFull();
        addListeners();
    }


    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();

    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            setReadFormProperties();
        }
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


    private void getHome() {
        main.content.setSecondComponent(new AccountMenu(main, "LANDING"));
    }

    private void setEditFormProperties() {

    }

    private void setReadFormProperties() {

    }

    private void addListeners() {

        //Register Table Listeners
        table.addValueChangeListener(this);
        orgSearchBox.addTextChangeListener(event -> {
            table.removeAllItems();
            Set<Organisation> list = new HashSet<>();
            for (Organisation company : companies) {
                if (company.getName().toLowerCase().contains(event.getText().toLowerCase())) {
                    list.add(company);
                }
            }
            table = new DisabledOrganisationTable(main, DisabledOrganisationTab.this, list);
            contentPanel.removeAllComponents();
            contentPanel.addComponent(table);
        });

        orgSearchBox.addShortcutListener(new ShortcutListener("Clear",
                ShortcutAction.KeyCode.ESCAPE, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                orgSearchBox.setValue("");
                table = new DisabledOrganisationTable(main, DisabledOrganisationTab.this, companies);
                contentPanel.removeAllComponents();
                contentPanel.addComponent(table);
            }
        });
    }
}
