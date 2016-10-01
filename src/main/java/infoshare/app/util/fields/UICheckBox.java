package infoshare.app.util.fields;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;

import java.io.Serializable;

/**
 * Created by user9 on 2016/04/19.
 */
public class UICheckBox<T> extends FormLayout implements Serializable {
    public CheckBox getCheckBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        CheckBox checkBox = new CheckBox(fieldText);
        checkBox.addValidator(new BeanValidator(fieldClass, fieldName));
        checkBox.setImmediate(true);
        checkBox.setWidth(250, Unit.PIXELS);
        binder.bind(checkBox, fieldName);

        return checkBox;
    }
}
