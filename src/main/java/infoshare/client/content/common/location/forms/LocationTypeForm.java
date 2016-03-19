package infoshare.client.content.common.location.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import infoshare.app.util.fields.ButtonsHelper;
import infoshare.app.util.fields.UIComponentHelper;
import infoshare.client.content.common.location.models.LocationTypeModel;



/**
 * Created by hashcode on 2015/09/07.
 */
public class LocationTypeForm extends FormLayout {
    private final LocationTypeModel bean;

    public final BeanItem<LocationTypeModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public LocationTypeForm() {
        bean = new LocationTypeModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final UIComponentHelper UIComponent = new UIComponentHelper();

        TextField name = UIComponent.getTextField("Location Type:", "name", LocationTypeModel.class, binder);
        TextField code = UIComponent.getTextField("Type Code :", "code", LocationTypeModel.class, binder);
        GridLayout layout = new GridLayout(2,2);
        layout.setSizeFull();
        layout.addComponent(name,0,0,1,0);
        layout.addComponent(code,0,1,1,1);
        addComponent(layout);


        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        addComponent(buttons);

    }

}
