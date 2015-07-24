package infoshare.client.content.setup.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.SetupMenu;
import infoshare.client.content.setup.forms.UserForm;
import infoshare.client.content.setup.models.UserModel;
import infoshare.client.content.setup.tables.UserTable;
import infoshare.domain.Role;
import infoshare.domain.User;
import infoshare.services.roles.Impl.RoleServiceImpl;
import infoshare.services.roles.RoleService;
import infoshare.services.users.Impl.UserServiceImpl;
import infoshare.services.users.UserService;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hashcode on 2015/06/23.
 */
public class UserView extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final UserForm form;
    private final UserTable table;

    private UserService userService = new UserServiceImpl();
    private RoleService roleService = new RoleServiceImpl();

    public UserView(MainLayout app) {
        main = app;
        form = new UserForm();
        table = new UserTable(main);
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
                final User user = userService.find(table.getValue().toString());
                final UserModel bean = getModel(user);
                form.binder.setItemDataSource(new BeanItem<>(bean));
                setReadFormProperties();
            }catch (Exception r){

            }

        }
    }
    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            userService.save(getNewEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        } catch(Exception dp){
            Notification.show("Username is already taken!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }
    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            userService.merge(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.HUMANIZED_MESSAGE);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }catch(Exception dp){
            Notification.show("Username is already taken!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }
    }
    private void deleteForm(FieldGroup binder) {
        userService.remove(getUpdateEntity(binder));
        getHome();
    }
    private User getNewEntity(FieldGroup binder) {

        final UserModel bean = ((BeanItem<UserModel>) binder.getItemDataSource()).getBean();
        Set<Role> userRoles = new HashSet<>();

        if (bean.getRoleIds() != null) {
            for (String roleId : bean.getRoleIds()) {
                Role role = roleService.find(roleId);
                if (role != null) {
                    userRoles.add(role);
                }
            }
        }
        final User user = new User.Builder(bean.getLastname())
                .firstname(bean.getFirstname())
                .role(userRoles)
                .enable(bean.isEnabled())
                .password(bean.getPassword())
                .username(bean.getUsername())
                .build();
        return user;
    }
    private User getUpdateEntity(FieldGroup binder) {

        final UserModel bean = ((BeanItem<UserModel>) binder.getItemDataSource()).getBean();
        Set<Role> userRoles = new HashSet<>();
        if (bean.getRoleIds()!= null) {
            for (String roleId : bean.getRoleIds()) {
                Role role = roleService.find(roleId);
                if (role != null) {
                    userRoles.add(role);
                }
            }
        }

        final User user = new User.Builder(bean.getLastname())
                .firstname(bean.getFirstname())
                .role(userRoles)
                .enable(bean.isEnabled())
                .username(bean.getUsername())
                .password(bean.getPassword())
                .id(table.getValue().toString())
                .build();
        return user;
    }
    private void getHome() {
        main.content.setSecondComponent(new SetupMenu(main, "LANDING"));
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
        form.rolesList.addValueChangeListener((Property.ValueChangeListener) this);
    }
    public UserModel getModel(User user) {
        Set<String> userRolesId = new HashSet<>();
        if (user.getRole() != null) {
            for (Role role : user.getRole()) {
                userRolesId.add(role.getId());
            }
        }
        UserModel model = new UserModel();
        model.setFirstname(user.getFirstname());
        model.setLastname(user.getLastname());
        model.setUsername(user.getUsername());
        model.setEnabled(user.isEnable());
        model.setRoleIds(userRolesId);
        model.setPassword(user.getPassword());
        return model;
    }

}
