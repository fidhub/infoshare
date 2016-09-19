package infoshare.viewUI.container.common.util.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import infoshare.app.util.fields.ButtonsHelper;
import infoshare.app.util.fields.UIComboBoxHelper;
import infoshare.app.util.fields.UIComponentHelper;
import infoshare.viewUI.container.common.util.models.MailModel;


/**
 * Created by hashcode on 2015/11/29.
 */
public class MailForm extends FormLayout {

//    private String key;
//    private String value;
//    private String host;
//    private String port;


    private final MailModel bean;
    public final BeanItem<MailModel> item;
    public final FieldGroup binder;
    final UIComponentHelper UIComponent;
    final UIComboBoxHelper UIComboBox;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public MailForm() {
        bean = new MailModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        UIComponent = new UIComponentHelper();
        UIComboBox = new UIComboBoxHelper();


        TextField key = UIComponent.getGridTextField("Email Key :", "key", MailModel.class, binder);
        PasswordField value = UIComponent.getPasswordField("Email Value :", "value", MailModel.class, binder);
        TextField host = UIComponent.getGridTextField("SMTP Host :", "host", MailModel.class, binder);
        TextField port = UIComponent.getGridTextField("SMTP Port :", "port", MailModel.class, binder);


        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();
        grid.addComponent(key, 0, 0);
        grid.addComponent(value, 1, 0);
        grid.addComponent(host, 2, 0);
        grid.addComponent(port, 0, 1);


        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 6, 2, 6);
        grid.addComponent(buttons, 0, 8, 2, 8);
        addComponent(grid);
    }


}