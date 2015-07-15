package infoshare.client.content.courseSetup.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.courseSetup.models.LessonModel;

/**
 * Created by codex on 2015/07/07.
 */
public class LessonForm extends FormLayout {

    private final LessonModel model;
    public final BeanItem<LessonModel> item;
    public final FieldGroup binder;


    public Button popUpSaveBtn = new Button("Save");
    public Button popUpUpdateBtn = new Button("Update");
    public Button popUpCancelBtn = new Button("Cancel");
    public LessonForm() {
        model = new LessonModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);
        final TextField lesson = getTextField("lesson","lesson");
        final TextArea description = getTextArea("Description","description");
        final RichTextArea textEditor = getRichTextArea("Content","content");

        final HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        popUpUpdateBtn.setIcon(FontAwesome.SAVE);
        popUpUpdateBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        popUpSaveBtn.setIcon(FontAwesome.SAVE);
        popUpSaveBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        popUpCancelBtn.setIcon(FontAwesome.BACKWARD);
        popUpCancelBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        buttons.addComponent(popUpUpdateBtn);
        buttons.addComponent(popUpSaveBtn);
        buttons.addComponent(popUpCancelBtn);

        GridLayout gridLayout = new  GridLayout(4,4);
        gridLayout.setSpacing(true);
        gridLayout.setSizeFull();
        Responsive.makeResponsive(gridLayout);

        gridLayout.addComponent(lesson,0,0);
        gridLayout.addComponent(description,1,0);
        gridLayout.addComponent(textEditor,0,2,3,2);
        gridLayout.addComponent(buttons,0,3);

        addComponent(gridLayout);
    }


    private RichTextArea getRichTextArea(String label, String field){
        final RichTextArea richTextArea = new RichTextArea(label);
        richTextArea.setImmediate(true);
        richTextArea.setWidth(98.0f, Unit.PERCENTAGE);
        richTextArea.setHeight(350.0f,Unit.PIXELS);
        richTextArea.setNullRepresentation("");
        richTextArea.addValidator(new BeanValidator(LessonModel.class, field));
        richTextArea.setImmediate(true);
        binder.bind(richTextArea,field);
        return richTextArea;
    }
    private TextField getTextField(String label,String field){
        TextField textField = new TextField(label);
        textField.setWidth(250, Unit.PIXELS);
        textField.setNullRepresentation("");
        textField.addValidator(new BeanValidator(LessonModel.class, field));
        textField.setImmediate(true);
        binder.bind(textField, field);

        return textField;

    }
    private TextArea getTextArea(String label, String field) {
        TextArea textArea = new TextArea(label);
        textArea.setWidth(250, Unit.PIXELS);
        textArea.setNullRepresentation("");
        textArea.addValidator(new BeanValidator(LessonModel.class, field));
        textArea.setImmediate(true);
        binder.bind(textArea, field);
        return textArea;
    }


}
