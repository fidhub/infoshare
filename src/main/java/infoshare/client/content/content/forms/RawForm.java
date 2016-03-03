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
 * Created by user9 on 2016/02/16.
 */
public class RawForm extends FormLayout {

    private final ContentModel model;
    public final BeanItem<ContentModel> item;
    public final FieldGroup binder;
    public final Button putEdited ;
    public final Button backBtn ;

    public RawForm() {
        model = new ContentModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);
        putEdited = new Button("Put To Edited");
        putEdited.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        putEdited.setIcon(FontAwesome.SAVE);
        backBtn = new Button("Back");
        backBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        backBtn.setIcon(FontAwesome.ARROW_LEFT);

        TextArea textEditor = getRichTextArea("ContentFiles","content");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.addComponent(putEdited);
        layout.addComponent(backBtn);

        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setEnabled(false);
        horizontalLayout.setSizeFull();
        horizontalLayout.addComponent(textEditor);


        GridLayout gridLayout = new  GridLayout(4,7);
        gridLayout.setSpacing(true);
        gridLayout.setSizeFull();
        Responsive.makeResponsive(gridLayout);
        gridLayout.addComponent(textEditor, 0, 0,3,2);
        gridLayout.addComponent(layout,0,5);

        addComponent(gridLayout);



    }
    private TextArea getRichTextArea(String label, String field){
        TextArea textArea = new TextArea(label);
        textArea.setImmediate(true);
        textArea.setWidth(99.0f, Unit.PERCENTAGE);
        textArea.setHeight(400.0f, Unit.PIXELS);
        textArea.setNullRepresentation("");
        textArea.setReadOnly(true);
        textArea.addValidator(new BeanValidator(ContentModel.class, field));
        textArea.setImmediate(true);
        binder.bind(textArea,field);
        return textArea;
    }
}
