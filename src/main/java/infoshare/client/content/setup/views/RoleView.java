package infoshare.client.content.setup.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.facade.RoleFacade;
import infoshare.app.util.DomainState;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.SetupMenu;
import infoshare.client.content.setup.forms.RoleForm;
import infoshare.client.content.setup.models.RoleModel;
import infoshare.client.content.setup.tables.RoleTable;
import infoshare.domain.demographics.Role;
import infoshare.factories.common.RolesListFactory;
import infoshare.services.roles.RoleService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hashcode on 2015/06/24.
 */
public class RoleView extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private RoleService roleService = RoleFacade.roleService;

    private final MainLayout main;
    private final RoleForm form;
    private final RoleTable table;

    public RoleView(MainLayout app) {
        main = app;
        form = new RoleForm();
        table = new RoleTable(main);
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
                final Role role = roleService.findById(table.getValue().toString());
                final RoleModel bean = getModel(role);
                form.binder.setItemDataSource(new BeanItem<>(bean));
            }catch (Exception e){
                    Notification.show(e.toString(), Notification.Type.WARNING_MESSAGE);
            }
            setReadFormProperties();
        }
    }
    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            roleService.save(getNewEntity(binder));
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
            roleService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }
    private void deleteForm(FieldGroup binder) {
        roleService.delete(getUpdateEntity(binder));
        getHome();
    }
    private Role getUpdateEntity(FieldGroup binder) {
        final RoleModel bean = ((BeanItem<RoleModel>) binder.getItemDataSource()).getBean();
        final Role role = new Role.Builder()
                .roleName(bean.getRoleName())
                .description(bean.getDescription())
                .id(table.getValue().toString())
                .build();
        return role;
    }
    private Role getNewEntity(FieldGroup binder) {
        final RoleModel bean = ((BeanItem<RoleModel>) binder.getItemDataSource()).getBean();
        Map<String,String> rolevals = new HashMap<>();
        rolevals.put("roleName",bean.getRoleName());
        rolevals.put("description",bean.getDescription());
        rolevals.put("state",DomainState.ACTIVE.name());
        final Role role = RolesListFactory.getRolesList(bean.getRoleName(),bean.getDescription());
        return role;
    }
    private void getHome() {
        main.content.setSecondComponent(new SetupMenu(main, "ROLES"));
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
    private RoleModel getModel(Role role) {
        final RoleModel model = new RoleModel();
        model.setDescription(role.getDescription());
        model.setRoleName(role.getName());
        return model;
    }
}