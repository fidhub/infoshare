package infoshare.app.util.fields;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.Sizeable;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;

import java.io.Serializable;

/**
 * Created by hashcode on 2015/09/07.
 */
public class UIComponentHelper<T> extends FormLayout implements Serializable {

    private Label createNewLabel(String value) {
        Label label = new Label(value);
        label.setSizeUndefined();
        label.addStyleName("h4");
        return label;
    }

    public TextField getGridTextField(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        TextField textField = new TextField(fieldText);
        textField.setWidth(250, Sizeable.Unit.PIXELS);
        textField.setNullRepresentation("");
        textField.addValidator(new BeanValidator(fieldClass, fieldName));
        textField.setImmediate(true);
        binder.bind(textField, fieldName);
        return textField;
    }

    public TextField getTextField(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        TextField textField = new TextField(fieldText);
        textField.setSizeFull();
        textField.setNullRepresentation("");
        textField.addValidator(new BeanValidator(fieldClass, fieldName));
        textField.setImmediate(true);
        binder.bind(textField, fieldName);
        return textField;
    }

    public TextField getGridBigDecimalTextField(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        TextField textField = new TextField();
        textField.setCaption(fieldText);

//        textField.setConverter(new BigDecimalConverter());
        textField.setWidth(250, Sizeable.Unit.PIXELS);
        textField.setNullRepresentation("");
        textField.setRequiredError("Field is Required");
//        textField.setComponentError(new UserError("Invalid format"));
        textField.addValidator(new BeanValidator(fieldClass, fieldName));
        textField.setImmediate(true);
        binder.bind(textField, fieldName);

        return textField;
    }

    public TextField getBigDecimalTextField(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder, int fieldWidth) {
        TextField textField = new TextField();
        textField.setCaption(fieldText);
//        textField.setConverter(new BigDecimalConverter());
        textField.setWidth(fieldWidth, Sizeable.Unit.PIXELS);
        textField.setNullRepresentation("");
//        textField.setRequiredError("Field is Required");
        textField.setComponentError(new UserError("Bad value"));
        textField.addValidator(new BeanValidator(fieldClass, fieldName));
        textField.setImmediate(true);
        binder.bind(textField, fieldName);
        return textField;
    }

    public TextArea getTextAreaFull(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        TextArea textArea = new TextArea(fieldText);
//        textArea.setWidth(510, Sizeable.Unit.PIXELS);
        textArea.setSizeFull();
        textArea.setNullRepresentation("");
        //textArea.addValidator(new BeanValidator(fieldClass.getClass(), fieldName));
        textArea.setImmediate(true);
        binder.bind(textArea, fieldName);
        return textArea;

    }

    public TextArea getGridTextArea(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        TextArea textArea = new TextArea(fieldText);
        textArea.setWidth(250, Sizeable.Unit.PIXELS);
        textArea.setNullRepresentation("");
        textArea.addValidator(new BeanValidator(fieldClass.getClass(), fieldName));
        textArea.setImmediate(true);
        binder.bind(textArea, fieldName);
        return textArea;
    }


    public TextArea getTextArea(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder, int fieldWidth) {
        TextArea textArea = new TextArea(fieldText);
        textArea.setWidth(fieldWidth, Sizeable.Unit.PIXELS);
        textArea.setNullRepresentation("");
        //textArea.addValidator(new BeanValidator(fieldClass.getClass(), fieldName));
        textArea.setImmediate(true);
        binder.bind(textArea, fieldName);
        return textArea;

    }

    public PasswordField getPasswordField(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        PasswordField passwordField = new PasswordField(fieldText);
        passwordField.setWidth(250, Sizeable.Unit.PIXELS);
        passwordField.setNullRepresentation("");
        passwordField.addValidator(new BeanValidator(fieldClass, fieldName));
        passwordField.setImmediate(true);
        binder.bind(passwordField, fieldName);
        return passwordField;
    }

    public PasswordField getPasswordField(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder, int fieldWidth) {
        PasswordField passwordField = new PasswordField(fieldText);
        passwordField.setWidth(fieldWidth, Sizeable.Unit.PIXELS);
        passwordField.setNullRepresentation("");
        passwordField.addValidator(new BeanValidator(fieldClass, fieldName));
        passwordField.setImmediate(true);
        binder.bind(passwordField, fieldName);
        return passwordField;
    }

    public DateField getGridDateField(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        DateField dateField = new DateField(fieldText);
        dateField.setWidth(250, Sizeable.Unit.PIXELS);
        dateField.addValidator(new BeanValidator(fieldClass, fieldName));
        dateField.setImmediate(true);
        dateField.setDateFormat("dd-MM-yyyy");
        binder.bind(dateField, fieldName);
        return dateField;
    }

    public DateField getDateField(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        DateField dateField = new DateField(fieldText);
        dateField.setSizeFull();
        dateField.addValidator(new BeanValidator(fieldClass, fieldName));
        dateField.setImmediate(true);
        binder.bind(dateField, fieldName);
        return dateField;
    }

    public CheckBox getGridCheckBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        CheckBox checkbox = new CheckBox(fieldText);
        checkbox.setWidth(250, Sizeable.Unit.PIXELS);
        checkbox.setHeight(250, Sizeable.Unit.PIXELS);
//        checkbox.setNullRepresentation("");
        checkbox.addValidator(new BeanValidator(fieldClass, fieldName));
        checkbox.setImmediate(true);
        binder.bind(checkbox, fieldName);
        return checkbox;
    }

    public CheckBox getCheckBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        CheckBox checkbox = new CheckBox(fieldText);
        checkbox.setSizeFull();
//        checkbox.setNullRepresentation("");
        checkbox.addValidator(new BeanValidator(fieldClass, fieldName));
        checkbox.setImmediate(true);
        binder.bind(checkbox, fieldName);
        return checkbox;
    }

    public Label getErrorLabel() {
        Label label = new Label();
        label.setValue("");
        label.setStyleName("errorstyle");
        label.setHeight("30px");
        return label;
    }
}
