package infoshare.client.content.profile.education.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import hashwork.app.facade.EducationFacade;
import hashwork.app.util.fields.ButtonsHelper;
import hashwork.app.util.fields.UIComboBoxHelper;
import hashwork.app.util.fields.UIComponentHelper;
import hashwork.client.content.profile.education.model.EducationHistoryModel;
import hashwork.domain.ui.education.DegreeType;
import hashwork.domain.ui.education.EducationType;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by hashcode on 2015/12/21.
 */
public class EducationHistoryForm extends FormLayout {
    private final EducationHistoryModel bean;
    public final BeanItem<EducationHistoryModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public EducationHistoryForm() {
        bean = new EducationHistoryModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final UIComponentHelper UIComponent = new UIComponentHelper();
        final UIComboBoxHelper UIComboBox = new UIComboBoxHelper();


        TextField institutionName = UIComponent.getGridTextField("Institution Name :", "institutionName", EducationHistoryModel.class, binder);
        TextField institutionLocation = UIComponent.getGridTextField("Institution Country :", "institutionLocation", EducationHistoryModel.class, binder);
        TextField yearOfGraduation = UIComponent.getGridTextField("Year of Graduation :", "yearOfGraduation", EducationHistoryModel.class, binder);

        TextArea notes = UIComponent.getGridTextArea("Any Other Note :", "notes", EducationHistoryModel.class, binder);

        //ComboBox Fields
        final ComboBox educationTypeId = UIComboBox.getComboBox("Education Type :", "educationTypeId", EducationHistoryModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<EducationType> educationTypes = EducationFacade.educationTypeService.findAll();
                for (EducationType educationType : educationTypes) {
                    comboBox.addItem(educationType.getId());
                    comboBox.setItemCaption(educationType.getId(), educationType.getName());
                }
            }
        });

        //ComboBox Fields
        final ComboBox degreeId = UIComboBox.getComboBox("A Degree Type :", "degreeId", EducationHistoryModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<DegreeType> degreeTypes = EducationFacade.degreeTypeService.findAll();
                for (DegreeType degreeType : degreeTypes) {
                    comboBox.addItem(degreeType.getId());
                    comboBox.setItemCaption(degreeType.getId(), degreeType.getName());
                }
            }
        });


        // Create a field group and use it to bind the fields in the layout
        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        // First ROW
        grid.addComponent(educationTypeId, 0, 0);
        grid.addComponent(degreeId, 1, 0);
        grid.addComponent(yearOfGraduation, 2, 0);

        //Second ROW
        grid.addComponent(institutionName, 0, 1);
        grid.addComponent(institutionLocation, 0, 2);
        grid.addComponent(notes, 1, 1, 2, 2);

        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        grid.addComponent(buttons, 0, 5, 2, 5);

        addComponent(grid);
    }
}
