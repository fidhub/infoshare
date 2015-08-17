package infoshare.client.content.setup.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.SetupMenu;
import infoshare.client.content.setup.forms.AddressForm;
import infoshare.client.content.setup.forms.ContactForm;
import infoshare.client.content.setup.forms.UserForm;
import infoshare.client.content.setup.models.AddressModel;
import infoshare.client.content.setup.models.UserModel;
import infoshare.client.content.setup.tables.AddressTable;
import infoshare.client.content.setup.tables.ContactTable;
import infoshare.client.content.setup.tables.UserTable;
import infoshare.domain.Address;
import infoshare.domain.Role;
import infoshare.domain.User;
import infoshare.services.Address.AddressService;
import infoshare.services.Address.Impl.AddressServiceImpl;
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
    private final AddressForm addressForm;
    private final AddressTable addressTable;
    private final ContactForm contactForm;
    private final ContactTable contactTable;



    private UserService userService = new UserServiceImpl();
    private RoleService roleService = new RoleServiceImpl();
    private AddressService addressService = new AddressServiceImpl();
    private Window popUpWindow =null;
    public UserView(MainLayout app) {
        main = app;
        userForm = new UserForm();
        addressForm = new AddressForm();
        addressTable = new AddressTable();
        contactForm = new ContactForm();
        contactTable = new ContactTable(main);
        table = new UserTable(main);
        contactForm.edit.setVisible(false);
        contactForm.clear.setVisible(false);
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
            popUpWindow = getModelWind(addressForm,addressTable) ;
            getUI().addWindow(popUpWindow);
        }else if(source == userForm.addNewContact){
            popUpWindow = getModelWind(contactForm,contactTable);
            getUI().addWindow(popUpWindow);
        }if((source == addressForm.exit)||(source == contactForm.cancel)){
            popUpWindow.setModal(false);
            UI.getCurrent().removeWindow(popUpWindow);
        }
    }

    private  Window getModelWind(FormLayout layout,Table whichTable){
        final Window window = new Window();
        setEditContactFormProperties();
        window.setWidth(50.0f, Unit.PERCENTAGE);
        window.setClosable(false);
        window.setResizable(false);
        window.setDraggable(false);
        window.setModal(true);
        VerticalLayout layout1 = new VerticalLayout();
        layout1.addComponent(layout);
        layout1.addComponent(whichTable);
        window.setContent(layout1);
        return window;
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == contactTable){
            contactForm.edit.setVisible(true);
            contactForm.save.setVisible(false);
            contactForm.clear.setVisible(true);
        }
        if (property == table) {
            try {
                final User user = userService.find(table.getValue().toString());
                final UserModel bean = getModel(user);
                userForm.binder.setItemDataSource(new BeanItem<>(bean));
                setReadFormProperties();
            }catch (Exception r){
            }
        }
        if (property == addressTable){
            try {
                final Address address = addressService.find(addressTable.getValue().toString());
                final AddressModel bean = getAddressModel(address);
                addressForm.binder.setItemDataSource(new BeanItem<>(bean));
                setReadContactFormProperties();
            }catch (Exception e){}
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
        System.out.println(bean.getRoles()) ;
        final User user = new User.Builder(bean.getLastName())
                .firstname(bean.getFirstName())
                .role(bean.getRoles())
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

        if (bean.getRoles()!= null) {
            for (String roleId : bean.getRoles()) {
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
    private void setEditContactFormProperties(){
        addressForm.binder.setReadOnly(false);
        addressForm.save.setVisible(true);
        addressForm.edit.setVisible(false);
        addressForm.cancel.setVisible(true);
        addressForm.update.setVisible(false);
        addressForm.exit.setVisible(true);
    }
    private void setReadContactFormProperties(){
        addressForm.binder.setReadOnly(true);
        addressForm.save.setVisible(false);
        addressForm.edit.setVisible(true);
        addressForm.cancel.setVisible(true);
        addressForm.update.setVisible(true);
        addressForm.exit.setVisible(false);
    }
    private void addListeners() {
        //Register Button Listeners
        userForm.save.addClickListener(this);
        userForm.edit.addClickListener(this);
        userForm.cancel.addClickListener(this);
        userForm.update.addClickListener(this);
        userForm.delete.addClickListener(this);

        addressForm.save.addClickListener(this);
        addressForm.edit.addClickListener(this);
        addressForm.cancel.addClickListener(this);
        addressForm.update.addClickListener(this);
        addressForm.exit.addClickListener(this);
        addressTable.addValueChangeListener(this);

        table.addValueChangeListener(this);
        contactTable.addValueChangeListener(this);
        userForm.rolesList.addValueChangeListener(this);
        userForm.addNewAddress.addClickListener(this);
        userForm.addNewContact.addClickListener(this);

        addressForm.save.addClickListener(this);
        addressForm.cancel.addClickListener(this);
        contactForm.save.addClickListener(this);
        contactForm.clear.addClickListener(this);
    }
    public UserModel getModel(User user) {
        Set<String> userRolesId = new HashSet<>();
        if (user.getRoles() != null) {
            userRolesId.addAll(user.getRoles().stream().collect(Collectors.toList()));
        }
        UserModel model = new UserModel();
        model.setFirstName(user.getFirstName());
        model.setLastName(user.getLastName());
        model.setOtherName(user.getOtherName());
        model.setUsername(user.getUsername());
        model.setEnable(user.isEnable());
        model.setRoles(userRolesId);
        model.setAddress(user.getAddress()); //Todo : no route for entity yet
        model.setContact(user.getContact()); //Todo : no route for entity yet
        model.setPassword(user.getPassword());
        return model;
    }
    public AddressModel getAddressModel(Address address){
        AddressModel model = new AddressModel();
        model.setAddressType(address.getAddressType());
        model.setPhysicalAddress(address.getPhysicalAddress());
        model.setPostalCode(address.getPostalCode());
        model.setPostalAddress(address.getPostalAddress());
        return model;
    }
}
