package infoshare.client.content.profile.contacts.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import hashwork.app.facade.LocationFacade;
import hashwork.app.util.fields.ButtonsHelper;
import hashwork.app.util.fields.UIComboBoxHelper;
import hashwork.app.util.fields.UIComponentHelper;
import hashwork.client.content.profile.contacts.model.PersonAddressModel;
import hashwork.domain.ui.location.AddressType;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonAddressForm extends FormLayout {
    private final PersonAddressModel bean;
    public final BeanItem<PersonAddressModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public PersonAddressForm() {
        bean = new PersonAddressModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final UIComponentHelper UIComponent = new UIComponentHelper();
        final UIComboBoxHelper UIComboBox = new UIComboBoxHelper();


//        private String description;
//        private String postalCode;
//        private String addressTypeId;
        TextField postalCode = UIComponent.getGridTextField("Postal Code :", "postalCode", PersonAddressModel.class, binder);
        TextArea postalAddress = UIComponent.getGridTextArea("Postal Address :", "description", PersonAddressModel.class, binder);

        //ComboBox Fields
        final ComboBox addressTypeId = UIComboBox.getComboBox("Address Type :", "addressTypeId", PersonAddressModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<AddressType> addressTypes = LocationFacade.addressTypeService.findAll();
                for (AddressType addressType : addressTypes) {
                    comboBox.addItem(addressType.getId());
                    comboBox.setItemCaption(addressType.getId(), addressType.getName());
                }
            }
        });


        // Create a field group and use it to bind the fields in the layout
        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        // First ROW
        grid.addComponent(postalAddress, 0, 0, 1, 1);
        grid.addComponent(addressTypeId, 2, 0);

        //Second ROW
        grid.addComponent(postalCode, 2, 1);

        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        grid.addComponent(buttons, 0, 5, 2, 5);

        addComponent(grid);
    }
}
