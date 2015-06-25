package infoshare.client.content.content.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.content.models.EditModel;

/**
 * Created by hashcode on 2015/06/24.
 */
public class EditForm extends FormLayout {

    private final EditModel model;
    public final BeanItem<EditModel> item;
    public final FieldGroup binder;

    public Button popUpSaveBtn = new Button();
    public Button popUpCancelBtn = new Button();

    public EditForm() {
        this.model = new EditModel();
        this.item = new BeanItem<>(model);
        this.binder = new FieldGroup(item);

        RichTextArea textEditor =  getRichTextArea("Edit Content","textEditor");

        GridLayout grid = new GridLayout(4,8);
        grid.setSizeFull();
        grid.addComponent(textEditor,0,1,8,1);
        grid.addComponent(popUpButtons(),0,2);

        addComponent(grid);
    }

    private RichTextArea getRichTextArea(String label, String field){
        final RichTextArea textEditor = new RichTextArea(label);
        textEditor.setImmediate(true);
        textEditor.setWidth(98.0f,Unit.PERCENTAGE);
        textEditor.setHeight(400.0f, Unit.PIXELS);
        textEditor.setNullRepresentation("");
        textEditor.addValidator(new BeanValidator(EditModel.class,field));
        textEditor.setImmediate(true);
        binder.bind(textEditor,field);
        return textEditor;
    }

    public HorizontalLayout popUpButtons(){
        final HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);

        popUpSaveBtn.setCaption("update");
        popUpSaveBtn.setIcon(FontAwesome.SAVE);
        popUpSaveBtn.setStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        popUpCancelBtn.setCaption("Cancel");
        popUpCancelBtn.setIcon(FontAwesome.CROSSHAIRS);
        popUpCancelBtn.setStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        buttons.addComponent(popUpSaveBtn);
        buttons.addComponent(popUpCancelBtn);

        return buttons;
    }
}
