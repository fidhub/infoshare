package infoshare.client.content.content.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.content.models.EditModel;

/**
 * Created by hashcode on 2015/06/24.
 */
public class RawForm extends FormLayout {
    private final EditModel model;
    public final BeanItem<EditModel> item;
    public final FieldGroup binder;

    public Button editBtn = new Button("EDIT");

    public RawForm() {
        model = new EditModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);

        final HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        buttonsLayout.addComponent(editBtn);
        addComponent(buttonsLayout);
    }

}
