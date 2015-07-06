package infoshare.client.content.setup.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import infoshare.client.content.setup.models.UserModel;
import infoshare.conf.util.UIComboBoxHelper;
import infoshare.domain.Role;
import infoshare.services.roles.Impl.RoleServiceImpl;
import infoshare.services.roles.RoleService;

/**
 * Created by hashcode on 2015/06/24.
 */
public class UserForm extends FormLayout {


    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final UserModel model;
    public final BeanItem<UserModel> item;
    public final FieldGroup binder;

    private RoleService roleService = new RoleServiceImpl();
    public ListSelect rolesList = new ListSelect();
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
        final HorizontalLayout buttons = getButtons();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);


        final TextField firstname = getTextField("First Name", "firstname");
        final TextField lastname = getTextField("Last Name", "lastname");
        final TextField username = getTextField("Username", "username");
        final CheckBox enable = getCheckBoxField("Activate Account", "enabled");
        final ListSelect roles = getRoles("Select Roles", "roleIds");



        final GridLayout grid = new GridLayout(3, 10);
        grid.setSizeFull();

        grid.addComponent(firstname, 0, 0);
        grid.addComponent(lastname, 1, 0);



        grid.addComponent(username, 0, 1);
        grid.addComponent(enable, 0, 2);

        grid.addComponent(roles, 1, 1, 1, 2);



        grid.addComponent(buttons, 0, 3, 2, 3);

        addComponent(grid);
    }

    private TextArea getTextArea(String label, String field) {
        TextArea textArea = new TextArea(label);
        textArea.setWidth(250, Unit.PIXELS);
        textArea.setNullRepresentation("");
        textArea.addValidator(new BeanValidator(UserModel.class, field));
        textArea.setImmediate(true);
        binder.bind(textArea, field);
        return textArea;

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

    private ComboBox getComboBox(String label, String field) {
        ComboBox comboBox = new ComboBox(label);

        binder.bind(comboBox, field);
        return comboBox;
    }

    private ListSelect getRoles(String label, String field) {
        rolesList.setCaption(label);
        for (Role role : roleService.findAll()) {
            rolesList.setItemCaption(role.getId(), role.getRolename() + " " + role.getDescription());
            rolesList.setNullSelectionAllowed(false);
            rolesList.setMultiSelect(true);
            rolesList.addItem(role.getId());
        }
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