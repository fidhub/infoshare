package infoshare.client.content.systemValues.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.facade.ContentTypeFacade;
import infoshare.client.content.MainLayout;
import infoshare.client.content.systemValues.SystemValues;
import infoshare.client.content.systemValues.forms.ContentTypeForm;
import infoshare.client.content.systemValues.models.ContentTypeModel;
import infoshare.client.content.systemValues.tables.ContentTypeTable;
import infoshare.domain.content.ContentType;
import infoshare.factories.content.ContentTypeFactory;
import infoshare.services.ContentType.ContentTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by codex on 2015/06/25.
 */
public class ContentTypeView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    @Autowired
    private ContentTypeService contentTypeService = ContentTypeFacade.contentTypeService;

    private final MainLayout main;
    private final ContentTypeForm form;
    private final ContentTypeTable table;

    public ContentTypeView(MainLayout mainApp) {
        this.main = mainApp;
        this.form = new ContentTypeForm();
        this.table = new ContentTypeTable(main);
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
            try {
                final ContentType contentType = contentTypeService.findById(table.getValue().toString());
                final ContentTypeModel bean = getModel(contentType);
                form.binder.setItemDataSource(new BeanItem<>(bean));
                setReadFormProperties();
            }catch (Exception r){

            }
        }
    }
    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            contentTypeService.save(getNewEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.HUMANIZED_MESSAGE);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }
    }
    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            contentTypeService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.HUMANIZED_MESSAGE);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }
    }
    private void deleteForm(FieldGroup binder) {
        contentTypeService.delete(getUpdateEntity(binder));
        getHome();
    }
    private ContentType getUpdateEntity(FieldGroup binder) {
        final ContentTypeModel bean = ((BeanItem<ContentTypeModel>) binder.getItemDataSource()).getBean();
        final ContentType contentType =  new ContentType.Builder()
                .name(bean.getName())
                .description(bean.getDescription())
                .id(table.getValue().toString())
                .build();
        return contentType;
    }
    private ContentType getNewEntity(FieldGroup binder) {
        final ContentTypeModel bean = ((BeanItem<ContentTypeModel>) binder.getItemDataSource()).getBean();
        Map<String,String> contentTypeVals = new HashMap<>();
        contentTypeVals.put("contentName",bean.getName());
        contentTypeVals.put("description",bean.getDescription());
        final ContentType contentType = ContentTypeFactory.getContentType(contentTypeVals);
        return contentType;
    }
    private void getHome() {
      main.content.setSecondComponent(new SystemValues(main, "LANDING"));
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
        //Register Table Listerners
        table.addValueChangeListener(this);
    }
    private ContentTypeModel getModel(ContentType contentType) {
        final ContentTypeModel model = new ContentTypeModel();
        model.setName(contentType.getName());
        model.setDescription(contentType.getDescription());
        return model;
    }
}
