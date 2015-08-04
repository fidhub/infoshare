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
    public Button edit;

    public AddressForm() {
        this.model = new AddressModel();
        this.item = new BeanItem<>(model);
        this.binder = new FieldGroup(item);
        setSizeFull();
        addComponent(getLayout());
        addComponent(getButtons());
    }

    private GridLayout getLayout(){

        final GridLayout gridLayout = new GridLayout(6, 5);
        gridLayout.setSizeFull();

        TextField postalAddress = getTextField("Postal Address","postalAddress");
        TextField postalCode = getTextField("Postal Code","postalCode");
        TextField physicalAddress = getTextField("Physical Address","physicalAddress");
        TextField addressType = getTextField("Address Type","addressType");

        gridLayout.addComponent(postalAddress, 0, 0);
        gridLayout.addComponent(postalCode, 1, 0, 2, 0);
        gridLayout.addComponent(physicalAddress, 0, 2);
        gridLayout.addComponent(addressType, 1, 2, 2, 2);

        return gridLayout;
    }

    private HorizontalLayout getButtons(){
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeUndefined();
        layout.setSpacing(true);

        save = new Button("Save");
        save.addStyleName(Reindeer.BUTTON_DEFAULT);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        clear = new Button("Clear");
        edit = new Button("Edit");

        layout.addComponent(save);
        layout.addComponent(edit);
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
