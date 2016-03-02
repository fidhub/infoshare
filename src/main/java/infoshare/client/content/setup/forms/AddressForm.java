package infoshare.client.content.setup.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import infoshare.client.content.setup.models.AddressModel;

/**
 * Created by codex on 2015/07/30.
 */
public class AddressForm extends FormLayout {

    private AddressModel model;
    public BeanItem<AddressModel> item;
    public FieldGroup binder;
    public final Button save = new Button("Save");
    public final Button edit = new Button("Edit");
    public final Button cancel = new Button("Cancel");
    public final Button update = new Button("Update");
    public final Button exit = new Button("Exit");

    public AddressForm() {
        this.model = new AddressModel();
        this.item = new BeanItem<>(model);
        this.binder = new FieldGroup(item);
        setSizeUndefined();
        addComponent(getLayout());
    }

    private GridLayout getLayout(){

        final GridLayout layout = new GridLayout(4,5);
        layout.setSpacing(true);
        TextField postalAddress = getTextField("Postal PersonAddress","postalAddress");
        TextField postalCode = getTextField("Postal Code","postalCode");
        TextField physicalAddress = getTextField("Physical PersonAddress","physicalAddress");
        TextField addressType = getTextField("PersonAddress Type","addressTypeId");

        layout.addComponent(postalAddress,0,0);
        layout.addComponent(physicalAddress,1,0);
        layout.addComponent(addressType,0,1);
        layout.addComponent(postalCode,1,1);
        layout.addComponent(getButtons(),0,2,1,2);
        return layout;
    }

    private HorizontalLayout getButtons(){
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeUndefined();
        layout.setSpacing(true);

        save.addStyleName(Reindeer.BUTTON_DEFAULT);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        layout.addComponent(save);
        layout.addComponent(edit);
        layout.addComponent(update);
        layout.addComponent(cancel);
        layout.addComponent(exit);

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
