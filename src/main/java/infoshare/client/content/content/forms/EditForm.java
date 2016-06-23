package infoshare.client.content.content.forms;

import com.google.common.io.Files;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.conf.RestUtil;
import infoshare.app.facade.FileResultsFacade;
import infoshare.client.content.content.models.ContentModel;
import infoshare.domain.storage.FileResults;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/24.
 */
public class EditForm extends FormLayout implements Upload.Receiver, Upload.SucceededListener {

    private final ContentModel model;
    public final BeanItem<ContentModel> item;
    public final FieldGroup binder;


    public Button popUpUpdateBtn = new Button("Save");
    public Button popUpCancelBtn = new Button("Back");
    public final ComboBox popUpContentTypeCmb;
    public final ComboBox popUpCategoryCmb;
    public final ComboBox popUpSourceCmb;
    public Upload upload;
    private File file;
    private  RichTextArea textEditor;
    public EditForm() {
        model = new ContentModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);
        upload = new Upload("Upload",this);
        upload.addSucceededListener(this);
        popUpContentTypeCmb = getComboBox("ContentFiles Type","contentType");
        popUpCategoryCmb = getComboBox("Category","category");
        popUpSourceCmb = getComboBox("source","source");
        textEditor = getRichTextArea("ContentFiles","content");

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
        combo.addComponent(upload);
        //combo.addComponent(example.getUI());

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

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        try {
            file = new File(Files.createTempDir(), filename);
            //file.deleteOnExit();
            return new FileOutputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent event) {
        Set<FileResults> set = FileResultsFacade.fileResultsService.save(file.getAbsoluteFile().toString());
        String url="";
        for (FileResults fileResults: set.stream().filter(file -> file.getSize().equalsIgnoreCase("Standard")).collect(Collectors.toSet()))
            url =RestUtil.URL+fileResults.getUrl();

        textEditor.setValue(textEditor.getValue()+" "+"<img src="+url+">");

    }
}
