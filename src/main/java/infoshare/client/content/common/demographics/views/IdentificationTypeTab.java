package infoshare.client.content.common.demographics.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import hashwork.app.util.DomainState;
import hashwork.client.content.MainLayout;
import hashwork.client.content.common.demographics.CommonDemographicsMenu;
import hashwork.client.content.common.demographics.forms.IdentificationTypeForm;
import hashwork.client.content.common.demographics.model.IdentificationTypeModel;
import hashwork.client.content.common.demographics.table.IdentificationTypeTable;
import hashwork.domain.ui.demographics.IdentificationType;
import hashwork.factories.ui.demographics.IdentificationTypeFactory;
import hashwork.services.ui.demographics.IdentificationTypeService;
import hashwork.services.ui.demographics.Impl.IdentificationTypeServiceImpl;

/**
 * Created by hashcode on 2015/08/18.
 */
public class IdentificationTypeTab extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {
    private final IdentificationTypeService identificationTypeService = new IdentificationTypeServiceImpl();
    private final MainLayout main;
    private final IdentificationTypeForm form;
    private final IdentificationTypeTable table;

    public IdentificationTypeTab(MainLayout app) {
        main = app;
        form = new IdentificationTypeForm();
        table = new IdentificationTypeTable(main);
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
            final IdentificationType identificationType = identificationTypeService.findById(table.getValue().toString());
            final IdentificationTypeModel model = getModel(identificationType);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            identificationTypeService.save(getNewEntity(binder));
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
            identificationTypeService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final IdentificationType identificationType = identificationTypeService.findById(table.getValue().toString());
        final IdentificationType updatedIdentificationType = new IdentificationType
                .Builder()
                .copy(identificationType)
                .state(DomainState.RETIRED.name())
                .build();

        identificationTypeService.save(updatedIdentificationType);
        getHome();
    }



    private void getHome() {
        main.content.setSecondComponent(new CommonDemographicsMenu(main, "ID"));
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

    private IdentificationType getNewEntity(FieldGroup binder) {
        final IdentificationTypeModel bean = ((BeanItem<IdentificationTypeModel>) binder.getItemDataSource()).getBean();
        final IdentificationType identificationType = IdentificationTypeFactory
                .getIdentificationType(bean.getName(), bean.getDescription());

        return identificationType;
    }

    private IdentificationType getUpdateEntity(FieldGroup binder) {
        final IdentificationTypeModel bean = ((BeanItem<IdentificationTypeModel>) binder.getItemDataSource()).getBean();
        final IdentificationType identificationType = identificationTypeService.findById(table.getValue().toString());
        final IdentificationType updatedIdentificationType = new IdentificationType
                .Builder()
                .copy(identificationType)
                .description(bean.getDescription())
                .name(bean.getName())
                .build();
        return updatedIdentificationType;
    }

    private IdentificationTypeModel getModel(IdentificationType identificationType) {
        final IdentificationTypeModel model = new IdentificationTypeModel();
        model.setDescription(identificationType.getDescription());
        model.setName(identificationType.getName());
        return model;
    }
}

