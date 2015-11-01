package infoshare.client.content.setup.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.forms.AddressForm;
import infoshare.client.content.setup.models.AddressModel;
import infoshare.client.content.setup.tables.AddressTable;
import infoshare.domain.Address;
import infoshare.domain.User;
import infoshare.services.Address.AddressService;
import infoshare.services.Address.Impl.AddressServiceImpl;
import infoshare.services.users.Impl.UserServiceImpl;
import infoshare.services.users.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codex on 2015/08/18.
 */
public class AddressView extends Window implements Button.ClickListener, Property.ValueChangeListener{

    private final AddressForm addressForm;
    private AddressTable addressTable;
    private AddressService addressService = new AddressServiceImpl();
    private UserService userService = new UserServiceImpl();
    private MainLayout main;

    public AddressView(MainLayout main) {
        this.main = main;
        addressForm = new AddressForm();
        addressTable = new AddressTable();
        addListeners();
        setEditContactFormProperties();
        setWidth(60.0f, Unit.PERCENTAGE);
        setClosable(false);
        setResizable(false);
        setDraggable(false);
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(addressForm);
        layout.addComponent(addressTable);
        setContent(layout);

    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        final Button source = clickEvent.getButton();
        if (source == addressForm.cancel){
            getHome();
            setEditContactFormProperties();
        }else if (source == addressForm.edit){
            setUpdateContactFormProperties();
        }else if (source == addressForm.exit){
            this.setModal(false);
            getUI().removeWindow(this);
        }else if (source == addressForm.save){
            saveForm(addressForm.binder);
            getHome();
        }
    }
    private void getHome() {
        addressForm.binder.setItemDataSource(new BeanItem<>(getClear()));
        addressTable.loadTable();
        addressTable.setValue(null);
    }
    private AddressModel getClear(){
        AddressModel model = new AddressModel();
        model.setAddressType(null);
        model.setPhysicalAddress(null);
        model.setPostalCode(null);
        model.setPostalAddress(null);
        return model;
    }
    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
        final Property property = valueChangeEvent.getProperty();
        if (property == addressTable){
            try {
                final Address address = addressService.find(addressTable.getValue().toString());
                final AddressModel bean = getAddressModel(address);
                addressForm.binder.setItemDataSource(new BeanItem<>(bean));
                setReadContactFormProperties();
            }catch (Exception e){}
        }
    }

    private User getUserUpdateEntity(String addressID) {

        final User bean = userService.find(AddressTable.userID);
        List<String> addresses = new ArrayList<>();

        if (bean.getAddress()!= null) {
            for (String ID : bean.getAddress()) {
                Address address = addressService.find(ID);
                if (address != null) {
                    addresses.add(address.getId());
                }
            }
        }
        addresses.add(addressID);

        final User user = new User.Builder(bean.getLastName())
                .firstname(bean.getFirstName())
                .role(bean.getRoles())
                .enable(bean.isEnable())
                .password(bean.getPassword())
                .username(bean.getUsername())
                .othername(bean.getOtherName())
                .address(addresses)//Todo : no route for entity yet
                .contact(bean.getContact())//Todo : no route for entity yet
                .id(addressTable.userID)
                .build();
        return user;
    }
    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            Address address = addressService.save(getAddressNewEntity(binder));
            userService.merge(getUserUpdateEntity(address.getId()));
            getHome();
            Notification.show("Address ADDED!", Notification.Type.HUMANIZED_MESSAGE);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        } catch(Exception dp){
            Notification.show("Un-expected error", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }
    }
    private Address getAddressNewEntity(FieldGroup binder) {
        final AddressModel bean = ((BeanItem<AddressModel>)binder.getItemDataSource()).getBean();
        final Address address = new Address.Builder(bean.getPhysicalAddress())
                                .postalCode(bean.getPostalCode())
                                .postalAddress(bean.getPostalAddress())
                                .addressType(bean.getAddressType())
                                .build();
        return address;
    }

    private void addListeners() {
        addressForm.save.addClickListener(this);
        addressForm.edit.addClickListener(this);
        addressForm.cancel.addClickListener(this);
        addressForm.update.addClickListener(this);
        addressForm.exit.addClickListener(this);
        addressTable.addValueChangeListener(this);
    }
    public AddressModel getAddressModel(Address address){
        AddressModel model = new AddressModel();
        model.setAddressType(address.getAddressType());
        model.setPhysicalAddress(address.getPhysicalAddress());
        model.setPostalCode(address.getPostalCode());
        model.setPostalAddress(address.getPostalAddress());
        return model;
    }

    private void setReadContactFormProperties(){
        addressForm.binder.setReadOnly(true);
        addressForm.save.setVisible(false);
        addressForm.edit.setVisible(true);
        addressForm.cancel.setVisible(true);
        addressForm.update.setVisible(false);
        addressForm.exit.setVisible(false);
    }
    private void setUpdateContactFormProperties(){
        addressForm.binder.setReadOnly(false);
        addressForm.save.setVisible(false);
        addressForm.edit.setVisible(false);
        addressForm.cancel.setVisible(true);
        addressForm.update.setVisible(true);
        addressForm.exit.setVisible(false);
    }
    private void setEditContactFormProperties(){
        addressForm.binder.setReadOnly(false);
        addressForm.save.setVisible(true);
        addressForm.edit.setVisible(false);
        addressForm.cancel.setVisible(true);
        addressForm.update.setVisible(false);
        addressForm.exit.setVisible(true);
    }
}
