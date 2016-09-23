package infoshare.viewUI.container.profile.demographics.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.DomainState;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.profile.demographics.DemographicsMenu;
import infoshare.viewUI.container.profile.demographics.forms.PersonLanguagesForm;
import infoshare.viewUI.container.profile.demographics.model.PersonLanguageModel;
import infoshare.viewUI.container.profile.demographics.table.PersonLanguageTable;
import infoshare.domain.person.PersonLanguage;
import infoshare.factories.person.PersonLanguageFactory;

/**
 * Created by hashcode on 2015/12/07.
 */
public class LanguagesTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;

    final private PersonLanguagesForm form;
    final private PersonLanguageTable table;

    public LanguagesTab(MainLayout main) {
        this.main = main;
        form = new PersonLanguagesForm();
        table = new PersonLanguageTable(main);
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
            final PersonLanguage personLanguage = PeopleFacade.personLanguageService.findById(personId, table.getValue().toString());
            final PersonLanguageModel model = getModel(personLanguage);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            PeopleFacade.personLanguageService.save(getNewEntity(binder));
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
            PeopleFacade.personLanguageService.save(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final PersonLanguage personLanguage = PeopleFacade.personLanguageService.findById(personId, table.getValue().toString());
        final PersonLanguage updatedPersonAddress = new PersonLanguage
                .Builder()
                .copy(personLanguage)
                .state(DomainState.RETIRED.name())
                .build();
        PeopleFacade.personLanguageService.save(updatedPersonAddress);
        getHome();
    }


    private void getHome() {
        main.content.setSecondComponent(new DemographicsMenu(main, "LANGUAGES"));
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


    private PersonLanguage getNewEntity(FieldGroup binder) {
        final PersonLanguageModel model = ((BeanItem<PersonLanguageModel>) binder.getItemDataSource()).getBean();
        final String personId = GetUserCredentials.getUser().getId();
        final PersonLanguage personLanguage = PersonLanguageFactory
                .getPersonLanguage(personId, model.getLanguageId(), model.getReading(), model.getWriting(), model.getSpeaking());
        return personLanguage;
    }


    private PersonLanguage getUpdateEntity(FieldGroup binder) {
        final String personId = GetUserCredentials.getUser().getId();
        final PersonLanguageModel model = ((BeanItem<PersonLanguageModel>) binder.getItemDataSource()).getBean();
        final PersonLanguage personLanguage = PeopleFacade.personLanguageService.findById(personId, table.getValue().toString());
        final PersonLanguage updatedPersonLanguage = new PersonLanguage
                .Builder()
                .copy(personLanguage)
                .languageId(model.getLanguageId())
                .writing(model.getWriting())
                .reading(model.getReading())
                .speaking(model.getSpeaking())
                .build();
        return updatedPersonLanguage;
    }

    private PersonLanguageModel getModel(PersonLanguage personLanguage) {
        final PersonLanguageModel model = new PersonLanguageModel();
        model.setLanguageId(personLanguage.getLanguageId());
        model.setReading(personLanguage.getReading());
        model.setSpeaking(personLanguage.getSpeaking());
        model.setWriting(personLanguage.getWriting());
        return model;
    }
}
