package infoshare.client.content.systemValues.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import infoshare.client.content.systemValues.models.CategoryModel;
import infoshare.client.content.systemValues.models.SourceModel;

/**
 * Created by codex on 2015/06/26.
 */
public class CategoryForm extends FormLayout {

    private final CategoryModel model;
    public final BeanItem<CategoryModel> item;
    public final FieldGroup binder;

    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");


    public CategoryForm(){
        model = new CategoryModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);

        TextField textF = getTextField("Tip Category", "name");
        TextArea description = getTextArea("Description", "description");

        GridLayout grid = new GridLayout(4, 4);
        grid.setSizeFull();

        HorizontalLayout buttons = getButtons();
        update.setVisible(false);
        delete.setVisible(false);

        //------The first number is the COLUMN and the second number is the ROW------//
        grid.addComponent(textF, 0, 0);
        grid.addComponent(description, 1, 0, 2, 0);
        grid.addComponent(buttons, 0, 1);

        addComponent(grid);
    }

    private TextField getTextField(String label, String field){
        TextField textField = new TextField(label);
        textField.setWidth(250, Unit.PIXELS);
        textField.setNullRepresentation("");
        textField.addValidator(new BeanValidator(CategoryModel.class, field));
        textField.setImmediate(true);
        binder.bind(textField, field);

        return textField;

    }

    private TextArea getTextArea(String label, String field) {
        TextArea textArea = new TextArea(label);
        textArea.setWidth(250, Unit.PIXELS);
        textArea.setNullRepresentation("");
        textArea.addValidator(new BeanValidator(SourceModel.class, field));
        textArea.setImmediate(true);
        binder.bind(textArea, field);
        return textArea;
    }

    private  HorizontalLayout getButtons(){
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);

        return buttons;
    }


}
