package infoshare.client.content.setup.views;

/**
 * Created by user9 on 2016/02/24.
 */
public class UserBackUp {
    /*
    public class PersonView extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final PersonForm personForm;
    private final PersonTable table;
    private UserService userService = new UserServiceImpl();
    private RoleService roleService = new RoleServiceImpl();
    private AddressView addressView ;
    private ContactView contactView;
    public PersonView(MainLayout app) {
        main = app;
        personForm = new PersonForm();
        table = new PersonTable(main);
        setSizeFull();
        addComponent(personForm);
        addComponent(table);
        addListeners();
    }
    @Override
    public void buttonClick(Button.ClickEvent event) {

        final Button source = event.getButton();
        if (source == personForm.save) {
            saveForm(personForm.binder);
        } else if (source == personForm.edit) {
            setEditFormProperties();
        } else if (source == personForm.cancel) {
            getHome();
        } else if (source == personForm.update) {
            saveEditedForm(personForm.binder);
        } else if (source == personForm.delete) {
            deleteForm(personForm.binder);
        }else if(source == personForm.addNewAddress){
            try {
                addressView.setModal(true);
                getUI().addWindow(addressView);
            }catch (Exception e){
                Notification.show("Select the user first",Notification.Type.HUMANIZED_MESSAGE);
            }
        }else if(source == personForm.addNewContact){
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
                final User user = userService.findById(table.getValue().toString());
                final UserModel bean = getModel(user);
                personForm.binder.setItemDataSource(new BeanItem<>(bean));
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
            userService.update(getUserUpdateEntity(binder));
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
        userService.delete(getUserUpdateEntity(binder));
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
                Role role = roleService.findById(roleId);
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
        personForm.binder.setReadOnly(false);
        personForm.save.setVisible(false);
        personForm.edit.setVisible(false);
        personForm.cancel.setVisible(true);
        personForm.delete.setVisible(false);
        personForm.update.setVisible(true);
    }
    private void setReadFormProperties() {
        personForm.binder.setReadOnly(true);
        //Buttons Behaviour
        personForm.save.setVisible(false);
        personForm.edit.setVisible(true);
        personForm.cancel.setVisible(true);
        personForm.delete.setVisible(true);
        personForm.update.setVisible(false);
    }

    private void addListeners() {
        //Register Button Listeners
        personForm.save.addClickListener(this);
        personForm.edit.addClickListener(this);
        personForm.cancel.addClickListener(this);
        personForm.update.addClickListener(this);
        table.addValueChangeListener(this);
        personForm.rolesList.addValueChangeListener(this);
        personForm.addNewAddress.addClickListener(this);
        personForm.addNewContact.addClickListener(this);
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
     */
}
