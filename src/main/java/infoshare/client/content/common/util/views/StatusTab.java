package infoshare.client.content.common.util.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.facade.UtilFacade;
import infoshare.app.util.DomainState;
import infoshare.client.content.MainLayout;
import infoshare.client.content.common.util.CommonUtilMenu;
import infoshare.client.content.common.util.forms.StatusForm;
import infoshare.client.content.common.util.models.StatusModel;
import infoshare.client.content.common.util.tables.StatusTable;
import infoshare.domain.util.Status;
import infoshare.factories.util.StatusFactory;


/**
 * Created by hashcode on 2015/10/12.
 */
public class StatusTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final StatusForm form;
    private final StatusTable table;

    public StatusTab(MainLayout app) {
        main = app;
        form = new StatusForm();
        table = new StatusTable(main);
        setSizeFull();
        addListeners();
        addComponent(form);
        addComponent(table);
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
            final Status status = UtilFacade.statusService.findById(table.getValue().toString());
            final StatusModel model = getModel(status);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }


    private void saveForm(FieldGroup binder) {
      try {
            binder.commit();
            UtilFacade.statusService.save(getNewEntity(binder));
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
            UtilFacade.statusService.save(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }


    private void getHome() {
        main.content.setSecondComponent(new CommonUtilMenu(main, "STATUS"));
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

    private void deleteForm(FieldGroup binder) {
        final Status status = UtilFacade.statusService.findById(table.getValue().toString());
        final Status updateStatus = new Status
                .Builder().copy(status)
                .state(DomainState.RETIRED.name())
                .build();
        UtilFacade.statusService.save(updateStatus);
        getHome();
    }

    private Status getNewEntity(FieldGroup binder) {
        final StatusModel model = ((BeanItem<StatusModel>) binder.getItemDataSource()).getBean();
        final Status status = StatusFactory.getStatus(model.getName(), model.getValue());
        return status;
    }

    private Status getUpdateEntity(FieldGroup binder) {
        final StatusModel model = ((BeanItem<StatusModel>) binder.getItemDataSource()).getBean();
        final Status status = UtilFacade.statusService.findById(table.getValue().toString());
        final Status updateStatus = new Status
                .Builder().copy(status)
                .name(model.getName())
                .value(model.getValue())
                .build();
        return updateStatus;
    }

    private StatusModel getModel(Status currency) {
        final StatusModel model = new StatusModel();
        model.setName(currency.getName());
        model.setValue(currency.getValue());
        return model;

    }

}
