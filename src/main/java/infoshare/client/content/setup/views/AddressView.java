package infoshare.client.content.setup.views;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.SetupMenu;
import infoshare.client.content.setup.forms.AddressForm;
import infoshare.client.content.setup.models.AddressModel;
import infoshare.client.content.setup.tables.AddressTable;
import infoshare.domain.Address;
import infoshare.services.Address.AddressService;
import infoshare.services.Address.Impl.AddressServiceImpl;

/**
 * Created by codex on 2015/08/18.
 */
public class AddressView extends Window implements Button.ClickListener, Property.ValueChangeListener{

    private final AddressForm addressForm;
    private final AddressTable addressTable;
    private AddressService addressService = new AddressServiceImpl();
    private MainLayout main;
    public AddressView(MainLayout main) {
        this.main = main;
        addressForm = new AddressForm();
        addressTable = new AddressTable();
        addListeners();
        setEditContactFormProperties();
        setWidth(50.0f, Unit.PERCENTAGE);
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
        }
    }
    private void getHome() {
        main.content.setSecondComponent(new SetupMenu(main, "LANDING"));
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
