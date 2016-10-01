package infoshare.viewUI.container.account.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import infoshare.app.util.fields.ButtonsHelper;
import infoshare.app.util.fields.UIComponentHelper;
import infoshare.viewUI.container.account.model.OrganisationModel;


/**
 * Created by hashcode on 2015/11/16.
 */
public class OrganisationForm extends FormLayout {
    private final OrganisationModel bean;
    public final BeanItem<OrganisationModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");
    public ImageUploader imageUploader = new ImageUploader();
    public OrganisationForm() {
        bean = new OrganisationModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final UIComponentHelper UIComponent = new UIComponentHelper();

        TextField name = UIComponent.getGridTextField("Organisation Name :", "name", OrganisationModel.class, binder);
        TextField code = UIComponent.getGridTextField("Organisation Code Name :", "code", OrganisationModel.class, binder);
        TextArea address = UIComponent.getGridTextArea("Organisation Address :", "address", OrganisationModel.class, binder);
        TextField email = UIComponent.getGridTextField("Contact Email :", "email", OrganisationModel.class, binder);
        TextField contactphone = UIComponent.getGridTextField("Contact number :", "contactphone", OrganisationModel.class, binder);

        // Create a field group and use it to bind the fields in the layout
        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        // First ROW
        grid.addComponent(name, 0, 0);
        grid.addComponent(code, 1, 0);

        //Second ROW
        grid.addComponent(email, 0, 1);
        grid.addComponent(contactphone, 1, 1);

        //Second ROW
        grid.addComponent(address, 2, 0, 2, 1);

        //SPAN ROW 1 and 2
        grid.addComponent(imageUploader,0,3,0,3);

        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        grid.addComponent(buttons, 0, 5, 2, 5);

        addComponent(grid);
    }

}
