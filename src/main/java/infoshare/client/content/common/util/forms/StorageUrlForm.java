package infoshare.client.content.common.util.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import infoshare.app.util.fields.ButtonsHelper;
import infoshare.app.util.fields.UIComboBoxHelper;
import infoshare.app.util.fields.UIComponentHelper;
import infoshare.client.content.common.util.models.StorageUrlModel;


/**
 * Created by hashcode on 2016/01/06.
 */
public class StorageUrlForm extends FormLayout {

    private final StorageUrlModel bean;
    public final BeanItem<StorageUrlModel> item;
    public final FieldGroup binder;
    final UIComponentHelper UIComponent;
    final UIComboBoxHelper UIComboBox;

    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public StorageUrlForm() {
        bean = new StorageUrlModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        UIComponent = new UIComponentHelper();
        UIComboBox = new UIComboBoxHelper();


        TextField linkname = UIComponent.getTextField("Link Name :", "name", StorageUrlModel.class, binder);
        TextField linkurl = UIComponent.getTextField("Link URL :", "url", StorageUrlModel.class, binder);

        // Create a field group and use it to bind the fields in the layout


        addComponent(linkname);
        addComponent(linkurl);

        // Create a field group and use it to bind the fields in the layout

        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        addComponent(buttons);


    }


}