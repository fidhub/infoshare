package infoshare.client.content.profile.demographics.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import hashwork.app.facade.DemographicsFacade;
import hashwork.app.util.fields.ButtonsHelper;
import hashwork.app.util.fields.UIComboBoxHelper;
import hashwork.app.util.fields.UIComponentHelper;
import hashwork.client.content.profile.demographics.model.PersonIdentitiesModel;
import hashwork.domain.ui.demographics.IdentificationType;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonIdentitiesForm extends FormLayout {
    private final PersonIdentitiesModel bean;
    public final BeanItem<PersonIdentitiesModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public PersonIdentitiesForm() {
        bean = new PersonIdentitiesModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final UIComponentHelper UIComponent = new UIComponentHelper();
        final UIComboBoxHelper UIComboBox = new UIComboBoxHelper();


        TextField idValue = UIComponent.getGridTextField("ID  :", "idValue", PersonIdentitiesModel.class, binder);
        CheckBox preffered = UIComponent.getGridCheckBox("Proffered :", "preffered", PersonIdentitiesModel.class, binder);
        preffered.setSizeFull();

        //ComboBox Fields
        final ComboBox idType = UIComboBox.getComboBox("ID Type :", "idType", PersonIdentitiesModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<IdentificationType> identificationTypes = DemographicsFacade.identificationTypeService.findAll();
                for (IdentificationType identificationType : identificationTypes) {
                    comboBox.addItem(identificationType.getId());
                    comboBox.setItemCaption(identificationType.getId(), identificationType.getName());
                }
            }
        });

        // Create a field group and use it to bind the fields in the layout
        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();
        grid.setMargin(true);


        // First ROW
        grid.addComponent(idType, 0, 0);
        grid.addComponent(idValue, 1, 0);

        grid.addComponent(preffered, 0, 2, 0, 3);

        //Second ROW

        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        grid.addComponent(buttons, 0, 5, 2, 5);

        addComponent(grid);
    }
}
