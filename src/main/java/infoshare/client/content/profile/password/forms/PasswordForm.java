package infoshare.client.content.profile.password.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.profile.password.model.PasswordModel;


/**
 * Created by hashcode on 2015/12/07.
 */
public class PasswordForm extends FormLayout {

    private final PasswordModel bean;
    public final BeanItem<PasswordModel> item;
    public final FieldGroup binder;
    public final Button changePasswordButton = new Button("Change Password");
    public final Button cancelButton = new Button("Cancel");

    public PasswordForm() {
        bean = new PasswordModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final HorizontalLayout buttons = getButtons();


        final PasswordField oldpassword = getTextField("Old Password", "oldpassword");
        final PasswordField newPassword = getTextField("New Password", "newPassword");
        final PasswordField repeatPassword = getTextField("Repeat Password", "repeatPassword");

        final GridLayout grid = new GridLayout(3, 10);
        grid.setSizeFull();

        grid.addComponent(oldpassword, 0, 0);
        grid.addComponent(repeatPassword, 1, 0);
        grid.addComponent(newPassword, 2, 0);

        grid.addComponent(buttons, 0, 3, 2, 3);

        addComponent(grid);
    }

    private PasswordField getTextField(String label, String field) {
        PasswordField textField = new PasswordField(label);
        textField.setWidth(250, Unit.PIXELS);
        textField.setNullRepresentation("");
        textField.addValidator(new BeanValidator(PasswordModel.class, field));
        textField.setImmediate(true);
        binder.bind(textField, field);
        return textField;
    }

    private HorizontalLayout getButtons() {

        HorizontalLayout buttons = new HorizontalLayout();
        changePasswordButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        changePasswordButton.setSizeFull();
        buttons.setMargin(true);
        buttons.setSizeFull();
        cancelButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        cancelButton.setSizeFull();
        buttons.addComponent(changePasswordButton);
        buttons.addComponent(cancelButton);
        return buttons;
    }
}