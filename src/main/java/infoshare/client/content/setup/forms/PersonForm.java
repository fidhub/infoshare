package infoshare.client.content.setup.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import infoshare.app.facade.RoleFacade;
import infoshare.client.content.setup.models.PersonModel;
import infoshare.services.roles.RoleService;

/**
 * Created by hashcode on 2015/06/24.
 */
public class PersonForm extends FormLayout {


    private final PersonModel model;
    public final BeanItem<PersonModel> item;
    public final FieldGroup binder;

    private RoleService roleService = RoleFacade.roleService;
    public ListSelect rolesList = new ListSelect();
/*
    public  Button addNewAddress = new Button("Address");
    public  Button addNewContact = new Button("Contact");*/

    // Define Buttons
    public final Button save = new Button("Save");
    public final Button edit = new Button("Edit");
    public final Button cancel = new Button("Cancel");
    public final Button update = new Button("Update");
    public final Button delete = new Button("Delete");

    public PersonForm() {
        model = new PersonModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);
        update.setVisible(false);
        delete.setVisible(false);
        addComponent(getLayout());
    }

    private  GridLayout getLayout(){

        final TextField firstname = getTextField("First Name", "firstName");
        final TextField middleName = getTextField("Middle Name", "middleName");
        final TextField emailAddress = getTextField("Email Address", "emailAddress");
        final TextField lastname = getTextField("Last Name", "lastName");
        final TextField authvalue = getTextField("author value", "authvalue");
        final CheckBox enable = getCheckBoxField("Activate Account", "enable");
        final CheckBox accountNonExpired = getCheckBoxField("Account Non-Expired", "accountNonExpired");
        final CheckBox credentialsNonExpired = getCheckBoxField("Credentials Non-Expired", "credentialsNonExpired");
        final CheckBox accountNonLocked = getCheckBoxField("AccountNonLocked", "accountNonLocked");
        final ComboBox state = getComboBox("State", "state");
        final ListSelect roles = getRoles("Select Roles", "role");

        VerticalLayout rightLayout = new VerticalLayout();
        rightLayout.setSpacing(true);
        rightLayout.addComponent(firstname);
        rightLayout.addComponent(middleName);
        rightLayout.addComponent(lastname);
        rightLayout.addComponent(emailAddress);
        rightLayout.addComponent(authvalue);
       // rightLayout.addComponent(password);
        rightLayout.addComponent(new Label("<br/>", ContentMode.HTML));
        rightLayout.addComponent(enable);

        VerticalLayout leftLayout = new VerticalLayout();
        leftLayout.setSpacing(true);
        leftLayout.addComponent(state);
        leftLayout.addComponent(accountNonExpired);
        leftLayout.addComponent(credentialsNonExpired);
        leftLayout.addComponent(accountNonLocked);
        leftLayout.addComponent(roles);

        /*addNewAddress.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        addNewAddress.addStyleName(ValoTheme.BUTTON_SMALL);
        addNewAddress.setIcon(FontAwesome.PLUS);
        addNewContact.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        addNewContact.addStyleName(ValoTheme.BUTTON_SMALL);
        addNewContact.setIcon(FontAwesome.PLUS);
*/
       /* HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        buttons.addComponent(addNewAddress);
        buttons.addComponent(addNewContact);*/

        final GridLayout grid = new GridLayout(8, 5);
        grid.setSizeFull();
        grid.setSpacing(true);
        grid.addComponent(rightLayout, 0, 0);
        grid.addComponent(leftLayout, 2, 0);
       // grid.addComponent(buttons, 0, 1,1,1);
        grid.addComponent(getButtons(), 0, 3,1,3);

        return grid;
    }

    private TextField getTextField(String label, String field) {
        TextField textField = new TextField(label);
        textField.setWidth(250, Unit.PIXELS);
        textField.setNullRepresentation("");
        textField.addValidator(new BeanValidator(PersonModel.class, field));
        textField.setImmediate(true);
        binder.bind(textField, field);
        return textField;
    }
/*
 private PasswordField getPasswordField(String label, String field) {
        PasswordField passwordField = new PasswordField(label);
        passwordField.setWidth(250, Unit.PIXELS);
        passwordField.setNullRepresentation("");
        passwordField.addValidator(new BeanValidator(PersonModel.class, field));
        passwordField.setImmediate(true);
        binder.bind(passwordField, field);
        return passwordField;
    }
*/
    private ComboBox getComboBox(String label, String field){
        final ComboBox comboBox = new ComboBox(label);
        comboBox.setImmediate(true);
        comboBox.addValidator(new BeanValidator(PersonModel.class, field));
        comboBox.setNullSelectionAllowed(false);
        binder.bind(comboBox,field);
        return comboBox;
    }
    private CheckBox getCheckBoxField(String label, String field) {
        CheckBox checkBox = new CheckBox(label);
        checkBox.setWidth(250, Unit.PIXELS);
        checkBox.addValidator(new BeanValidator(PersonModel.class, field));
        checkBox.setImmediate(true);
        binder.bind(checkBox, field);
        return checkBox;
    }

    private ListSelect getRoles(String label, String field) {
        rolesList.setCaption(label);
        roleService.findAll()
                .stream()
                .filter(role -> role.getId() != null).forEach(role -> {
        rolesList.setItemCaption(role.getId(), role.getName() + " " + role.getDescription());
        rolesList.addItem(role.getId());
        });
        rolesList.setNullSelectionAllowed(false);
        rolesList.setMultiSelect(true);
        rolesList.setWidth("250px");
        binder.bind(rolesList, field);
        return rolesList;
    }


    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);
        return buttons;
    }

}