package infoshare.client.content.content.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.content.models.EditModel;

/**
 * Created by hashcode on 2015/06/24.
 */
public class PublishForm extends FormLayout{

    private final EditModel model;
    public final BeanItem<EditModel> item;
    public final FieldGroup binder;
    public Button viewContent = new Button("View Content");

    public PublishForm() {
        this.model = new EditModel();
        this.item = new BeanItem<>(model);
        this.binder = new FieldGroup(item);
        addComponent(viewContent);
    }
}
