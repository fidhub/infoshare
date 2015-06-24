package infoshare.client.content.setup.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import infoshare.client.content.setup.models.RoleModel;

/**
 * Created by hashcode on 2015/06/24.
 */
public class RoleForm extends FormLayout {

    private final RoleModel model;
    public final BeanItem<RoleModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public RoleForm() {
        model = new RoleModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        TextField rolename = getTextField("Role Name", "rolename");
        TextField description = getTextField("Description", "dscription");

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(rolename, 0, 0);
        grid.addComponent(description, 1, 0, 2, 0);
        grid.addComponent(buttons, 0, 3, 2, 3);

        addComponent(grid);
    }

    private TextField getTextField(String label, String field) {
        TextField textField = new TextField(label);
        textField.setWidth(250, Unit.PIXELS);
        textField.setNullRepresentation("");
        textField.addValidator(new BeanValidator(RoleModel.class, field));
        textField.setImmediate(true);
        binder.bind(textField, field);
        return textField;
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
