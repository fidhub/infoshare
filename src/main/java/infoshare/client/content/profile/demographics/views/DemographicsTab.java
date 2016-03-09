package infoshare.client.content.profile.demographics.views;

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
import infoshare.client.content.profile.demographics.DemographicsMenu;
import infoshare.client.content.profile.demographics.forms.PersonDemographicsForm;
import infoshare.client.content.profile.demographics.model.PersonDemographicsModel;
import infoshare.client.content.profile.demographics.table.PersonDemographicsTable;
import infoshare.domain.person.PersonDemographics;
import infoshare.factories.person.PersonDemographicsFactory;


/**
 * Created by hashcode on 2015/12/07.
 */
public class DemographicsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;

    final private PersonDemographicsForm form;
    final private PersonDemographicsTable table;

    public DemographicsTab(MainLayout main) {
        this.main = main;
        form = new PersonDemographicsForm();
        table = new PersonDemographicsTable(main);
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
            System.out.println(" Person ID is " + personId + " The ID ");
            final PersonDemographics personDemographics = PeopleFacade.personDemographicsService.findById(personId, table.getValue().toString());
            final PersonDemographicsModel model = getModel(personDemographics);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            PeopleFacade.personDemographicsService.save(getNewEntity(binder));
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
            PeopleFacade.personDemographicsService.save(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final PersonDemographics personDemographic = PeopleFacade.personDemographicsService.findById(personId, table.getValue().toString());
        final PersonDemographics updatedPersonDemographics = new PersonDemographics
                .Builder().copy(personDemographic)
                .state(DomainState.RETIRED.name())
                .build();
        PeopleFacade.personDemographicsService.save(updatedPersonDemographics);
        getHome();
    }


    private void getHome() {
        main.content.setSecondComponent(new DemographicsMenu(main, "DEMOGRAPHICS"));
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


    private PersonDemographics getNewEntity(FieldGroup binder) {
        final PersonDemographicsModel model = ((BeanItem<PersonDemographicsModel>) binder.getItemDataSource()).getBean();
        final String personId = GetUserCredentials.getUser().getId();
        final PersonDemographics personDemographics = PersonDemographicsFactory
                .getPersonDemographics(personId, model.getGenderId(), model.getDateOfBirth());
        return personDemographics;
    }


    private PersonDemographics getUpdateEntity(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final PersonDemographicsModel model = ((BeanItem<PersonDemographicsModel>) binder.getItemDataSource()).getBean();
        final PersonDemographics personAddress = PeopleFacade.personDemographicsService.findById(personId, table.getValue().toString());
        final PersonDemographics updatedPersonAddress = new PersonDemographics
                .Builder()
                .copy(personAddress)
                .dateOfBirth(model.getDateOfBirth())
                .genderId(model.getGenderId())
                .build();
        return updatedPersonAddress;
    }

    private PersonDemographicsModel getModel(PersonDemographics personDemographics) {
        final PersonDemographicsModel model = new PersonDemographicsModel();
        model.setDateOfBirth(personDemographics.getDateOfBirth());
        model.setGenderId(personDemographics.getGenderId());
        return model;
    }
}
