package infoshare.client.content.ContentType.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import infoshare.client.content.ContentType.models.ContentTypeModel;


/**
 * Created by codex on 2015/06/25.
 */
public class ContentTypeForm extends FormLayout {

    private final ContentTypeModel model;
    public final BeanItem<ContentTypeModel> item;
    public final FieldGroup binder;

    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public ContentTypeForm(){

        model = new ContentTypeModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);

        ComboBox name = new ComboBox();         //getComboBox("content type", "contentTyeName");
        ComboBox description = new ComboBox();  // getComboBox("Description", "contentTyeDescription");

        GridLayout grid = new GridLayout(4, 8);
        grid.setSizeFull();

        HorizontalLayout buttons = getButtons();
        update.setVisible(false);
        delete.setVisible(false);

        grid.addComponent(name, 0, 0,0,1);
        grid.addComponent(description, 1, 0, 2, 0);
        grid.addComponent(buttons, 0, 3, 2, 3);

        addComponent(grid);

    }

  /*  private ComboBox getComboBox(String label, String field) {
        ComboBox comboBox = new ComboBox(label);
        comboBox.setWidth(250, Unit.PIXELS);
        comboBox.addValidator(new BeanValidator(RoleModel.class, field));
        comboBox.setImmediate(true);
        binder.bind(comboBox, field);
        return comboBox;
    }
*/
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
