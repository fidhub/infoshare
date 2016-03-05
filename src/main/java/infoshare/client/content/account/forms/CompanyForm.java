package infoshare.client.content.account.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import hashwork.app.util.fields.ButtonsHelper;
import hashwork.app.util.fields.UIComboBoxHelper;
import hashwork.app.util.fields.UIComponentHelper;
import hashwork.client.content.account.model.CompanyModel;

/**
 * Created by hashcode on 2015/11/16.
 */
public class CompanyForm extends FormLayout {
    private final CompanyModel bean;
    public final BeanItem<CompanyModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public CompanyForm() {
        bean = new CompanyModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final UIComponentHelper UIComponent = new UIComponentHelper();
        final UIComboBoxHelper UIComboBox = new UIComboBoxHelper();


        TextField name = UIComponent.getGridTextField("Company Name :", "name", CompanyModel.class, binder);
        TextField code = UIComponent.getGridTextField("Company Code Name :", "code", CompanyModel.class, binder);
        TextArea address = UIComponent.getGridTextArea("Company Address :", "address", CompanyModel.class, binder);
        TextField email = UIComponent.getGridTextField("Contact Email :", "email", CompanyModel.class, binder);
        TextField contactphone = UIComponent.getGridTextField("Contact number :", "contactphone", CompanyModel.class, binder);


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
//        grid.addComponent(description, 2, 0, 2, 1);


        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        grid.addComponent(buttons, 0, 5, 2, 5);

        addComponent(grid);
    }
}
