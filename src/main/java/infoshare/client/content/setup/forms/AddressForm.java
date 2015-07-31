package infoshare.client.content.setup.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import infoshare.client.content.setup.models.AddressModel;
import infoshare.client.content.setup.models.ContactModel;

/**
 * Created by codex on 2015/07/30.
 */
public class AddressForm extends FormLayout {

    private AddressModel model;
    public BeanItem<AddressModel> item;
    public FieldGroup binder;
    public Button save ;
    public Button clear;

    public AddressForm() {
        this.model = new AddressModel();
        this.item = new BeanItem<>(model);
        this.binder = new FieldGroup(item);
        setSizeFull();
        addComponent(getLayout());
        addComponent(getButtons());
    }

    private HorizontalLayout getLayout(){
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);

        TextField postalAddress = getTextField("Postal Address","postalAddress");
        TextField postalCode = getTextField("Postal Code","postalCode");
        TextField physicalAddress = getTextField("Physical Address","physicalAddress");
        TextField addressType = getTextField("Address Type","addressType");

        layout.addComponent(postalAddress);
        layout.addComponent(postalCode);
        layout.addComponent(physicalAddress);
        layout.addComponent(addressType);
        return layout;
    }

    private HorizontalLayout getButtons(){
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeUndefined();
        layout.setSpacing(true);

        save = new Button("Save");
        save.addStyleName(Reindeer.BUTTON_DEFAULT);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        clear = new Button("Clear");
        layout.addComponent(save);
        layout.addComponent(clear);

        return layout;
    }
    private TextField getTextField(String label,String field){
        final TextField textField = new TextField(label);
        textField.setWidth(250, Unit.PIXELS);
        textField.setNullRepresentation("");
        textField.addValidator(new BeanValidator(AddressModel.class,field));
        textField.setImmediate(true);
        binder.bind(textField, field);

        return textField;
    }
}
