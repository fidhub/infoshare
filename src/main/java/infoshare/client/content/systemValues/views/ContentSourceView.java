package infoshare.client.content.systemValues.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.ContentMenu;
import infoshare.client.content.systemValues.forms.ContentSourceForm;
import infoshare.client.content.systemValues.models.SourceModel;
import infoshare.client.content.systemValues.tables.ContentSourceTable;
import infoshare.domain.Source;
import infoshare.services.source.SourceService;
import infoshare.services.source.sourceServiceImpl.SourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by codex on 2015/06/26.
 */
public class ContentSourceView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    @Autowired
    private SourceService sourceService = new SourceServiceImpl();

    private final MainLayout main;
    private final ContentSourceForm form;
    private final ContentSourceTable table;

    public ContentSourceView(MainLayout mainApp) {
        this.main = mainApp;
        this.form = new ContentSourceForm();
        this.table = new ContentSourceTable(main);
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
            final Source source = sourceService.find(table.getValue().toString());
            final SourceModel bean = getModel(source);
            form.binder.setItemDataSource(new BeanItem<>(bean));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            sourceService.save(getUpdateEntity(binder));
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
            sourceService.merge(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final Source source = getUpdateEntity(binder);
        sourceService.remove(source);
        getHome();
    }
    private Source getUpdateEntity(FieldGroup binder) {
        final SourceModel bean = ((BeanItem<SourceModel>) binder.getItemDataSource()).getBean();
        final Source source = new Source.Builder(bean.getName())
                .description(bean.getDescription())
                .id(bean.getId())
                .build();
        return source;
    }

    private void getHome() {
      main.content.setSecondComponent(new ContentMenu(main, "SOURCE"));
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
        form.save.addClickListener((Button.ClickListener) this);
        form.edit.addClickListener((Button.ClickListener) this);
        form.cancel.addClickListener((Button.ClickListener) this);
        form.update.addClickListener((Button.ClickListener) this);
        form.delete.addClickListener((Button.ClickListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private SourceModel getModel(Source val) {
        final SourceModel model = new SourceModel();
        final Source source = sourceService.find(val.getId());
        model.setDescription(source.getDescription());
        model.setId(source.getId());
        model.setName(source.getName());
        return model;
    }
}
