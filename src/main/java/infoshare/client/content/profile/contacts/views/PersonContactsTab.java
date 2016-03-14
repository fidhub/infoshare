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
import infoshare.client.content.profile.contacts.forms.PersonContactForm;
import infoshare.client.content.profile.contacts.model.PersonContactsModel;
import infoshare.client.content.profile.contacts.table.PersonContactsTable;
import infoshare.domain.person.PersonContact;
import infoshare.factories.person.PersonContactFactory;


/**
 * Created by hashcode on 2015/12/07.
 */
public class PersonContactsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;
    final private PersonContactForm form;
    final private PersonContactsTable table;

    public PersonContactsTab(MainLayout main) {
        this.main = main;
        form = new PersonContactForm();
        table = new PersonContactsTable(main);
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
            final PersonContact personContact = PeopleFacade.getPersonContactServiceInstance().findById(personId, table.getValue().toString());
            final PersonContactsModel model = getModel(personContact);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            PeopleFacade.getPersonContactServiceInstance().save(getNewEntity(binder));
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
            PeopleFacade.getPersonContactServiceInstance().save(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final PersonContact personContact = PeopleFacade.getPersonContactServiceInstance().findById(personId, table.getValue().toString());
        final PersonContact updatedPersonAddress = new PersonContact
                .Builder().copy(personContact)
                .state(DomainState.RETIRED.name())
                .build();
        PeopleFacade.getPersonContactServiceInstance().save(updatedPersonAddress);
        getHome();
    }


    private void getHome() {
        main.content.setSecondComponent(new ContactsMenu(main, "CONTACTS"));
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


    private PersonContact getNewEntity(FieldGroup binder) {
        final PersonContactsModel model = ((BeanItem<PersonContactsModel>) binder.getItemDataSource()).getBean();
        final String personId = GetUserCredentials.getUser().getId();
        final PersonContact personContact = PersonContactFactory
                .getContact(personId, model.getAddressTypeId(), model.getContactValue(), model.getStatus());
        return personContact;
    }

    private PersonContact getUpdateEntity(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final PersonContactsModel model = ((BeanItem<PersonContactsModel>) binder.getItemDataSource()).getBean();
        final PersonContact personContact = PeopleFacade.getPersonContactServiceInstance().findById(personId, table.getValue().toString());
        final PersonContact updatedPersonAddress = new PersonContact
                .Builder()
                .copy(personContact)
                .addresstypeid(model.getAddressTypeId())
                .contactvalue(model.getContactValue())
                .status(model.getStatus())
                .build();
        return updatedPersonAddress;
    }

    private PersonContactsModel getModel(PersonContact personContact) {
        final PersonContactsModel model = new PersonContactsModel();
        model.setAddressTypeId(personContact.getAddressTypeId());
        model.setContactValue(personContact.getContactValue());
        model.setStatus(personContact.getStatus());
        return model;
    }
}