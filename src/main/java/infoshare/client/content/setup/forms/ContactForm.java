package infoshare.client.content.setup.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.setup.models.ContactModel;
import infoshare.client.content.setup.models.RoleModel;


/**
 * Created by user9 on 2015/07/30.
 */
public class ContactForm extends FormLayout {

    private final ContactModel model;
    public final BeanItem<ContactModel> item;
    public final FieldGroup binder;
    public Button save ;
    public Button clear;
    public ContactForm() {
        model = new ContactModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup();
        setSizeUndefined();

        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);

        TextField phone = getTextField("Phone Number","phone");
        TextField email = getTextField("Email Address","email");
        TextField contactType = getTextField("contact Type","contactType");

        layout.addComponent(phone);
        layout.addComponent(email);
        layout.addComponent(contactType);

        addComponent(layout);
        addComponent(getButtons());
    }

    private HorizontalLayout getButtons(){
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeUndefined();
        layout.setSpacing(true);

        save = new Button("Save");
        save.addStyleName(Reindeer.BUTTON_DEFAULT);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        clear= new Button("Clear");
        layout.addComponent(save);
        layout.addComponent(clear);

        return layout;
    }
    private TextField getTextField(String label,String field){
        TextField textField = new TextField(label);
        textField.setWidth(250, Unit.PIXELS);
        textField.setNullRepresentation("");
        textField.addValidator(new BeanValidator(ContactModel.class,field));
        textField.setImmediate(true);
        binder.bind(textField,field);
        return textField;
    }
}
