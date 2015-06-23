/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infoshare.conf.util;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.ComboBox;

import java.io.Serializable;

/**
 *
 * @author Ferox
 */
public class UIComboBoxHelper<T> implements Serializable {

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

}
