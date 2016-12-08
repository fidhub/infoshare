package infoshare.viewUI.container.users.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import infoshare.app.facade.DemographicsFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.DomainState;
import infoshare.app.util.fields.ButtonsHelper;
import infoshare.app.util.fields.UIComboBoxHelper;
import infoshare.app.util.fields.UIComponentHelper;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.app.util.security.PasswordHash;
import infoshare.app.util.security.SecurityService;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.account.model.AdminModel;
import infoshare.viewUI.container.users.UserManagementMenu;
import infoshare.viewUI.container.users.model.UserModel;
import infoshare.domain.demographics.Role;
import infoshare.domain.person.Person;
import infoshare.domain.person.PersonRole;
import infoshare.factories.person.PersonFactory;
import infoshare.factories.person.PersonRoleFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by THULEH on 2016/03/31.
 */
public class UserForm extends FormLayout implements Button.ClickListener  {
    public final BeanItem<UserModel> item;
    public final FieldGroup binder;
    private final  MainLayout main;
    private UserModel bean;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public UserForm(MainLayout main) {
        this.main = main;
        bean = new UserModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final UIComponentHelper UIComponent = new UIComponentHelper();
        final UIComboBoxHelper UIComboBox = new UIComboBoxHelper();

        TextField firstName = UIComponent.getGridTextField("First Name :", "firstName", UserModel.class, binder);
        TextField middlename = UIComponent.getGridTextField("Middle Name :", "middleName", UserModel.class, binder);
        TextField password = UIComponent.getGridTextField("Password :", "password", AdminModel.class, binder);
        TextField lastname = UIComponent.getGridTextField("Last Name:", "lastName", UserModel.class, binder);
        TextField emailAddress = UIComponent.getGridTextField("Email Address :", "emailAddress", UserModel.class, binder);

        ComboBox role = UIComboBox.getUploadComboBox("Select Role :", "role", UserModel.class, binder);

        role.setPageLength(4);
        for (Role role1: DemographicsFacade.rolesListService.findAll().stream()
                .filter(rol -> rol.getState().equalsIgnoreCase(DomainState.ACTIVE.name())).collect(Collectors.toSet())){
            role.addItem(role1.getId());
            role.setDescription(role1.getName());
        }

        // Create a field group and use it to bind the fields in the layout
        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        // First ROW
        grid.addComponent(firstName, 0, 0);
        grid.addComponent(lastname, 1, 0);

        //Second ROW
        grid.addComponent(middlename, 0, 1);
        grid.addComponent(emailAddress, 1, 1);
        //3rd ROW
        grid.addComponent(role, 0, 2);
        grid.addComponent(password,1,2);

        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        grid.addComponent(buttons, 0, 5, 2, 5);
        addComponent(grid);
        addListeners();
    }
    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == save) {
            saveForm(binder);
        } else if (source == edit) {
            setEditFormProperties();
        } else if (source == cancel) {
            getHome();
        } else if (source == update) {
            saveEditedForm(binder);
        } else if (source == delete) {
            deleteForm(binder);
        }
    }
    private void deleteForm(FieldGroup binder) {

    }

    private void saveEditedForm(FieldGroup binder) {

    }

    private void getHome(){
        main.content.setSecondComponent(new UserManagementMenu(main,"LANDING"));
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }

        final UserModel bean = ((BeanItem<UserModel>) binder.getItemDataSource()).getBean();


        //final String password = SecurityService.generateRandomPassword();
        Map<String, String> stringVals = new HashMap<>();

        String password = "admin";
        stringVals.put("firstName", bean.getFirstName());
        stringVals.put("middleName", bean.getMiddleName());
        stringVals.put("lastName", bean.getLastName());
        stringVals.put("authvalue", password);
        //stringVals.put("password", bean.getPassword());
        stringVals.put("emailAddress", bean.getEmailAddress());
        stringVals.put("org", OrganisationUtil.getCompanyCode());
        stringVals.put("role",bean.getRole());

        Map<String, Boolean> boolVals = new HashMap<>();
        boolVals.put("enabled", Boolean.TRUE);
        boolVals.put("accountNonExpired", Boolean.TRUE);
        boolVals.put("accountNonLocked", Boolean.TRUE);
        boolVals.put("credentialsNonExpired", Boolean.TRUE);
        Person companyAdmin = createAccount(stringVals, boolVals);
        SecurityService.sendEmail(password, companyAdmin);
        getHome();

    }

    private Person createAccount(Map<String, String> stringVals, Map<String, Boolean> boolVals) {
        Person companyAdmin = PersonFactory.getPerson(stringVals, boolVals);
        try {
            PeopleFacade.personService.save(companyAdmin);
            PersonRole role = PersonRoleFactory.getPersonRole(companyAdmin.getId(), stringVals.get("role"));
            PeopleFacade.personRoleService.save(role);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return companyAdmin;
    }
    private void setEditFormProperties() {
        binder.setReadOnly(false);
        save.setVisible(false);
        edit.setVisible(false);
        cancel.setVisible(true);
        delete.setVisible(false);
        update.setVisible(true);
    }

    private void addListeners() {
        //Register Button Listeners
        save.addClickListener(this);
        edit.addClickListener(this);
        cancel.addClickListener(this);
        update.addClickListener(this);
        delete.addClickListener(this);
    }


}
