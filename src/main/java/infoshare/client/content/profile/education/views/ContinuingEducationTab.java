package infoshare.client.content.profile.education.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import hashwork.app.facade.PeopleFacade;
import hashwork.app.util.DomainState;
import hashwork.app.util.security.GetUserCredentials;
import hashwork.client.content.MainLayout;
import hashwork.client.content.profile.contacts.ContactsMenu;
import hashwork.client.content.profile.contacts.model.PersonAddressModel;
import hashwork.client.content.profile.education.table.ContinuingEducationTable;
import hashwork.domain.people.PersonAddress;
import hashwork.factories.people.PersonAddressFactory;

/**
 * Created by hashcode on 2015/12/07.
 */
public class ContinuingEducationTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;


    final private ContinuingEducationTable table;

    public ContinuingEducationTab(MainLayout main) {
        this.main = main;

        table = new ContinuingEducationTable(main);
        setSizeFull();
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();

    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        final String personId = GetUserCredentials.getUser().getId();
        if (property == table) {
            final PersonAddress personAddress = PeopleFacade.personAddressService.findById(personId, table.getValue().toString());
            final PersonAddressModel model = getModel(personAddress);
            

        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            PeopleFacade.personAddressService.save(getNewEntity(binder));
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
            PeopleFacade.personAddressService.save(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final PersonAddress personAddress = PeopleFacade.personAddressService.findById(personId, table.getValue().toString());
        final PersonAddress updatedPersonAddress = new PersonAddress
                .Builder().copy(personAddress)
                .state(DomainState.RETIRED.name())
                .build();
        PeopleFacade.personAddressService.save(updatedPersonAddress);
        getHome();
    }


    private void getHome() {
        main.content.setSecondComponent(new ContactsMenu(main, "ADDRESS"));
    }


    private void addListeners() {
        //Register Button Listeners

        //Register Table Listeners
        table.addValueChangeListener(this);
    }


    private PersonAddress getNewEntity(FieldGroup binder) {
        final PersonAddressModel model = ((BeanItem<PersonAddressModel>) binder.getItemDataSource()).getBean();
        final String personId = GetUserCredentials.getUser().getId();
        final PersonAddress personAddress = PersonAddressFactory
                .getPersonAddress(personId, model.getDescription(), model.getPostalCode(), model.getAddressTypeId());
        return personAddress;
    }


    private PersonAddress getUpdateEntity(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final PersonAddressModel model = ((BeanItem<PersonAddressModel>) binder.getItemDataSource()).getBean();
        final PersonAddress personAddress = PeopleFacade.personAddressService.findById(personId, table.getValue().toString());
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
