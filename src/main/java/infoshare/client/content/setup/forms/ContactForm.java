package infoshare.client.content.setup.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import infoshare.client.content.setup.models.ContactModel;


/**
 * Created by user9 on 2015/07/30.
 */
public class ContactForm extends FormLayout {

    private final ContactModel model;
    public final BeanItem<ContactModel> item;
    public final FieldGroup binder;

    public Button save = new Button("Save");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button edit = new Button("Edit");
    public Button exit = new Button("Exit");


    public ContactForm() {
        model = new ContactModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);
        setSizeUndefined();
        addComponent(getLayout());

    }

    public GridLayout getLayout(){

        final GridLayout layout = new GridLayout(4,10);
        layout.setSpacing(true);
        TextField phone = getTextField("Phone Number","phone");
        TextField email = getTextField("Email PersonAddress","email");
        TextField contactType = getTextField("contact Type","contactType");

        layout.addComponent(phone, 0, 0);
        layout.addComponent(email,1,0);
        layout.addComponent(contactType,0,1);
        layout.addComponent(getButtons(),0,2);

        addComponent(layout);
       // addComponent(getButtons());
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
        //layout.addComponent(edit);
        //layout.addComponent(edit);
        layout.addComponent(cancel);
        layout.addComponent(exit);

        return layout;
    }
    private TextField getTextField(String label,String field){
       final TextField textField = new TextField(label);
        textField.setWidth(250, Unit.PIXELS);
        textField.setNullRepresentation("");
        textField.addValidator(new BeanValidator(ContactModel.class,field));
        textField.setImmediate(true);
        binder.bind(textField,field);

        return textField;
    }
}
