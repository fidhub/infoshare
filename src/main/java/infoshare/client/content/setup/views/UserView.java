package infoshare.client.content.setup.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.SetupMenu;
import infoshare.client.content.setup.forms.UserForm;
import infoshare.client.content.setup.models.UserModel;
import infoshare.client.content.setup.tables.AddressTable;
import infoshare.client.content.setup.tables.UserTable;
import infoshare.domain.Role;
import infoshare.domain.User;
import infoshare.services.roles.Impl.RoleServiceImpl;
import infoshare.services.roles.RoleService;
import infoshare.services.users.Impl.UserServiceImpl;
import infoshare.services.users.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/23.
 */
public class UserView extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final UserForm userForm;
    private final UserTable table;
    private UserService userService = new UserServiceImpl();
    private RoleService roleService = new RoleServiceImpl();
    private AddressView addressView ;
    private ContactView contactView;
    public UserView(MainLayout app) {
        main = app;
        userForm = new UserForm();
        table = new UserTable(main);
        setSizeFull();
        addComponent(userForm);
        addComponent(table);
        addListeners();
    }
    @Override
    public void buttonClick(Button.ClickEvent event) {

        final Button source = event.getButton();
        if (source == userForm.save) {
            saveForm(userForm.binder);
        } else if (source == userForm.edit) {
            setEditFormProperties();
        } else if (source == userForm.cancel) {
            getHome();
        } else if (source == userForm.update) {
            saveEditedForm(userForm.binder);
        } else if (source == userForm.delete) {
            deleteForm(userForm.binder);
        }else if(source == userForm.addNewAddress){
            try {
                addressView.setModal(true);
                getUI().addWindow(addressView);
            }catch (Exception e){
                Notification.show("Select the user first",Notification.Type.HUMANIZED_MESSAGE);
            }
        }else if(source == userForm.addNewContact){
            try {
                contactView.setModal(true);
                getUI().addWindow(contactView);
            }catch (Exception e){
                Notification.show("Select the user first",Notification.Type.HUMANIZED_MESSAGE);
            }
        }
    }


    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            try {
                AddressTable.userID = table.getValue().toString();
                addressView = new AddressView(main);
                contactView = new ContactView(main);
                final User user = userService.find(table.getValue().toString());
                final UserModel bean = getModel(user);
                userForm.binder.setItemDataSource(new BeanItem<>(bean));
                setReadFormProperties();
            }catch (Exception r){
            }
        }
    }
    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            userService.save(getUserNewEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.HUMANIZED_MESSAGE);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        } catch(Exception dp){
            Notification.show("Username is already taken!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }
    }
    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            userService.merge(getUserUpdateEntity(binder));
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
        userService.remove(getUserUpdateEntity(binder));
        getHome();
    }
    private User getUserNewEntity(FieldGroup binder) {
        final UserModel bean = ((BeanItem<UserModel>) binder.getItemDataSource()).getBean();
        final User user = new User.Builder(bean.getLastName())
                .firstname(bean.getFirstName())
                .role(bean.getRole())
                .enable(bean.isEnable())
                .password(bean.getPassword())
                .username(bean.getUsername())
                .othername(bean.getOtherName())
                .address(bean.getAddress())     //Todo : no route for entity yet
                .contact(bean.getContact())     //Todo : no route for entity yet
                .build();
        return user;
    }
    private User getUserUpdateEntity(FieldGroup binder) {

        final UserModel bean = ((BeanItem<UserModel>) binder.getItemDataSource()).getBean();
        Set<String> userRoles = new HashSet<>();

        if (bean.getRole()!= null) {
            for (String roleId : bean.getRole()) {
                Role role = roleService.find(roleId);
                if (role != null) {
                    userRoles.add(role.getId());
                }
            }
        }
        final User user = new User.Builder(bean.getLastName())
                .firstname(bean.getFirstName())
                .role(userRoles)
                .enable(bean.isEnable())
                .password(bean.getPassword())
                .username(bean.getUsername())
                .othername(bean.getOtherName())
                .address(bean.getAddress())//Todo : no route for entity yet
                .contact(bean.getContact())//Todo : no route for entity yet
                .id(table.getValue().toString())
                .build();
        return user;
    }

    private void getHome() {
        main.content.setSecondComponent(new SetupMenu(main, "LANDING"));
    }
    private void setEditFormProperties() {
        userForm.binder.setReadOnly(false);
        userForm.save.setVisible(false);
        userForm.edit.setVisible(false);
        userForm.cancel.setVisible(true);
        userForm.delete.setVisible(false);
        userForm.update.setVisible(true);
    }
    private void setReadFormProperties() {
        userForm.binder.setReadOnly(true);
        //Buttons Behaviour
        userForm.save.setVisible(false);
        userForm.edit.setVisible(true);
        userForm.cancel.setVisible(true);
        userForm.delete.setVisible(true);
        userForm.update.setVisible(false);
    }

    private void addListeners() {
        //Register Button Listeners
        userForm.save.addClickListener(this);
        userForm.edit.addClickListener(this);
        userForm.cancel.addClickListener(this);
        userForm.update.addClickListener(this);
        table.addValueChangeListener(this);
        userForm.rolesList.addValueChangeListener(this);
        userForm.addNewAddress.addClickListener(this);
        userForm.addNewContact.addClickListener(this);
    }
    public UserModel getModel(User user) {
        Set<String> userRolesId = new HashSet<>();
        if (user.getRole() != null) {
            userRolesId.addAll(user.getRole().stream().collect(Collectors.toList()));
        }
        UserModel model = new UserModel();
        model.setFirstName(user.getFirstName());
        model.setLastName(user.getLastName());
        model.setOtherName(user.getOtherName());
        model.setUsername(user.getUsername());
        model.setEnable(user.isEnable());
        model.setRole(userRolesId);
        model.setAddress(user.getAddress()); //Todo : no route for entity yet
        model.setContact(user.getContact()); //Todo : no route for entity yet
        model.setPassword(user.getPassword());
        return model;
    }

}
