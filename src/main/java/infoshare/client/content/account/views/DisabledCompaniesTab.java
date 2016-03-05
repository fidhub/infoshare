package infoshare.client.content.account.views;

import com.vaadin.data.Property;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import hashwork.app.facade.OfficeFacade;
import hashwork.client.content.MainLayout;
import hashwork.client.content.account.AccountMenu;
import hashwork.client.content.account.model.CompanyModel;
import hashwork.client.content.account.table.DisabledCompanyTable;
import hashwork.domain.company.Company;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hashcode on 2015/11/30.
 */
public class DisabledCompaniesTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;


    private DisabledCompanyTable table;
    private final HorizontalLayout headerBar = new HorizontalLayout();
    public final VerticalLayout contentPanel = new VerticalLayout();

    private final TextField companySearchBox = new TextField(" Company Search");
    Set<Company> companies = OfficeFacade.companyService.getRetiredCompanies();

    public DisabledCompaniesTab(MainLayout main) {
        contentPanel.setSizeFull();

        companySearchBox.setInputPrompt("Use Name of the Company to Search");
        this.main = main;

        table = new DisabledCompanyTable(main, this, companies);
        headerBar.setSizeFull();
        headerBar.addComponent(companySearchBox);
        headerBar.setExpandRatio(companySearchBox, 1);
        headerBar.setComponentAlignment(companySearchBox, Alignment.MIDDLE_LEFT);
        headerBar.setComponentAlignment(companySearchBox, Alignment.MIDDLE_LEFT);
        companySearchBox.setWidth("400px");
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

    private CompanyModel getModel(Company company) {
        CompanyModel model = new CompanyModel();
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
        companySearchBox.addTextChangeListener(event -> {
            table.removeAllItems();
            Set<Company> list = new HashSet<>();
            for (Company company : companies) {
                if (company.getName().toLowerCase().contains(event.getText().toLowerCase())) {
                    list.add(company);
                }
            }
            table = new DisabledCompanyTable(main, DisabledCompaniesTab.this, list);
            contentPanel.removeAllComponents();
            contentPanel.addComponent(table);
        });

        companySearchBox.addShortcutListener(new ShortcutListener("Clear",
                ShortcutAction.KeyCode.ESCAPE, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                companySearchBox.setValue("");
                table = new DisabledCompanyTable(main, DisabledCompaniesTab.this, companies);
                contentPanel.removeAllComponents();
                contentPanel.addComponent(table);
            }
        });
    }
}
