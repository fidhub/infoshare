package infoshare.client.content.common.location.forms;


import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import infoshare.app.util.fields.ButtonsHelper;
import infoshare.app.util.fields.UIComponentHelper;
import infoshare.client.content.common.location.models.AddressTypeModel;

/**
 * Created by hashcode on 2015/09/07.
 */
public class AddressTypeForm extends FormLayout {
    private final AddressTypeModel bean;
    public final BeanItem<AddressTypeModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public AddressTypeForm() {
        bean = new AddressTypeModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);

        final UIComponentHelper UIComponent = new UIComponentHelper();

        TextField addressTypeName = UIComponent.getTextField("Address Type Name", "addressTypeName", AddressTypeModel.class, binder);
        addComponent(addressTypeName);

        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        addComponent(buttons);
    }

}
