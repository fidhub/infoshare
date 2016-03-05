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
import hashwork.client.content.profile.education.EducationMenu;
import hashwork.client.content.profile.education.forms.EducationHistoryForm;
import hashwork.client.content.profile.education.model.EducationHistoryModel;
import hashwork.client.content.profile.education.table.EducationHistoryTable;
import hashwork.domain.people.PersonEducationHistory;
import hashwork.factories.people.PersonEducationHistoryFactory;

/**
 * Created by hashcode on 2015/12/07.
 */
public class EducationHistoryTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;

    final private EducationHistoryForm form;
    final private EducationHistoryTable table;

    public EducationHistoryTab(MainLayout main) {
        this.main = main;
        form = new EducationHistoryForm();
        table = new EducationHistoryTable(main);
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
            final PersonEducationHistory personEducationHistory = PeopleFacade.personEducationHistoryService.findById(personId, table.getValue().toString());
            final EducationHistoryModel model = getModel(personEducationHistory);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            PeopleFacade.personEducationHistoryService.save(getNewEntity(binder));
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
            PeopleFacade.personEducationHistoryService.save(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final PersonEducationHistory personAddress = PeopleFacade.personEducationHistoryService.findById(personId, table.getValue().toString());
        final PersonEducationHistory updatedPersonAddress = new PersonEducationHistory
                .Builder().copy(personAddress)
                .state(DomainState.RETIRED.name())
                .build();
        PeopleFacade.personEducationHistoryService.save(updatedPersonAddress);
        getHome();
    }


    private void getHome() {
        main.content.setSecondComponent(new EducationMenu(main, "HISTORY"));
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


    private PersonEducationHistory getNewEntity(FieldGroup binder) {
        final EducationHistoryModel model = ((BeanItem<EducationHistoryModel>) binder.getItemDataSource()).getBean();
        final String personId = GetUserCredentials.getUser().getId();
        final PersonEducationHistory personEducationHistory = PersonEducationHistoryFactory
                .getPersonEducationHistory(personId,
                        model.getInstitutionName(),
                        model.getInstitutionLocation(),
                        model.getYearOfGraduation(),
                        model.getEducationTypeId(),
                        model.getDegreeId(),
                        model.getNotes());
        return personEducationHistory;
    }


    private PersonEducationHistory getUpdateEntity(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final EducationHistoryModel model = ((BeanItem<EducationHistoryModel>) binder.getItemDataSource()).getBean();
        final PersonEducationHistory personEducationHistory = PeopleFacade.personEducationHistoryService.findById(personId, table.getValue().toString());
        final PersonEducationHistory updatedPersonAddress = new PersonEducationHistory
                .Builder()
                .copy(personEducationHistory)
                .degreeId(model.getDegreeId())
                .educationTypeId(model.getEducationTypeId())
                .institutionLocation(model.getInstitutionLocation())
                .institutionName(model.getInstitutionName())
                .notes(model.getNotes())
                .build();
        return updatedPersonAddress;
    }

    private EducationHistoryModel getModel(PersonEducationHistory personEducationHistory) {
        final EducationHistoryModel model = new EducationHistoryModel();
        model.setDegreeId(personEducationHistory.getDegreeId());
        model.setEducationTypeId(personEducationHistory.getEducationTypeId());
        model.setInstitutionName(personEducationHistory.getInstitutionName());
        model.setInstitutionLocation(personEducationHistory.getInstitutionLocation());
        model.setNotes(personEducationHistory.getEducationTypeId());
        model.setYearOfGraduation(personEducationHistory.getYearOfGraduation());
        return model;
    }
}
