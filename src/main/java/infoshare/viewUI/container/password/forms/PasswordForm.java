package infoshare.viewUI.container.password.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import infoshare.viewUI.container.password.models.PasswordModel;

/**
 * Created by hashcode on 2015/06/23.
 */
public class PasswordForm extends FormLayout{
    private final PasswordModel model;
    public final BeanItem<PasswordModel> item;
    public final FieldGroup binder;
    public final Button changePasswordButton = new Button("Change Password");
    public final Button cancelButton = new Button("Cancel");

    public PasswordForm() {
        model = new PasswordModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);
        final HorizontalLayout buttons = getButtons();
        changePasswordButton.setStyleName("default");
        cancelButton.setStyleName("default");

        final PasswordField oldpassword = getTextField("Old Password", "oldpassword");
        final PasswordField newPassword = getTextField("New Password", "newPassword");
        final PasswordField repeatPassword = getTextField("Repeat Password", "repeatPassword");

        final GridLayout grid = new GridLayout(3, 10);
        grid.setSizeFull();
        grid.setHeight(400.0f, Unit.PIXELS);
        grid.addComponent(oldpassword, 0, 0);
        grid.addComponent(repeatPassword, 1, 0);
        grid.addComponent(newPassword, 2, 0);
        grid.addComponent(buttons, 0, 1, 2, 1);

        addComponent(grid);
    }

    private PasswordField getTextField(String label, String field) {
        PasswordField textField = new PasswordField(label);
        textField.setWidth(250, Sizeable.Unit.PIXELS);
        textField.setNullRepresentation("");
        textField.addValidator(new BeanValidator(PasswordModel.class, field));
        textField.setImmediate(true);
        binder.bind(textField, field);
        return textField;
    }

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponent(changePasswordButton);
        buttons.addComponent(cancelButton);
        return buttons;
    }
}
