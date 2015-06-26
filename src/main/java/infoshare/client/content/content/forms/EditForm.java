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

    public Button editBtn = new Button("EDIT");
    public Button deleteBtn = new Button("DELETE");
    public EditForm() {
        model = new EditModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);

        final HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        buttonsLayout.addComponent(editBtn);
        buttonsLayout.addComponent(deleteBtn);

        addComponent(buttonsLayout);
    }


 /* private RichTextArea getRichTextArea(String label, String field){
        final RichTextArea textArea = new RichTextArea(label);
        textArea.setImmediate(true);
        textArea.setWidth(98.0f,Unit.PERCENTAGE);
        textArea.setHeight(400.0f, Unit.PIXELS);
        textArea.setNullRepresentation("");
        textArea.addValidator(new BeanValidator(EditModel.class,field));
        textArea.setImmediate(true);
        binder.bind(textArea,field);
        return textArea;
    }

    public HorizontalLayout popUpButtons(){
        final HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);

        popUpEditBtn.setCaption("update");
        popUpEditBtn.setIcon(FontAwesome.SAVE);
        popUpEditBtn.setStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        popUpCloseBtn.setCaption("Cancel");
        popUpCloseBtn.setIcon(FontAwesome.CROSSHAIRS);
        popUpCloseBtn.setStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        buttons.addComponent(popUpEditBtn);
        buttons.addComponent(popUpCloseBtn);

        return buttons;
    }*/
}
