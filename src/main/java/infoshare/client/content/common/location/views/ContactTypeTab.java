package infoshare.client.content.common.location.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.facade.LocationFacade;
import infoshare.client.content.MainLayout;
import infoshare.client.content.common.location.CommonLocationMenu;
import infoshare.client.content.common.location.forms.ContactTypeForm;
import infoshare.client.content.common.location.models.ContactTypeModel;
import infoshare.client.content.common.location.tables.ContactTypeTable;
import infoshare.domain.location.ContactType;
import infoshare.factories.location.ContactTypeFactory;


/**
 * Created by hashcode on 2015/09/07.
 */
public class ContactTypeTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;

    private final ContactTypeForm form;
    private final ContactTypeTable table;

    public ContactTypeTab(MainLayout app) {
        main = app;
        form = new ContactTypeForm();
        table = new ContactTypeTable(main);
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
            final ContactType contactype = LocationFacade.contactListService.findById(table.getValue().toString());
            final ContactTypeModel model = getModel(contactype);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            LocationFacade.contactListService.save(getNewEntity(binder));
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
            LocationFacade.contactListService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        ContactType contactype = LocationFacade.contactListService.findById(table.getValue().toString());
        LocationFacade.contactListService.delete(contactype);
        getHome();
    }



    private void getHome() {
        main.content.setSecondComponent(new CommonLocationMenu(main, "CONTACT"));
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

    private ContactType getNewEntity(FieldGroup binder) {
        final ContactTypeModel bean = ((BeanItem<ContactTypeModel>) binder.getItemDataSource()).getBean();
        final ContactType Contactype = ContactTypeFactory.contactType(bean.getName());
        return Contactype;
    }

    private ContactType getUpdateEntity(FieldGroup binder) {
        final ContactTypeModel bean = ((BeanItem<ContactTypeModel>) binder.getItemDataSource()).getBean();
        final ContactType Contactype = LocationFacade.contactListService.findById(table.getValue().toString());
        final ContactType updatedContactype = new ContactType
                .Builder().copy(Contactype)
                .name(bean.getName())
                .build();
        return updatedContactype;
    }

    private ContactTypeModel getModel(ContactType contactype) {
        final ContactTypeModel model = new ContactTypeModel();
        model.setName(contactype.getName());
        return model;
    }
}
