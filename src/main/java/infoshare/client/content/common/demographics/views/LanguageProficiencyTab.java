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
import infoshare.client.content.common.demographics.forms.LanguageProficiencyForm;
import infoshare.client.content.common.demographics.model.LanguageProficiencyModel;
import infoshare.client.content.common.demographics.table.LanguageProficiencyTable;
import infoshare.domain.demographics.LanguageProficiency;
import infoshare.factories.common.LanguageProficiencyFactory;


/**
 * Created by hashcode on 2015/08/18.
 */
public class LanguageProficiencyTab extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final LanguageProficiencyForm form;
    private final LanguageProficiencyTable table;

    public LanguageProficiencyTab(MainLayout app) {
        main = app;
        form = new LanguageProficiencyForm();
        table = new LanguageProficiencyTable(main);
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

            final LanguageProficiency languageProficiency = DemographicsFacade.getLanguageProficiencyServiceInstance().findById(table.getValue().toString());
            final LanguageProficiencyModel model = getModel(languageProficiency);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            DemographicsFacade.getLanguageProficiencyServiceInstance().save(getNewEntity(binder));
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
            DemographicsFacade.getLanguageProficiencyServiceInstance().save(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final LanguageProficiency languageProficiency = DemographicsFacade.getLanguageProficiencyServiceInstance().findById(table.getValue().toString());
        final LanguageProficiency updatedLanguageProficiency = new LanguageProficiency
                .Builder()
                .copy(languageProficiency)
                .state(DomainState.RETIRED.name())
                .build();
        DemographicsFacade.getLanguageProficiencyServiceInstance().save(updatedLanguageProficiency);
        getHome();
    }


    private void getHome() {
        main.content.setSecondComponent(new CommonDemographicsMenu(main, "PROFICIENCY"));
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

    private LanguageProficiency getNewEntity(FieldGroup binder) {
        final LanguageProficiencyModel bean = ((BeanItem<LanguageProficiencyModel>) binder.getItemDataSource()).getBean();
        final LanguageProficiency languageProficiency = LanguageProficiencyFactory
                .getLanguageProficiency(bean.getName());
        return languageProficiency;
    }

    private LanguageProficiency getUpdateEntity(FieldGroup binder) {
        final LanguageProficiencyModel bean = ((BeanItem<LanguageProficiencyModel>) binder.getItemDataSource()).getBean();
        final LanguageProficiency languageProficiency = DemographicsFacade.getLanguageProficiencyServiceInstance().findById(table.getValue().toString());
        final LanguageProficiency updatedLanguageProficiency = new LanguageProficiency
                .Builder()
                .copy(languageProficiency)
                .name(bean.getName())
                .build();

        return updatedLanguageProficiency;
    }

    private LanguageProficiencyModel getModel(LanguageProficiency languageProficiency) {
        final LanguageProficiencyModel model = new LanguageProficiencyModel();
        model.setName(languageProficiency.getName());
        return model;
    }
}
