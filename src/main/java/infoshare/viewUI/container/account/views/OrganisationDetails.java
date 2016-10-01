package infoshare.viewUI.container.account.views;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import infoshare.app.conf.RestUtil;
import infoshare.app.facade.FileResultsFacade;
import infoshare.app.facade.OrganisationFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.DomainState;
import infoshare.app.util.ScreenMessages;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.app.util.security.RolesValues;
import infoshare.app.util.security.SecurityService;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.account.AccountMenu;
import infoshare.viewUI.container.account.forms.OrganisationAdminForm;
import infoshare.viewUI.container.account.forms.OrganisationForm;
import infoshare.viewUI.container.account.model.OrganisationModel;
import infoshare.domain.organisation.Organisation;
import infoshare.domain.organisation.OrganisationLogo;
import infoshare.domain.person.Person;
import infoshare.domain.storage.FileResults;
import infoshare.factories.organasation.OrganisationFactory;
import infoshare.factories.organasation.OrganisationLogoFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

;

/**
 * Created by hashcode on 2015/12/01.
 */
public class OrganisationDetails extends VerticalLayout implements
        Button.ClickListener {

    private final MainLayout main;
    private final GridLayout grid;
    private final OrganisationForm form;
    private final String tab;
    private final Organisation organisation;
    private final String orgId;


    public OrganisationDetails(MainLayout main, Organisation co, String tab) {
        this.main = main;
        this.tab = tab;
        form = new OrganisationForm();
        organisation = co;
        orgId = organisation.getId();
        final OrganisationModel model = getModel(organisation);
        form.binder.setItemDataSource(new BeanItem<>(model));
        Label heading = new Label("<center><h2>Details for " + organisation.getName() + "</H2></center>", ContentMode.HTML);
        heading.setStyleName(ValoTheme.LABEL_COLORED);
        heading.setSizeFull();

        setReadFormProperties();

        grid = new GridLayout(4, 10);
        grid.setSizeFull();
        // First ROW
        grid.addComponent(heading, 0, 0, 3, 0);
        grid.addComponent(form, 0, 1, 3, 1);



        Set<Person> admins = PeopleFacade.personService.getPersonsWithRole(organisation.getId(), RolesValues.ORG_ADMIN.name());


        if (admins.size() == 0) {
            Button addAdmin = new Button("Add Organisation Administrator", FontAwesome.USER);
            addAdmin.setWidth(100, Unit.PERCENTAGE);
            addAdmin.setHeight(50, Unit.PIXELS);
            addAdmin.setIcon(FontAwesome.USER);
            addAdmin.setStyleName(ValoTheme.BUTTON_HUGE);
            grid.addComponent(addAdmin, 0, 4, 3, 4);
            addAdmin.addClickListener(event -> {
                removeAllComponents();
                addComponent(new OrganisationAdminForm(main, organisation));
            });

        }
        if (admins.size() > 0) {
            Label companyAdmin = new Label("<h2>"+organisation.getName().toUpperCase()+"'S CURRENT EMPLOYEES</H2>", ContentMode.HTML);
            companyAdmin.setStyleName(ValoTheme.LABEL_COLORED);
            companyAdmin.setSizeFull();
            companyAdmin.setSizeFull();
            grid.addComponent(companyAdmin, 0, 2, 3, 2);

            Table adminTable = new Table();
            adminTable.addStyleName(ValoTheme.TABLE_BORDERLESS);
            adminTable.addStyleName(ValoTheme.TABLE_NO_STRIPES);
            adminTable.addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
            adminTable.addStyleName(ValoTheme.TABLE_SMALL);
            adminTable.setPageLength(8);
            adminTable.setSizeFull();
            adminTable.addContainerProperty("Last Name", String.class, null);
            adminTable.addContainerProperty("First Name", String.class, null);
            adminTable.addContainerProperty("Email Address", String.class, null);
            adminTable.addContainerProperty("Reset Credentials", Button.class, null);

            admins.parallelStream().forEach(item -> {
                Button reset = new Button("Reset Password");
                reset.setStyleName(ValoTheme.BUTTON_LINK);
                reset.setData(item.getId());
                reset.addClickListener(event -> {
                    MessageBox.showPlain(Icon.WARN,
                            "Password Reset",
                            "Do you really want to RESET " + organisation.getName() + " Administrator Password?",
                            buttonId -> {
                                if (buttonId == ButtonId.YES) {
                                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                                    executorService.execute(() -> reset(item));
                                    executorService.shutdown();
                                    ScreenMessages.getMessage("The Password has been Reset")
                                            .show(Page.getCurrent());
                                }
                            },
                            ButtonId.YES,
                            ButtonId.NO);
                });
                adminTable.addItem(new Object[]{
                        item.getLastName(),
                        item.getFirstName(),
                        item.getEmailAddress(),
                        reset,
                }, item.getId());

            });
            adminTable.setNullSelectionAllowed(false);
            adminTable.setSelectable(true);
            adminTable.setImmediate(true);
            grid.addComponent(adminTable, 0, 5, 3, 5);
        }

        addComponent(grid);
        addListeners();
    }

    private void reset(Person item) {
        SecurityService.resetValue(item);
    }

    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviour
        form.imageUploader.upload.setVisible(false);
        form.imageUploader.image.setVisible(true);
        form.imageUploader.image.setHeight(175.0f, Unit.PIXELS);
        form.imageUploader.image.setWidth(100.0f, Unit.PERCENTAGE);
        form.save.setVisible(false);
        form.edit.setVisible(true);
        form.cancel.setVisible(true);
        form.delete.setVisible(true);
        form.update.setVisible(false);
    }

    private OrganisationModel getModel(Organisation company) {
        OrganisationModel model = new OrganisationModel();
        model.setAddress(company.getDetails().get("address"));
        model.setContactphone(company.getDetails().get("contactphone"));
        model.setEmail(company.getDetails().get("email"));
        model.setState(company.getState());
        model.setCode(company.getId());
        model.setName(company.getName());
        try {
            form.imageUploader.image.setSource(
                    new ExternalResource(OrganisationFacade.companyLogosService
                            .findById(company.getId()
                                    , company.getId()).getUrl()));
        }catch (Exception e){
            form.imageUploader.image.setVisible(false);
        }

        return model;
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
        }
    }

    private void deleteForm(FieldGroup binder) {
        Organisation updatedCompany = new Organisation
                .Builder()
                .copy(organisation)
                .state(DomainState.RETIRED.name())
                .build();
        OrganisationFacade.companyService.update(updatedCompany);
        getHome();

    }

    private void saveEditedForm(FieldGroup binder) {
        Organisation updatedCompany = getUpdateEntity(binder);
        if(form.imageUploader.path.length()>1) {
            Set<FileResults> set = FileResultsFacade.fileResultsService.save(form.imageUploader.path);
            for (FileResults fileResult : set.stream().filter(file -> file.getSize().equalsIgnoreCase("original")).collect(Collectors.toSet())) {
                OrganisationFacade.companyLogosService.save(getNewLogo(fileResult));
                OrganisationFacade.companyService.update(updatedCompany);
                form.imageUploader.path = "";
            }
        }

        getHome();
    }

    private void saveForm(FieldGroup binder) {
        Organisation newCompany = getNewEntity(binder);
        if (form.imageUploader.path.length() > 1) {
            try {
                Set<FileResults> set = FileResultsFacade.fileResultsService.save(form.imageUploader.path);
                for (FileResults fileResults : set.stream().filter(file -> file.getSize().equalsIgnoreCase("original")).collect(Collectors.toSet())) {
                    OrganisationFacade.companyLogosService.save(getNewLogo(fileResults));
                    OrganisationFacade.companyService.save(newCompany);
                    form.imageUploader.path = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            getHome();
        }
    }

    private OrganisationLogo getNewLogo(FileResults fileResults){
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("id",orgId);
        stringMap.put("url", RestUtil.URL+fileResults.getUrl());
        stringMap.put("size",fileResults.getSize());
        stringMap.put("description", OrganisationUtil.getCompanyCode()+" Logo");
        stringMap.put("mime",fileResults.getMime());
        stringMap.put("org",orgId);
        OrganisationLogo logo = OrganisationLogoFactory.getOrganisationLogo(stringMap);
        return logo;

    }

    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.imageUploader.upload.setVisible(true);
        form.save.setVisible(false);
        form.edit.setVisible(false);
        form.cancel.setVisible(true);
        form.delete.setVisible(false);
        form.update.setVisible(true);
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener(this);
        form.edit.addClickListener(this);
        form.cancel.addClickListener(this);
        form.update.addClickListener(this);
        form.delete.addClickListener(this);

        //Register Table Listeners
    }

    private void getHome() {
        main.content.setSecondComponent(new AccountMenu(main, tab));
    }

    private Organisation getUpdateEntity(FieldGroup binder) {
        try {
            binder.commit();
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }
        final Organisation company = OrganisationFacade.companyService.findById(orgId);
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

    private Organisation getNewEntity(FieldGroup binder) {
        final OrganisationModel bean = ((BeanItem<OrganisationModel>) binder.getItemDataSource()).getBean();
        Map<String, String> details = new HashMap<String, String>();
        details.put("address", bean.getAddress());
        details.put("contactphone", bean.getContactphone());
        details.put("email", bean.getEmail());
        final Organisation company =
                OrganisationFactory.getOrganisation(bean.getName(), bean.getCode(), details);
        return company;

    }
}
