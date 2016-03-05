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
import hashwork.client.content.common.demographics.forms.TitleListForm;
import hashwork.client.content.common.demographics.model.TitleListModel;
import hashwork.client.content.common.demographics.table.TitleListTable;
import hashwork.domain.ui.demographics.Title;
import hashwork.factories.ui.demographics.TitleListFactory;
import hashwork.services.ui.demographics.Impl.TitleListServiceImpl;
import hashwork.services.ui.demographics.TitleListService;

/**
 * Created by hashcode on 2015/08/18.
 */
public class TitleListTab extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {
    private final TitleListService titleListService = new TitleListServiceImpl();
    private final MainLayout main;
    private final TitleListForm form;
    private final TitleListTable table;

    public TitleListTab(MainLayout app) {
        main = app;
        form = new TitleListForm();
        table = new TitleListTable(main);
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
            final Title title = titleListService.findById(table.getValue().toString());
            final TitleListModel model = getModel(title);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            titleListService.save(getNewEntity(binder));
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
            titleListService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        titleListService.update(deleteEntity(binder));
        getHome();
    }


    private void getHome() {
        main.content.setSecondComponent(new CommonDemographicsMenu(main, "TITLE"));
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

    private Title getNewEntity(FieldGroup binder) {
        final TitleListModel bean = ((BeanItem<TitleListModel>) binder.getItemDataSource()).getBean();
        final Title Title = TitleListFactory.getTitleList(bean.getName());
        return Title;
    }

    private Title getUpdateEntity(FieldGroup binder) {
        final TitleListModel bean = ((BeanItem<TitleListModel>) binder.getItemDataSource()).getBean();
        final Title title = titleListService.findById(table.getValue().toString());
        final Title updatedTitle = new Title.Builder().copy(title).name(bean.getName()).build();
        return updatedTitle;
    }

    private Title deleteEntity(FieldGroup binder) {
        final TitleListModel bean = ((BeanItem<TitleListModel>) binder.getItemDataSource()).getBean();
        final Title title = titleListService.findById(table.getValue().toString());
        final Title updatedTitle = new Title.Builder().copy(title).state(DomainState.RETIRED.name()).build();
        return updatedTitle;
    }

    private TitleListModel getModel(Title title) {
        final TitleListModel model = new TitleListModel();
        model.setName(title.getName());
        return model;
    }

}
