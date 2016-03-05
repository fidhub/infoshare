package infoshare.client.content.profile.demographics.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import hashwork.app.facade.DemographicsFacade;
import hashwork.app.util.fields.ButtonsHelper;
import hashwork.app.util.fields.UIComboBoxHelper;
import hashwork.app.util.fields.UIComponentHelper;
import hashwork.client.content.profile.demographics.model.PersonDemographicsModel;
import hashwork.domain.ui.demographics.Gender;
import hashwork.domain.ui.demographics.MaritalStatus;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonDemographicsForm extends FormLayout {
    private final PersonDemographicsModel bean;
    public final BeanItem<PersonDemographicsModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

//    private String genderId;
//    private Date dateOfBirth;
//    private String maritalStatusId;
//    private int numberOfDependencies;

    public PersonDemographicsForm() {
        bean = new PersonDemographicsModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final UIComponentHelper UIComponent = new UIComponentHelper();
        final UIComboBoxHelper UIComboBox = new UIComboBoxHelper();


        DateField dateOfBirth = UIComponent.getGridDateField("Date of Birth :", "dateOfBirth", PersonDemographicsModel.class, binder);

        TextField numberOfDependencies = UIComponent.getGridTextField("Dependencies :", "numberOfDependencies", PersonDemographicsModel.class, binder);
        //ComboBox Fields
        final ComboBox genderId = UIComboBox.getComboBox("Gender :", "genderId", PersonDemographicsModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<Gender> genders = DemographicsFacade.genderListService.findAll();
                for (Gender gender : genders) {
                    comboBox.addItem(gender.getId());
                    comboBox.setItemCaption(gender.getId(), gender.getName());
                }
            }
        });

        //ComboBox Fields
        final ComboBox maritalStatusId = UIComboBox.getComboBox("Marital Status :", "maritalStatusId", PersonDemographicsModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<MaritalStatus> maritalStatuses = DemographicsFacade.maritalStatusListService.findAll();
                for (MaritalStatus maritalStatus : maritalStatuses) {
                    comboBox.addItem(maritalStatus.getId());
                    comboBox.setItemCaption(maritalStatus.getId(), maritalStatus.getName());
                }
            }
        });


        // Create a field group and use it to bind the fields in the layout
        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        // First ROW
        grid.addComponent(dateOfBirth, 0, 0);
        grid.addComponent(genderId, 1, 0);
        grid.addComponent(maritalStatusId, 2, 0);
        grid.addComponent(numberOfDependencies, 0, 1);

        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        grid.addComponent(buttons, 0, 5, 2, 5);

        addComponent(grid);
    }
}
