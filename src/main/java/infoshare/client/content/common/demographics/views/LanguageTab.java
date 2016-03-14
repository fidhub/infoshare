package infoshare.client.content.common.demographics.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.facade.DemographicsFacade;
import infoshare.app.util.DomainState;
import infoshare.client.content.MainLayout;
import infoshare.client.content.common.demographics.CommonDemographicsMenu;
import infoshare.client.content.common.demographics.forms.LanguageForm;
import infoshare.client.content.common.demographics.model.LanguageModel;
import infoshare.client.content.common.demographics.table.LanguageTable;
import infoshare.domain.demographics.Language;
import infoshare.factories.common.LanguageFactory;


/**
 * Created by hashcode on 2015/08/18.
 */
public class LanguageTab extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final LanguageForm form;
    private final LanguageTable table;

    public LanguageTab(MainLayout app) {
        main = app;
        form = new LanguageForm();
        table = new LanguageTable(main);
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
        if (property == table) {
            final Language language = DemographicsFacade.getLanguageServiceInstance().findById(table.getValue().toString());
            final LanguageModel model = getModel(language);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            DemographicsFacade.getLanguageServiceInstance().save(getNewEntity(binder));
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
            DemographicsFacade.getLanguageServiceInstance().update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final Language language = DemographicsFacade.getLanguageServiceInstance().findById(table.getValue().toString());
        final Language updatedLanguage = new Language.Builder()
                .copy(language)
                .name(DomainState.RETIRED.name())
                .build();
        DemographicsFacade.getLanguageServiceInstance().save(updatedLanguage);
        getHome();
    }


    private void getHome() {
        main.content.setSecondComponent(new CommonDemographicsMenu(main, "LANGUAGE"));
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
        //Buttons Behaviou
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
        //Register Table Listerners
        table.addValueChangeListener(this);
    }

    private Language getNewEntity(FieldGroup binder) {
        final LanguageModel bean = ((BeanItem<LanguageModel>) binder.getItemDataSource()).getBean();
        final Language language = LanguageFactory.getLanguage(bean.getName());
        return language;
    }

    private Language getUpdateEntity(FieldGroup binder) {
        final LanguageModel bean = ((BeanItem<LanguageModel>) binder.getItemDataSource()).getBean();
        final Language language = DemographicsFacade.getLanguageServiceInstance().findById(table.getValue().toString());
        final Language updatedLanguage = new Language.Builder()
                .copy(language)
                .name(bean.getName())
                .build();
        return updatedLanguage;
    }

    private LanguageModel getModel(Language language) {
        final LanguageModel model = new LanguageModel();
        model.setName(language.getName());
        return model;
    }
}
