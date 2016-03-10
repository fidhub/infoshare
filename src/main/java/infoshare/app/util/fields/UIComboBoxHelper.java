package infoshare.app.util.fields;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Created by hashcode on 2015/09/07.
 */
public class UIComboBoxHelper<T> extends FormLayout implements Serializable {

    public ComboBox getComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder, final Consumer<ComboBox> getData) {
        ComboBox comboBox = new ComboBox(fieldText);
        getData.accept(comboBox);
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }
    public ComboBox getUploadComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }
    public ComboBox getEmptyComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getComboBoxFull(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder, final Consumer<ComboBox> getData) {
        ComboBox comboBox = new ComboBox(fieldText);
        getData.accept(comboBox);
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setSizeFull();
        binder.bind(comboBox, fieldName);
        return comboBox;
    }
}
