package infoshare.client.content.profile.password.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import hashwork.app.facade.PeopleFacade;
import hashwork.app.util.security.GetUserCredentials;
import hashwork.app.util.security.PasswordHash;
import hashwork.client.content.MainLayout;
import hashwork.client.content.profile.password.PasswordMenu;
import hashwork.client.content.profile.password.forms.PasswordForm;
import hashwork.client.content.profile.password.model.PasswordModel;
import hashwork.client.content.profile.password.util.PasswordCheckUtil;
import hashwork.domain.people.Person;

/**
 * Created by hashcode on 2015/12/07.
 */
public class PasswordTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

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

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
    }

    private void saveForm(FieldGroup binder) {
        final PasswordModel bean = ((BeanItem<PasswordModel>) form.binder.getItemDataSource()).getBean();
        try {
            binder.commit();
            if (new PasswordCheckUtil().checkOldPassowrd(bean.getOldpassword())) {
                if (PasswordCheckUtil.comparePasswords(bean.getNewPassword(), bean.getRepeatPassword())) {
                    changePassword(bean.getNewPassword());
                    Notification.show("Your Password Has Been Changed!", Notification.Type.WARNING_MESSAGE);
                } else {
                    Notification.show("There is a Password Mismatch for the New Password", Notification.Type.WARNING_MESSAGE);
                }
            } else {
                Notification.show("Your Old Password is Wrong!", Notification.Type.WARNING_MESSAGE);
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

    private void addListeners() {
        //Register Button Listeners
        form.cancelButton.addClickListener(this);
        form.changePasswordButton.addClickListener(this);
    }

    private void changePassword(String newPassword) {
        Person user = GetUserCredentials.getUser();
        String password = PasswordHash.createEncryptedPassword(newPassword);
        Person updatedUser = new Person.Builder()
                .copy(user)
                .authvalue(password)
                .build();
        PeopleFacade.personService.update(updatedUser);
    }
}
