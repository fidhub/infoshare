package infoshare.client.content.common.demographics.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import infoshare.app.util.fields.ButtonsHelper;
import infoshare.app.util.fields.UIComponentHelper;
import infoshare.client.content.common.demographics.model.GenderListModel;


/**
 * Created by hashcode on 2015/08/18.
 */
public class GenderListForm extends FormLayout {

    private final GenderListModel bean;
    public final BeanItem<GenderListModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public GenderListForm() {
        bean = new GenderListModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final UIComponentHelper UIComponent = new UIComponentHelper();


        TextField gender = UIComponent.getTextField("Gender :", "gender", GenderListModel.class, binder);
        GridLayout layout = new GridLayout(2,2);
        layout.setSizeFull();
        layout.addComponent(gender,0,0,1,0);
        addComponent(layout);

        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        addComponent(buttons);
    }
}
