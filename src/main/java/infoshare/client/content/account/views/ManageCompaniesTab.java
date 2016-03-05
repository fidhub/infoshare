package infoshare.client.content.account.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import hashwork.app.facade.OfficeFacade;
import hashwork.client.content.MainLayout;
import hashwork.client.content.account.AccountMenu;
import hashwork.client.content.account.forms.CompanyForm;
import hashwork.client.content.account.model.CompanyModel;
import hashwork.client.content.account.table.CompanyTable;
import hashwork.domain.company.Company;
import hashwork.factories.company.CompanyFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hashcode on 2015/11/16.
 */
public class ManageCompaniesTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;
    private final CompanyForm form;

    private CompanyTable table;
    private final HorizontalLayout headerBar = new HorizontalLayout();
    public final VerticalLayout contentPanel = new VerticalLayout();

    private final Button addCompany = new Button("Add New Company");
    private final TextField companySearchBox = new TextField(" Company Search");
    Set<Company> companies = OfficeFacade.companyService.getActiveCompanies();


    public ManageCompaniesTab(MainLayout main) {
        contentPanel.setSizeFull();
        addCompany.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        companySearchBox.setInputPrompt("Use Name of the Company to Search");
        this.main = main;
        form = new CompanyForm();
        table = new CompanyTable(main, this, companies);
        headerBar.setSizeFull();
        headerBar.addComponent(companySearchBox);
        headerBar.setExpandRatio(companySearchBox, 1);
        headerBar.setComponentAlignment(companySearchBox, Alignment.MIDDLE_LEFT);
        headerBar.addComponent(addCompany);
        headerBar.setComponentAlignment(addCompany, Alignment.MIDDLE_RIGHT);
        headerBar.setComponentAlignment(companySearchBox, Alignment.MIDDLE_LEFT);
        addCompany.setWidth("200px");
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
        if (source == form.save) {
            saveForm(form.binder);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
            saveEditedForm(form.binder);
        } else if (source == form.delete) {
            deleteForm(form.binder);
        } else if (source == addCompany) {
            contentPanel.removeAllComponents();
            contentPanel.addComponent(form);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final Company company = OfficeFacade.companyService.findById(table.getValue().toString());
            final CompanyModel model = getModel(company);
            form.binder.setItemDataSource(new BeanItem<>(model));
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

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            OfficeFacade.companyService.save(getNewEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private Company getNewEntity(FieldGroup binder) {
        final CompanyModel bean = ((BeanItem<CompanyModel>) binder.getItemDataSource()).getBean();
        Map<String, String> details = new HashMap<String, String>();
        details.put("address", bean.getAddress());
        details.put("contactphone", bean.getContactphone());
        details.put("email", bean.getEmail());
        final Company company = CompanyFactory.getCompany(bean.getName(), bean.getCode(), details);
        return company;

    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            OfficeFacade.companyService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private Company getUpdateEntity(FieldGroup binder) {
        final Company company = OfficeFacade.companyService.findById(table.getValue().toString());
        final CompanyModel bean = ((BeanItem<CompanyModel>) binder.getItemDataSource()).getBean();
        Map<String, String> details = new HashMap<String, String>();
        details.put("address", bean.getAddress());
        details.put("contactphone", bean.getContactphone());
        details.put("email", bean.getEmail());
        final Company updatedCompany = new Company.Builder()
                .copy(company)
                .details(details)
                .name(bean.getName()).build();
        return updatedCompany;
    }

    private void deleteForm(FieldGroup binder) {
        Company company = OfficeFacade.companyService.findById(table.getValue().toString());
        if (false) {
            Notification.show("CANNOT DELETE", "Object has related Items. Delete Related Items First", Notification.Type.ERROR_MESSAGE);
        } else {
            OfficeFacade.companyService.delete(company);
            getHome();
        }
    }


    private void getHome() {
        main.content.setSecondComponent(new AccountMenu(main, "LANDING"));
    }

    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.save.setVisible(false);
        form.edit.setVisible(false);
        form.cancel.setVisible(true);
        form.delete.setVisible(false);
        form.update.setVisible(true);
    }

    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviour
        form.save.setVisible(false);
        form.edit.setVisible(true);
        form.cancel.setVisible(true);
        form.delete.setVisible(true);
        form.update.setVisible(false);
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener(this);
        form.edit.addClickListener(this);
        form.cancel.addClickListener(this);
        form.update.addClickListener(this);
        form.delete.addClickListener(this);
        //Register Table Listeners
        table.addValueChangeListener(this);
        addCompany.addClickListener(this);

        companySearchBox.addTextChangeListener(event -> {
            table.removeAllItems();
            Set<Company> list = new HashSet<>();
            for (Company company : companies) {
                if (company.getName().toLowerCase().contains(event.getText().toLowerCase())) {
                    list.add(company);
                }
            }
            table = new CompanyTable(main, ManageCompaniesTab.this, list);
            contentPanel.removeAllComponents();
            contentPanel.addComponent(table);
        });

        companySearchBox.addShortcutListener(new ShortcutListener("Clear",
                ShortcutAction.KeyCode.ESCAPE, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                companySearchBox.setValue("");
                table = new CompanyTable(main, ManageCompaniesTab.this, companies);
                contentPanel.removeAllComponents();
                contentPanel.addComponent(table);
            }
        });
    }
}
