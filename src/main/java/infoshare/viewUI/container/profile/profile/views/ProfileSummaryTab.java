package infoshare.viewUI.container.profile.profile.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.profile.profile.from.ProfileFrom;
import infoshare.viewUI.container.profile.profile.model.ProfileModel;
import infoshare.domain.person.Person;

/**
 * Created by hashcode on 2015/12/07.
 */
public class ProfileSummaryTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {
    final private MainLayout main;
    private ProfileFrom form;
    private final ProfileModel bean;
    public final BeanItem<ProfileModel> item;
    public final FieldGroup binder;
    public ProfileSummaryTab(MainLayout main) {
        this.main = main;
        bean = new ProfileModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        form = new ProfileFrom();
        setSizeFull();
        ProfileModel model = getModel(GetUserCredentials.getUser());
        form.binder.setItemDataSource(new BeanItem<>(model));
        binder.setReadOnly(true);
        addComponent(form);

    }

    private ProfileModel getModel(Person person){
        ProfileModel model = new ProfileModel();
        model.setCompany(person.getOrg());
        model.setFirstName(person.getFirstName());
        model.setLastName(person.getLastName());
        model.setEmailAddress(person.getEmailAddress());
        model.setEnabled(person.getEnabled());
        model.setCredentialsNonExpired(person.getCredentialsNonExpired());
        model.setAccountNonExpired(person.getAccountNonExpired());
        model.setAccountNonLocked(person.getAccountNonLocked());

        return model;
    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
