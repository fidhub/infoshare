package infoshare.client.content.common.util.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import infoshare.app.util.fields.ButtonsHelper;
import infoshare.app.util.fields.UIComboBoxHelper;
import infoshare.app.util.fields.UIComponentHelper;
import infoshare.client.content.common.util.models.StatusModel;


/**
 * Created by hashcode on 2015/10/12.
 */
public class StatusForm extends FormLayout {

    private final StatusModel bean;
    public final BeanItem<StatusModel> item;
    public final FieldGroup binder;
    final UIComponentHelper UIComponent;
    final UIComboBoxHelper UIComboBox;

    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public StatusForm() {
        bean = new StatusModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        UIComponent = new UIComponentHelper();
        UIComboBox = new UIComboBoxHelper();


        TextField statusType = UIComponent.getTextField("Status Type :", "name", StatusModel.class, binder);
        TextField statusValues = UIComponent.getTextField("Status Value :", "value", StatusModel.class, binder);
        GridLayout layout = new GridLayout(2,2);
        layout.setSizeFull();
        layout.addComponent(statusType,0,0,1,0);
        layout.addComponent(statusValues,0,1,1,1);
        addComponent(layout);

        // Create a field group and use it to bind the fields in the layout

        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        addComponent(buttons);


    }


}