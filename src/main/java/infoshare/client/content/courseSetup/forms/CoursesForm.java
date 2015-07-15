package infoshare.client.content.courseSetup.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import infoshare.client.content.courseSetup.models.CourseModel;

/**
 * Created by codex on 2015/07/07.
 */
public class CoursesForm extends FormLayout {

    private final CourseModel model;
    public final BeanItem<CourseModel> item;
    public final FieldGroup binder;

    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public CoursesForm() {
        model = new CourseModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);


        TextField textF = getTextField("Course Level", "courseLevel");
        TextField description = getTextField("Course Description", "courseDescription");

        HorizontalLayout buttons = getButtons();
        update.setVisible(false);
        delete.setVisible(false);


        GridLayout grid = new GridLayout(4, 4);
        grid.setSizeFull();
        grid.addComponent(textF, 0, 0);
        grid.addComponent(description,2,0);
        grid.addComponent(buttons,0,2);

        addComponent(grid);

    }

    private TextField getTextField(String label, String field){
        final TextField textField = new TextField(label);
        textField.setImmediate(true);
        textField.setNullRepresentation("");
        textField.setWidth(250, Unit.PIXELS);
        textField.addValidator(new BeanValidator(CourseModel.class,field));
        binder.bind(textField,field);

        return textField;
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
