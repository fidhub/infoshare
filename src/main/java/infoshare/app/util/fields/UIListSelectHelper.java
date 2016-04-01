package infoshare.app.util.fields;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.ListSelect;

import java.io.Serializable;

/**
 * Created by THULEH on 2016/03/31.
 */
public class UIListSelectHelper<T> extends FormLayout implements Serializable {

    public ListSelect getMultSelector(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder){
        ListSelect listSelect = new ListSelect(fieldText);
        listSelect.addValidator(new BeanValidator(fieldClass, fieldName));
        listSelect.setImmediate(true);
        listSelect.setNullSelectionAllowed(false);
        listSelect.setWidth(250, Sizeable.Unit.PIXELS);
        listSelect.setMultiSelect(true);
        binder.bind(listSelect, fieldName);
        return listSelect;
    }

}
