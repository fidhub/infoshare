package infoshare.client.content.courseSetup.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import infoshare.client.content.courseSetup.models.CoursesModel;

/**
 * Created by codex on 2015/07/07.
 */
public class CoursesForm extends FormLayout {

    private final CoursesModel model;
    public final BeanItem<CoursesModel> item;
    public final FieldGroup binder;

    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public CoursesForm() {
        model = new CoursesModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);


        TextField textF = getTextField("Course Level", "courseLevel");
        TextArea description = getTextArea("Course Description", "courseDescription");

        GridLayout grid = new GridLayout(4, 4);
        grid.setSizeFull();

        HorizontalLayout buttons = getButtons();
        update.setVisible(false);
        delete.setVisible(false);
        grid.addComponent(textF, 0, 0);
        grid.addComponent(description, 1, 0, 2, 0);
        grid.addComponent(buttons, 0, 1);

        addComponent(grid);

    }

    private TextField getTextField(String label, String field){
        final TextField textField = new TextField(label);
        textField.setImmediate(true);
        textField.setNullRepresentation("");
        textField.setWidth(250, Unit.PIXELS);
        textField.addValidator(new BeanValidator(CoursesModel.class,field));
        binder.bind(textField,field);

        return textField;
    }

    private TextArea getTextArea(String label, String field){
        TextArea textArea = new TextArea(label);
        textArea.setWidth(250, Unit.PIXELS);
        textArea.setNullRepresentation("");
        textArea.addValidator(new BeanValidator(CoursesModel.class, field));
        textArea.setImmediate(true);
        binder.bind(textArea, field);

        return textArea;
    }


    private HorizontalLayout getButtons(){
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);

        return buttons;
    }
}
