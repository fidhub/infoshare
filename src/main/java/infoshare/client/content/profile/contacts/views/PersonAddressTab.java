package infoshare.client.content.profile.contacts.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.DomainState;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.client.content.MainLayout;
import infoshare.client.content.profile.contacts.ContactsMenu;
import infoshare.client.content.profile.contacts.forms.PersonAddressForm;
import infoshare.client.content.profile.contacts.model.PersonAddressModel;
import infoshare.client.content.profile.contacts.table.PersonAddressTable;
import infoshare.domain.person.PersonAddress;
import infoshare.factories.person.PersonAddressFactory;


/**
 * Created by hashcode on 2015/12/07.
 */
public class PersonAddressTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;
    final private PersonAddressForm form;
    final private PersonAddressTable table;

    public PersonAddressTab(MainLayout main) {
        this.main = main;
        form = new PersonAddressForm();
        table = new PersonAddressTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.save) {
            saveForm(form.binder);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
            saveEditedForm(form.binder);
        } else if (source == form.delete) {
            deleteForm(form.binder);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        final String personId = GetUserCredentials.getUser().getId();
        if (property == table) {
            final PersonAddress personAddress = PeopleFacade.getPersonAddressServiceInstance().findById(personId, table.getValue().toString());
            final PersonAddressModel model = getModel(personAddress);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            PeopleFacade.getPersonAddressServiceInstance().save(getNewEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            PeopleFacade.getPersonAddressServiceInstance().save(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final PersonAddress personAddress = PeopleFacade.getPersonAddressServiceInstance().findById(personId, table.getValue().toString());
        final PersonAddress updatedPersonAddress = new PersonAddress
                .Builder().copy(personAddress)
                .state(DomainState.RETIRED.name())
                .build();
        PeopleFacade.getPersonAddressServiceInstance().save(updatedPersonAddress);
        getHome();
    }


    private void getHome() {
        main.content.setSecondComponent(new ContactsMenu(main, "ADDRESS"));
    }

    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.save.setVisible(false);
        form.edit.setVisible(false);
        form.cancel.setVisible(true);
        form.delete.setVisible(false);
        form.update.setVisible(true);
    }

    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviour
        form.save.setVisible(false);
        form.edit.setVisible(true);
        form.cancel.setVisible(true);
        form.delete.setVisible(true);
        form.update.setVisible(false);
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener(this);
        form.edit.addClickListener(this);
        form.cancel.addClickListener(this);
        form.update.addClickListener(this);
        form.delete.addClickListener(this);
        //Register Table Listeners
        table.addValueChangeListener(this);
    }


    private PersonAddress getNewEntity(FieldGroup binder) {
        final PersonAddressModel model = ((BeanItem<PersonAddressModel>) binder.getItemDataSource()).getBean();
        final String personId = GetUserCredentials.getUser().getId();
        final PersonAddress personAddress = PersonAddressFactory
                .getAddress(personId, model.getDescription(), model.getPostalCode(), model.getAddressTypeId());
        return personAddress;
    }


    private PersonAddress getUpdateEntity(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final PersonAddressModel model = ((BeanItem<PersonAddressModel>) binder.getItemDataSource()).getBean();
        final PersonAddress personAddress = PeopleFacade.getPersonAddressServiceInstance().findById(personId, table.getValue().toString());
        final PersonAddress updatedPersonAddress = new PersonAddress
                .Builder()
                .copy(personAddress)
                .addressTypeId(model.getAddressTypeId())
                .description(model.getDescription())
                .postalCode(model.getPostalCode())
                .build();
        return updatedPersonAddress;
    }

    private PersonAddressModel getModel(PersonAddress personAddress) {
        final PersonAddressModel model = new PersonAddressModel();
        model.setAddressTypeId(personAddress.getAddressTypeId());
        model.setDescription(personAddress.getDescription());
        model.setPostalCode(personAddress.getPostalCode());
        return model;
    }
}
