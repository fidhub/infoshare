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
import infoshare.client.content.common.util.forms.StorageUrlForm;
import infoshare.client.content.common.util.models.StorageUrlModel;
import infoshare.client.content.common.util.tables.StorageUrlTable;
import infoshare.domain.storage.StorageUrl;
import infoshare.factories.storage.StorageUrlFactory;


/**
 * Created by hashcode on 2016/01/06.
 */
public class StorageUrlTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final StorageUrlForm form;
    private final StorageUrlTable table;

    public StorageUrlTab(MainLayout app) {
        main = app;
        form = new StorageUrlForm();
        table = new StorageUrlTable(main);
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
            final StorageUrl StorageUrl = UtilFacade.storageUrlService.getById(table.getValue().toString());
            final StorageUrlModel model = getModel(StorageUrl);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            UtilFacade.storageUrlService.save(getNewEntity(binder));
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
            UtilFacade.storageUrlService.save(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }


    private void getHome() {
        main.content.setSecondComponent(new CommonUtilMenu(main, "LINKS"));
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
        final StorageUrl StorageUrl = UtilFacade.storageUrlService.getById(table.getValue().toString());
        final StorageUrl updateStorageUrl = new StorageUrl
                .Builder().copy(StorageUrl)
                .url(DomainState.RETIRED.name())
                .build();
        UtilFacade.storageUrlService.save(updateStorageUrl);
        getHome();
    }

    private StorageUrl getNewEntity(FieldGroup binder) {
        final StorageUrlModel model = ((BeanItem<StorageUrlModel>) binder.getItemDataSource()).getBean();
        final StorageUrl StorageUrl = StorageUrlFactory.
                getStorageUrl(model.getName(), model.getUrl());
        return StorageUrl;
    }

    private StorageUrl getUpdateEntity(FieldGroup binder) {
        final StorageUrlModel model = ((BeanItem<StorageUrlModel>) binder.getItemDataSource()).getBean();
        final StorageUrl StorageUrl = UtilFacade.storageUrlService.getById(table.getValue().toString());
        final StorageUrl updateStorageUrl = new StorageUrl
                .Builder().copy(StorageUrl)
                .name(model.getName())
                .url(model.getUrl())
                .build();
        return updateStorageUrl;
    }

    private StorageUrlModel getModel(StorageUrl storageUrl) {
        final StorageUrlModel model = new StorageUrlModel();
        model.setName(storageUrl.getName());
        model.setUrl(storageUrl.getUrl());
        return model;
    }
}