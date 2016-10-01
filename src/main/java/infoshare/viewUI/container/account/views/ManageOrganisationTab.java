package infoshare.viewUI.container.account.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.conf.RestUtil;
import infoshare.app.facade.FileResultsFacade;
import infoshare.app.facade.OrganisationFacade;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.account.AccountMenu;
import infoshare.viewUI.container.account.forms.OrganisationForm;
import infoshare.viewUI.container.account.model.OrganisationModel;
import infoshare.viewUI.container.account.table.OrganisationTable;
import infoshare.domain.organisation.Organisation;
import infoshare.domain.organisation.OrganisationLogo;
import infoshare.domain.storage.FileResults;
import infoshare.factories.organasation.OrganisationFactory;
import infoshare.factories.organasation.OrganisationLogoFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

;

/**
 * Created by hashcode on 2015/11/16.
 */
public class ManageOrganisationTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;
    private final OrganisationForm form;

    private OrganisationTable table;
    private final HorizontalLayout headerBar = new HorizontalLayout();
    public final VerticalLayout contentPanel = new VerticalLayout();

    private final Button addOrg = new Button("Add New Organisation");
    private final TextField orgSearchBox = new TextField(" Organisation Search");
    Set<Organisation> companies = OrganisationFacade.companyService.getActiveOrganisations();


    public ManageOrganisationTab(MainLayout main) {
        contentPanel.setSizeFull();
        addOrg.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        orgSearchBox.setInputPrompt("Use Name of the Organisation to Search");
        this.main = main;
        form = new OrganisationForm();
        table = new OrganisationTable(main, this, companies);
        headerBar.setSizeFull();
        headerBar.addComponent(orgSearchBox);
        headerBar.setExpandRatio(orgSearchBox, 1);
        headerBar.setComponentAlignment(orgSearchBox, Alignment.MIDDLE_LEFT);
        headerBar.addComponent(addOrg);
        headerBar.setComponentAlignment(addOrg, Alignment.MIDDLE_RIGHT);
        headerBar.setComponentAlignment(orgSearchBox, Alignment.MIDDLE_LEFT);
        addOrg.setWidth("200px");
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
        } else if (source == addOrg) {
            contentPanel.removeAllComponents();
            contentPanel.addComponent(form);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final Organisation company = OrganisationFacade.companyService.findById(table.getValue().toString());
            final OrganisationModel model = getModel(company);
            form.binder.setItemDataSource(new BeanItem<>(model));
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

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            if (form.imageUploader.path.length() > 1) {
                Set<FileResults> set = FileResultsFacade.fileResultsService.save(form.imageUploader.path);
                for (FileResults fileResults : set.stream().filter(file -> file.getSize().equalsIgnoreCase("original")).collect(Collectors.toSet())) {
                    OrganisationFacade.companyLogosService.save(getNewLogo(fileResults, binder));
                    form.imageUploader.path = "";
                }
            }
            OrganisationFacade.companyService.save(getNewEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        }
    }
    private OrganisationLogo getNewLogo(FileResults fileResults,FieldGroup binder){
        Map<String, String> stringMap = new HashMap<>();
        final OrganisationModel bean = ((BeanItem<OrganisationModel>) binder.getItemDataSource()).getBean();
        stringMap.put("id",bean.getCode());
        stringMap.put("url", RestUtil.URL+fileResults.getUrl());
        stringMap.put("size",fileResults.getSize());
        stringMap.put("description", bean.getName()+"Logo");
        stringMap.put("mime",fileResults.getMime());
        OrganisationLogo logo = OrganisationLogoFactory.getOrganisationLogo(stringMap);
        return logo;
    }
    private Organisation getNewEntity(FieldGroup binder) {
        final OrganisationModel bean = ((BeanItem<OrganisationModel>) binder.getItemDataSource()).getBean();
        Map<String, String> details = new HashMap<String, String>();
        details.put("address", bean.getAddress());
        details.put("contactphone", bean.getContactphone());
        details.put("email", bean.getEmail());
        final Organisation company = OrganisationFactory.getOrganisation(bean.getName(), bean.getCode(), details);
        return company;

    }

    private void saveEditedForm(FieldGroup binder) {

        try {
            binder.commit();
            if (form.imageUploader.path.length() > 1) {
                Set<FileResults> set = FileResultsFacade.fileResultsService.save(form.imageUploader.path);
                for (FileResults fileResults : set.stream().filter(file -> file.getSize().equalsIgnoreCase("original")).collect(Collectors.toSet())) {
                    OrganisationFacade.companyLogosService.save(getNewLogo(fileResults, binder));
                    form.imageUploader.path = "";
                }
            }
            OrganisationFacade.companyService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private Organisation getUpdateEntity(FieldGroup binder) {
        final Organisation company = OrganisationFacade.companyService.findById(table.getValue().toString());
        final OrganisationModel bean = ((BeanItem<OrganisationModel>) binder.getItemDataSource()).getBean();
        Map<String, String> details = new HashMap<String, String>();
        details.put("address", bean.getAddress());
        details.put("contactphone", bean.getContactphone());
        details.put("email", bean.getEmail());
        final Organisation updatedCompany = new Organisation.Builder()
                .copy(company)
                .details(details)
                .name(bean.getName()).build();
        return updatedCompany;
    }

    private void deleteForm(FieldGroup binder) {
        Organisation company = OrganisationFacade.companyService.findById(table.getValue().toString());
        if (false) {
            Notification.show("CANNOT DELETE", "Object has related Items. Delete Related Items First", Notification.Type.ERROR_MESSAGE);
        } else {
            OrganisationFacade.companyService.delete(company);
            getHome();
        }
    }


    private void getHome() {
        main.content.setSecondComponent(new AccountMenu(main, "LANDING"));
    }

    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.save.setVisible(false);
        form.imageUploader.upload.setVisible(true);
        form.edit.setVisible(false);
        form.cancel.setVisible(true);
        form.delete.setVisible(false);
        form.update.setVisible(true);
    }

    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        form.imageUploader.upload.setVisible(false);
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
        addOrg.addClickListener(this);

        orgSearchBox.addTextChangeListener(event -> {
            table.removeAllItems();
            Set<Organisation> list = new HashSet<>();
            for (Organisation company : companies) {
                if (company.getName().toLowerCase().contains(event.getText().toLowerCase())) {
                    list.add(company);
                }
            }
            table = new OrganisationTable(main, ManageOrganisationTab.this, list);
            contentPanel.removeAllComponents();
            contentPanel.addComponent(table);
        });

        orgSearchBox.addShortcutListener(new ShortcutListener("Clear",
                ShortcutAction.KeyCode.ESCAPE, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                orgSearchBox.setValue("");
                table = new OrganisationTable(main, ManageOrganisationTab.this, companies);
                contentPanel.removeAllComponents();
                contentPanel.addComponent(table);
            }
        });
    }
}
