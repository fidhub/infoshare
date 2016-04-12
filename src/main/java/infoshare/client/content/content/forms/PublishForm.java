package infoshare.client.content.content.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.content.models.ContentModel;

/**
 * Created by hashcode on 2015/06/24.
 */
public class PublishForm extends FormLayout{


    private final ContentModel model;
    public final BeanItem<ContentModel> item;
    public final FieldGroup binder;
    public Button popUpCloseBtn =new Button("CANCEL");
    public Button putToEdit =new Button("Re-Edited");

    public PublishForm() {
        model = new ContentModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);

        final RichTextArea richTextArea = getTextArea("Content","content");
        final HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        popUpCloseBtn.setIcon(FontAwesome.ARROW_CIRCLE_LEFT);
        popUpCloseBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);

        putToEdit.setIcon(FontAwesome.EDIT);
        putToEdit.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        buttons.addComponent(putToEdit);
        buttons.addComponent(popUpCloseBtn);
        GridLayout gridLayout = new  GridLayout(4,7);
        gridLayout.setSizeFull();
        gridLayout.setSpacing(true);
        Responsive.makeResponsive(gridLayout);
        gridLayout.addComponent(richTextArea, 0, 0, 3, 2);
        gridLayout.addComponent(buttons,0,5);
        addComponent(gridLayout);
    }

    private RichTextArea getTextArea(String label,String field){
        final RichTextArea richTextArea = new RichTextArea(label);
        richTextArea.setImmediate(true);
        richTextArea.setHeight(450.0f, Unit.PIXELS);
        richTextArea.setWidth(98.0f, Unit.PERCENTAGE);
        richTextArea.setNullRepresentation("");
        richTextArea.setReadOnly(true);
        richTextArea.addValidator(new BeanValidator(ContentModel.class, field));
        richTextArea.setImmediate(true);
        binder.bind(richTextArea,field);

        return richTextArea;
    }

}
