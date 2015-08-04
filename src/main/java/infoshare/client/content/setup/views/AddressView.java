package infoshare.client.content.setup.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.SetupMenu;
import infoshare.client.content.setup.forms.AddressForm;
import infoshare.client.content.setup.models.AddressModel;
import infoshare.client.content.setup.tables.AddressTable;
import infoshare.domain.Address;
import infoshare.services.Address.AddressService;
import infoshare.services.Address.Impl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by codex on 2015/07/30.
 */
public class AddressView extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {
    @Autowired
    private AddressService addressService = new AddressServiceImpl();

    private MainLayout main;
    private AddressForm form;
    private AddressTable table;

    public AddressView(MainLayout main) {
        this.main = main;
        this.form = new AddressForm();
        this.table = new AddressTable();
        form.edit.setVisible(false);
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
        }
        else if (source == form.edit) {
            editor();
        } else if (source == form.clear) {
            getHome();
        }

    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table){
            try {
                final Address address = addressService.find(table.getValue().toString());
                final AddressModel bean = getModel(address);
                form.binder.setItemDataSource(new BeanItem<>(bean));
                setReadFormProperties();
            }catch (Exception r){

            }
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            addressService.save(getNewEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.HUMANIZED_MESSAGE);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }
    }

    private Address getNewEntity(FieldGroup binder){
        final AddressModel bean = ((BeanItem<AddressModel>) binder.getItemDataSource()).getBean();
        final Address address = new Address.Builder(bean.getPhysicalAddress())
                .postalAddress(bean.getPostalAddress())
                .addressType(bean.getAddressType())
                .postalCode(bean.getPostalCode())
                .build();
        return address;
    }

    private void getHome() {
        main.content.setSecondComponent(new SetupMenu(main, "ADDRESS"));
    }

    private void editor(){
        form.binder.setReadOnly(false);
        //Buttons Behaviour
        form.save.setVisible(true);
        form.edit.setVisible(false);
    }
    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviour
        form.save.setVisible(false);
        form.edit.setVisible(true);
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener((Button.ClickListener) this);
        form.clear.addClickListener((Button.ClickListener) this);
        form.edit.addClickListener((Button.ClickListener) this);
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private AddressModel getModel(Address address){
        final AddressModel model = new AddressModel();
        model.setAddressType(address.getAddressType());
        model.setPhysicalAddress(address.getPhysicalAddress());
        model.setPostalAddress(address.getPostalAddress());
        System.out.println(address.getPostalCode());
        model.setPostalCode(address.getPostalCode());

        return model;
    }

}
