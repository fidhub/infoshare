package infoshare.viewUI.container.common.systemValues.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.facade.SourceFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.common.systemValues.SystemValues;
import infoshare.viewUI.container.common.systemValues.forms.ContentSourceForm;
import infoshare.viewUI.container.common.systemValues.models.SourceModel;
import infoshare.viewUI.container.common.systemValues.tables.ContentSourceTable;
import infoshare.domain.content.Source;
import infoshare.factories.content.SourceFactory;
import infoshare.services.ContentFiles.source.SourceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by codex on 2015/06/26.
 */
public class ContentSourceView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    @Autowired
    private SourceService sourceService = SourceFacade.sourceService;

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
            try {
                final Source source = sourceService.findById(OrganisationUtil.getCompanyCode(),table.getValue().toString());
                final SourceModel bean = getModel(source);
                form.binder.setItemDataSource(new BeanItem<>(bean));
                setReadFormProperties();
            }catch (Exception r) {
            }
        }
    }
    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            sourceService.save(getNewEntity(binder));
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
            sourceService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }
    private void deleteForm(FieldGroup binder) {
        sourceService.delete(getUpdateEntity(binder));
        getHome();
    }
    private Source getNewEntity(FieldGroup binder) {
        final SourceModel bean = ((BeanItem<SourceModel>) binder.getItemDataSource()).getBean();
        Map<String,String> sourceVals = new HashMap<>();
        sourceVals.put("name",bean.getName());
        sourceVals.put("description",bean.getDescription());
        final Source source = SourceFactory.getSource(sourceVals);
        return source;
    }
    private Source getUpdateEntity(FieldGroup binder) {
        final SourceModel bean = ((BeanItem<SourceModel>) binder.getItemDataSource()).getBean();
        final Source source = new Source.Builder()
                .name(bean.getName())
                .description(bean.getDescription())
                .id(table.getValue().toString())
                .build();
        return source;
    }
    private void getHome() {
      main.content.setSecondComponent(new SystemValues(main, "SOURCE"));
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
    private SourceModel getModel(Source source) {
        final SourceModel model = new SourceModel();
        model.setDescription(source.getDescription());
        model.setName(source.getName());
        return model;
    }
}
