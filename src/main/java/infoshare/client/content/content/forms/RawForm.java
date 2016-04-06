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
    public final Button saveBtn;
    public final Button backBtn ;
    public final ComboBox popUpContentTypeCmb;
    public final ComboBox popUpCategoryCmb;
    public final ComboBox popUpSourceCmb;
    ImmediateUploadExample example = new ImmediateUploadExample();
    public RawForm() {
        model = new ContentModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);

        popUpContentTypeCmb = getComboBox("ContentFiles Type","contentType");
        popUpCategoryCmb = getComboBox("Category","category");
        popUpSourceCmb = getComboBox("source","source");

        saveBtn = new Button("Save");
        saveBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        saveBtn.setIcon(FontAwesome.SAVE);
        backBtn = new Button("Back");
        backBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        backBtn.setIcon(FontAwesome.ARROW_LEFT);

        RichTextArea textEditor = getRichTextArea("ContentFiles","content");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.addComponent(saveBtn);
        layout.addComponent(backBtn);

        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.addComponent(textEditor);

        final HorizontalLayout combo = new HorizontalLayout();
        combo.setSpacing(true);
        combo.addComponent(popUpContentTypeCmb);
        combo.addComponent(popUpCategoryCmb);
        combo.addComponent(example);

        GridLayout gridLayout = new  GridLayout(4,7);
        gridLayout.setSpacing(true);
        gridLayout.setSizeFull();
        Responsive.makeResponsive(gridLayout);
        gridLayout.addComponent(textEditor, 0, 0,3,2);
        gridLayout.addComponent(combo,0,3);
        gridLayout.addComponent(layout,0,5);

        addComponent(gridLayout);


    }
    private RichTextArea getRichTextArea(String label, String field){
        RichTextArea richTextArea = new RichTextArea(label);
        richTextArea.setImmediate(true);
        richTextArea.setWidth(99.0f, Unit.PERCENTAGE);
        richTextArea.setHeight(400.0f, Unit.PIXELS);
        richTextArea.setNullRepresentation("");
        richTextArea.setReadOnly(true);
        richTextArea.addValidator(new BeanValidator(ContentModel.class, field));
        richTextArea.setImmediate(true);
        binder.bind(richTextArea,field);
        return richTextArea;
    }
    private ComboBox getComboBox(String label, String field){
        final ComboBox comboBox = new ComboBox(label);
        comboBox.setImmediate(true);
        comboBox.addValidator(new BeanValidator(ContentModel.class, field));
        comboBox.setNullSelectionAllowed(false);
        binder.bind(comboBox,field);
        return comboBox;
    }
}
