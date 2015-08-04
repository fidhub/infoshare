package infoshare.client.content.setup.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import infoshare.client.content.setup.models.UserModel;
import infoshare.domain.Address;
import infoshare.domain.Contact;
import infoshare.services.Address.AddressService;
import infoshare.services.Address.Impl.AddressServiceImpl;
import infoshare.services.Contact.ContactService;
import infoshare.services.Contact.Impl.ContactServiceImpl;
import infoshare.services.roles.Impl.RoleServiceImpl;
import infoshare.services.roles.RoleService;

/**
 * Created by hashcode on 2015/06/24.
 */
public class UserForm extends FormLayout {


    private final UserModel model;
    public final BeanItem<UserModel> item;
    public final FieldGroup binder;

    private RoleService roleService = new RoleServiceImpl();
    public ListSelect rolesList = new ListSelect();

    private ComboBox contactComb;
    private ComboBox addressComb;
    // Define Buttons
    public final Button save = new Button("Save");
    public final Button edit = new Button("Edit");
    public final Button cancel = new Button("Cancel");
    public final Button update = new Button("Update");
    public final Button delete = new Button("Delete");

    public UserForm() {
        model = new UserModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);
        loadCombos();

        addComponent(getLayout());
    }

    private  GridLayout getLayout(){

        final TextField firstname = getTextField("First Name", "firstName");
        final TextField lastname = getTextField("Last Name", "lastName");
        final TextField username = getTextField("Username", "username");
        final TextField otherName = getTextField("Other name", "otherName");
        contactComb = getCombo("contact number","contact");
        addressComb = getCombo("Address ","address");
        final CheckBox enable = getCheckBoxField("Activate Account", "enable");
        final ListSelect roles = getRoles("Select Roles", "role");

        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.addComponent(username);
        layout.addComponent(otherName);

        VerticalLayout layout2 = new VerticalLayout();
        layout2.setSpacing(true);
        layout2.addComponent(contactComb);
        layout2.addComponent(addressComb);

        final GridLayout grid = new GridLayout(3, 10);
        grid.setSizeFull();

        grid.addComponent(firstname, 0, 0);
        grid.addComponent(lastname, 1, 0);
        grid.addComponent(layout2,1,1);
        grid.addComponent(layout, 0, 1);
        grid.addComponent(enable, 0, 2);
        grid.addComponent(roles, 2, 0, 2, 2);
        grid.addComponent( getButtons(), 0, 3, 2, 3);

        return grid;
    }

    private TextField getTextField(String label, String field) {
        TextField textField = new TextField(label);
        textField.setWidth(250, Unit.PIXELS);
        textField.setNullRepresentation("");
        textField.addValidator(new BeanValidator(UserModel.class, field));
        textField.setImmediate(true);
        binder.bind(textField, field);
        return textField;
    }

    private CheckBox getCheckBoxField(String label, String field) {
        CheckBox checkBox = new CheckBox(label);
        checkBox.setWidth(250, Unit.PIXELS);
        checkBox.addValidator(new BeanValidator(UserModel.class, field));
        checkBox.setImmediate(true);
        binder.bind(checkBox, field);
        return checkBox;
    }

    private ListSelect getRoles(String label, String field) {
        rolesList.setCaption(label);
        roleService.findAll().stream().filter(role -> role.getId() != null).forEach(role -> {
            rolesList.setItemCaption(role.getId(), role.getRoleName() + " " + role.getDescription());
            rolesList.setNullSelectionAllowed(false);
            rolesList.setMultiSelect(true);
            rolesList.addItem(role.getId());
        });
        rolesList.setWidth("250px");
        binder.bind(rolesList, field);

        return rolesList;
    }

    private void loadCombos(){
        AddressService addressService = new AddressServiceImpl();
        ContactService contactService = new ContactServiceImpl();
        for(Contact contact : contactService.findAll()) {
            if(contact == null) {
                contactComb.setItemCaption(contact.getId(), contact.getPhone());
                contactComb.addItem(contact.getId());
            }
        }
        for(Address address : addressService.findAll()) {
            if( address == null) {
                addressComb.setItemCaption(address.getId(), address.getPhysicalAddress());
                addressComb.addItem(address.getId());
            }
        }


    }

    private ComboBox getCombo(String label, String field){
        final ComboBox comboBox = new ComboBox(label);
        comboBox.setWidth(250, Unit.PIXELS);
        comboBox.setNullSelectionAllowed(false);
        comboBox.addValidator(new BeanValidator(UserModel.class, field));
      //  comboBox.setMultiSelect(true);
        comboBox.setImmediate(true);
        binder.bind(comboBox, field);
        return  comboBox;
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