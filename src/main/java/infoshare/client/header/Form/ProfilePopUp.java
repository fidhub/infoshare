package infoshare.client.header.Form;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.content.models.ContentModel;
import com.vaadin.ui.Button.ClickEvent;

import java.io.File;
/**
 * Created by THULEBONA on 2015/12/03.
 */

public class ProfilePopUp extends Window {
    private final ContentModel model;
    public final BeanItem<ContentModel> item;
    public final FieldGroup binder;

    public ProfilePopUp() {
        model = new ContentModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);
        setWidth("300px");
        setHeight("500px");
        addStyleName("PROFILE");
        setResizable(false);
        setClosable(false);
        setDraggable(false);
        setModal(true);
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE, null);
        VerticalLayout layout = getLayout();
        setContent(layout);
    }
    public VerticalLayout getLayout(){
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addComponent(getLogo());
        layout.addComponent(getTextField("First Name", "contentType"));
        layout.addComponent(getTextField("Last Name", "contentType"));
        layout.addComponent(getTextField("Occupation", "contentType"));
        Button changePass = new Button("change Password");
        changePass.setStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        changePass.setIcon(FontAwesome.LOCK);
        changePass.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        layout.addComponent(changePass);
        layout.addComponent(getButtons());

        return  layout;
    }
    private HorizontalLayout getButtons(){
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        Button save = new Button("Save");
        save.setStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        save.setIcon(FontAwesome.SAVE);
        save.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        Button cancel = new Button("Cancel");
        cancel.setStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        cancel.setIcon(FontAwesome.ARROW_LEFT);
        cancel.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        cancel.addClickListener(ClickEvent -> {
            this.setModal(false);
            this.close();
        });

        layout.addComponent(save);
        layout.addComponent(cancel);
        return layout;
    }
    private HorizontalLayout getLogo(){
        final HorizontalLayout logo = new HorizontalLayout();
        logo.setSpacing(true);
        logo.setMargin(true);
        FileResource resource = new FileResource(
                new File("src/main/webapp/VAADIN/themes/dashboard/Kujali Logo.png"));
        Image logoImage = new Image(null,resource);
        logoImage.addStyleName("logo-header-image");
        logoImage.setHeight(80.0f, Unit.PIXELS);
        logoImage.setWidth(40.0f, Unit.PERCENTAGE);
        logo.addComponent(logoImage);
        logo.setComponentAlignment(logoImage, Alignment.MIDDLE_LEFT);
        return logo;
    }
    private TextField getTextField(String label, String field){
        final TextField textField = new TextField(label);
        textField.setImmediate(true);
        textField.setWidth(90.0f, Unit.PERCENTAGE);
        textField.setReadOnly(true);
       // textField.setHeight(400.0f,Unit.PIXELS);
        textField.setNullRepresentation("");
      //  textField.addValidator(new BeanValidator(ContentModel.class, field));
        textField.setImmediate(true);
     //   binder.bind(textField,field);
        return textField;
    }
}
