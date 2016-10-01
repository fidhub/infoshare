package infoshare.viewUI.container.password.tab;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.password.PasswordMenu;
import infoshare.viewUI.container.password.forms.PasswordForm;
import infoshare.viewUI.container.password.models.PasswordModel;

/**
 * Created by hashcode on 2015/06/23.
 */
public class PasswordTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener{
    private final MainLayout main;
    private final PasswordForm form;

    public PasswordTab(MainLayout app) {
        main = app;
        form = new PasswordForm();
        setSizeFull();
        addComponent(form);
        addListeners();
    }
    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (form.cancelButton == source) {
            getHome();
        } else if (form.changePasswordButton == source) {
            saveForm(form.binder);
        }

    }
    private void saveForm(FieldGroup binder) {
        final PasswordModel bean = ((BeanItem<PasswordModel>) form.binder.getItemDataSource()).getBean();
        try {
            binder.commit();
            if (true) {
                if (true) {
                    changePassword(bean.getNewPassword());
                    Notification.show("Password Changed!", Notification.Type.WARNING_MESSAGE);
                } else {
                    Notification.show("New Password Do not Match!", Notification.Type.WARNING_MESSAGE);
                }
            } else {
                Notification.show("Old Passoword Wrong!", Notification.Type.WARNING_MESSAGE);
            }
            getHome();

        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }
    private void getHome() {
        main.content.setSecondComponent(new PasswordMenu(main, "LANDING"));
    }
    private void changePassword(String newPassword) {
    }
    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();

    }
    private void addListeners() {
        //Register Button Listeners
        form.cancelButton.addClickListener(this);
        form.changePasswordButton.addClickListener(this);
    }
}
