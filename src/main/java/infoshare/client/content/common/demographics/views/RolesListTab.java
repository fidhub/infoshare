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
import infoshare.client.content.common.demographics.forms.RolesListForm;
import infoshare.client.content.common.demographics.model.RolesListModel;
import infoshare.client.content.common.demographics.table.RolesListTable;
import infoshare.domain.demographics.Role;
import infoshare.factories.common.RolesListFactory;


/**
 * Created by hashcode on 2015/08/18.
 */
public class RolesListTab extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final RolesListForm form;
    private final RolesListTable table;
    private String id;

    public RolesListTab(MainLayout app) {
        main = app;
        form = new RolesListForm();
        table = new RolesListTable(main);
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
            id = table.getValue().toString();
            final Role role = DemographicsFacade.rolesListService.findById(table.getValue().toString());
            final RolesListModel model = getModel(role);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            DemographicsFacade.rolesListService.save(getNewEntity(binder));
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
            DemographicsFacade.rolesListService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        final Role role = DemographicsFacade.rolesListService.findById(table.getValue().toString());
        final Role updatedRoleList = new Role
                .Builder().copy(role)
                .state(DomainState.RETIRED.name())
                .build();
        DemographicsFacade.rolesListService.save(updatedRoleList);
        getHome();
    }



    private void getHome() {
        main.content.setSecondComponent(new CommonDemographicsMenu(main, "LANDING"));
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

    private Role getNewEntity(FieldGroup binder) {
        final RolesListModel bean = ((BeanItem<RolesListModel>) binder.getItemDataSource()).getBean();
        final Role Role = RolesListFactory.getRolesList(bean.getRoleName(), bean.getDescription());
        return Role;
    }

    private Role getUpdateEntity(FieldGroup binder) {
        final RolesListModel bean = ((BeanItem<RolesListModel>) binder.getItemDataSource()).getBean();
        final Role role = DemographicsFacade.rolesListService.findById(table.getValue().toString());
        final Role updatedRoleList = new Role
                .Builder().copy(role)
                .description(bean.getDescription())
                .roleName(bean.getRoleName()).build();
        return updatedRoleList;
    }

    private RolesListModel getModel(Role role) {
        final RolesListModel model = new RolesListModel();
        model.setDescription(role.getDescription());
        model.setRoleName(role.getName());

        return model;
    }
}
