package infoshare.client.content.content.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.content.models.ContentModel;

/**
 * Created by hashcode on 2015/06/24.
 */
public class RawAndEditForm extends FormLayout {

    private final ContentModel model;
    public final BeanItem<ContentModel> item;
    public final FieldGroup binder;


    public Button popUpUpdateBtn = new Button("UPDATE");
    public Button popUpCancelBtn = new Button("CANCEL");
    public final ComboBox popUpContentTypeCmb;
    public final ComboBox popUpCategoryCmb;
    public final ComboBox popUpSourceCmb;
    public RawAndEditForm() {
        model = new ContentModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);
        popUpContentTypeCmb = getComboBox("Content Type","contentType");
        popUpCategoryCmb = getComboBox("Category","category");
        popUpSourceCmb = getComboBox("source","source");

        final RichTextArea textEditor = getRichTextArea("Content","content");

        final HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        popUpUpdateBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        popUpUpdateBtn.setIcon(FontAwesome.SAVE);
        popUpCancelBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        popUpCancelBtn.setIcon(FontAwesome.BACKWARD);
        buttons.addComponent(popUpUpdateBtn);
        buttons.addComponent(popUpCancelBtn);

        final HorizontalLayout combo = new HorizontalLayout();
        combo.setSpacing(true);
        combo.addComponent(popUpContentTypeCmb);
        combo.addComponent(popUpCategoryCmb);

        GridLayout gridLayout = new  GridLayout(4,7);
        gridLayout.setSpacing(true);
        gridLayout.setSizeFull();
        Responsive.makeResponsive(gridLayout);
        gridLayout.addComponent(textEditor, 0, 0,3,2);
        gridLayout.addComponent(combo,0,3);
        gridLayout.addComponent(buttons,0,5);

        addComponent(gridLayout);
    }


  private RichTextArea getRichTextArea(String label, String field){
      final RichTextArea richTextArea = new RichTextArea(label);
      richTextArea.setImmediate(true);
      richTextArea.setWidth(98.0f, Unit.PERCENTAGE);
      richTextArea.setHeight(400.0f,Unit.PIXELS);
      richTextArea.setNullRepresentation("");
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
