package infoshare.viewUI.container.common.demographics.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import infoshare.app.util.fields.ButtonsHelper;
import infoshare.app.util.fields.UIComponentHelper;
import infoshare.viewUI.container.common.demographics.model.RolesListModel;


/**
 * Created by hashcode on 2015/08/18.
 */
public class RolesListForm extends FormLayout {

    private final RolesListModel bean;
    public final BeanItem<RolesListModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public RolesListForm() {
        bean = new RolesListModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final UIComponentHelper UIComponent = new UIComponentHelper();

        TextField roleName = UIComponent.getTextField("Role Name :", "roleName", RolesListModel.class, binder);
        TextField description = UIComponent.getTextField("Description :", "description", RolesListModel.class, binder);
        GridLayout layout = new GridLayout(2,2);
        layout.setSizeFull();
        layout.addComponent(roleName,0,0,1,0);
        layout.addComponent(description,0,1,1,1);
        addComponent(layout);


        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        addComponent(buttons);
    }


}
