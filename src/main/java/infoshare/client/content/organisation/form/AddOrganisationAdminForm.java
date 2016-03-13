package infoshare.client.content.organisation.form;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import infoshare.app.util.fields.ButtonsHelper;
import infoshare.app.util.fields.UIComponentHelper;
import infoshare.client.content.organisation.model.AddOrganisationModel;
import infoshare.domain.organisation.Organisation;


/**
 * Created by THULEBONA on 2016/03/13.
 */
public class AddOrganisationAdminForm extends FormLayout {
    private final AddOrganisationModel model;
    public final BeanItem<AddOrganisationModel> item;
    public final FieldGroup binder;
    public final Button save = new Button("Save");
    public final Button edit = new Button("Edit");
    public final Button cancel = new Button("Cancel");
    public final Button update = new Button("Update");
    public final Button delete = new Button("Delete");
    public AddOrganisationAdminForm() {
        model = new AddOrganisationModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup();
        UIComponentHelper uiComponentHelper = new UIComponentHelper();
        ButtonsHelper buttonsHelper = new ButtonsHelper();

        TextField nameTxt = uiComponentHelper.getGridTextField("Name","name", Organisation.class,binder);
        TextField adminattached = uiComponentHelper.getGridTextField("admin attached","adminattached", Organisation.class,binder);

        HorizontalLayout buttons = buttonsHelper.getButtons(save, edit, cancel, update, delete) ;


       final GridLayout layout = new GridLayout(5,5);
        layout.setSpacing(true);
        layout.setSizeFull();
        layout.addComponent(nameTxt, 0, 0,4,0);
        layout.addComponent(adminattached,0,1,4,1);
        layout.addComponent(buttons,0,2,4,2);
        addComponent(layout);
    }
}
