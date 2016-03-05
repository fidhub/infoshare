package infoshare.client.content.profile.demographics.views;

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
import hashwork.client.content.profile.demographics.DemographicsMenu;
import hashwork.client.content.profile.demographics.forms.PersonIdentitiesForm;
import hashwork.client.content.profile.demographics.model.PersonIdentitiesModel;
import hashwork.client.content.profile.demographics.table.PersonIdentitiesTable;
import hashwork.domain.people.PersonIdentity;
import hashwork.factories.people.PersonIdentityFactory;

/**
 * Created by hashcode on 2015/12/07.
 */
public class IdentitiesTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;

    final private PersonIdentitiesForm form;
    final private PersonIdentitiesTable table;

    public IdentitiesTab(MainLayout main) {
        this.main = main;
        form = new PersonIdentitiesForm();
        table = new PersonIdentitiesTable(main);
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
            final PersonIdentity personIdentity = PeopleFacade.personIdentityService.findById(personId, table.getValue().toString());
            final PersonIdentitiesModel model = getModel(personIdentity);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            PeopleFacade.personIdentityService.save(getNewEntity(binder));
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
            PeopleFacade.personIdentityService.save(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final PersonIdentity personIdentity = PeopleFacade.personIdentityService.findById(personId, table.getValue().toString());
        final PersonIdentity updatedPersonIdentity = new PersonIdentity
                .Builder().copy(personIdentity)
                .state(DomainState.RETIRED.name())
                .build();
        PeopleFacade.personIdentityService.save(updatedPersonIdentity);
        getHome();
    }


    private void getHome() {
        main.content.setSecondComponent(new DemographicsMenu(main, "IDENTITIES"));
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


    private PersonIdentity getNewEntity(FieldGroup binder) {
        final PersonIdentitiesModel model = ((BeanItem<PersonIdentitiesModel>) binder.getItemDataSource()).getBean();
        final String personId = GetUserCredentials.getUser().getId();
        final PersonIdentity personIdentity = PersonIdentityFactory
                .getPersonIdentity(personId, model.getIdType(), model.getIdValue(), model.isPreffered());
        return personIdentity;
    }


    private PersonIdentity getUpdateEntity(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final PersonIdentitiesModel model = ((BeanItem<PersonIdentitiesModel>) binder.getItemDataSource()).getBean();
        final PersonIdentity personIdentity = PeopleFacade.personIdentityService.findById(personId, table.getValue().toString());
        final PersonIdentity updatedPersonAddress = new PersonIdentity
                .Builder()
                .copy(personIdentity)
                .idType(model.getIdType())
                .idValue(model.getIdValue())
                .preffered(model.isPreffered())
                .build();
        return updatedPersonAddress;
    }

    private PersonIdentitiesModel getModel(PersonIdentity personIdentity) {
        final PersonIdentitiesModel model = new PersonIdentitiesModel();
        model.setIdType(personIdentity.getIdType());
        model.setIdValue(personIdentity.getIdValue());
        model.setPreffered(personIdentity.isPreffered());
        return model;
    }
}
