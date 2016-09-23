package infoshare.viewUI.container.common.systemValues.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import infoshare.viewUI.container.common.systemValues.models.SourceModel;

/**
 * Created by codex on 2015/06/26.
 */
public class ContentSourceForm extends FormLayout {

    private final SourceModel model;
    public final BeanItem<SourceModel> item;
    public final FieldGroup binder;
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public ContentSourceForm(){

        model = new SourceModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);

        TextField name = getTextField("Source ", "name");
        TextArea description = getTextArea("Description", "description");
        GridLayout grid = new GridLayout(4, 8);
        grid.setSizeFull();

        HorizontalLayout buttons = getButtons();
        update.setVisible(false);
        delete.setVisible(false);

        grid.addComponent(name, 0, 0,0,1);
        grid.addComponent(description, 1, 0, 2, 0);
        grid.addComponent(buttons, 0, 2);

        addComponent(grid);

    }
    private TextField getTextField(String label, String field) {
        TextField textField = new TextField(label);
        textField.setWidth(250, Unit.PIXELS);
        textField.setNullRepresentation("");
        textField.addValidator(new BeanValidator(SourceModel.class, field));
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
    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);

        return buttons;
    }
}
