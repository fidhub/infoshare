package infoshare.client.content.setup.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.courseSetup.models.LessonModel;
import infoshare.client.content.setup.models.UserModel;

/**
 * Created by user9 on 2016/02/11.
 */
public class UserDetailsForm extends FormLayout {

    private final UserModel model;
    public final BeanItem<UserModel> item;
    public final FieldGroup binder;

    public TextField field = new TextField();

    public Button popUpBackBtn = new Button("Back");

    public UserDetailsForm() {
        model = new UserModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);
        final TextField UserName = getTextField("User Name", "username");
        final TextField FirstName = getTextField("First Name", "firstName");
        final TextField LastName = getTextField("Last Name", "lastName");

        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(false);
        layout.addComponent(getField());
        addComponent(layout);
        popUpBackBtn.setIcon(FontAwesome.BACKWARD);
        popUpBackBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        layout.addComponent(popUpBackBtn);

        GridLayout gridLayout = new  GridLayout(4,4);
        gridLayout.setSpacing(true);
        gridLayout.setSizeFull();
        Responsive.makeResponsive(gridLayout);

        gridLayout.addComponent(UserName, 0, 0);
        gridLayout.addComponent(FirstName, 0, 1);
        gridLayout.addComponent(LastName,0 , 2);

        addComponent(gridLayout);

    }
    private TextField getField(){
        field.setInputPrompt("Filter content ...");
        field.setWidth("260px");
        field.setIcon(FontAwesome.FILTER);
        field.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        field.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        return field;
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
}
