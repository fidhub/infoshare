package infoshare.client.content.content.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import infoshare.client.content.content.models.RawAndEditModel;

/**
 * Created by hashcode on 2015/06/24.
 */
public class PublishForm extends FormLayout{


    private final RawAndEditModel model;
    public final BeanItem<RawAndEditModel> item;
    public final FieldGroup binder;


    public Button popUpCloseBtn;

    public PublishForm() {
        model = new RawAndEditModel();
        item = new BeanItem<>(model);
        binder = new FieldGroup(item);

        popUpCloseBtn = new Button("CLOSE");
        final TextArea textArea = getTextArea("Content","content");
        final HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        buttons.addComponent(popUpCloseBtn);

        GridLayout gridLayout = new  GridLayout(4,7);
        gridLayout.setSizeFull();
        gridLayout.setSpacing(true);
        Responsive.makeResponsive(gridLayout);
        gridLayout.addComponent(textArea, 0, 0, 3, 2);
        gridLayout.addComponent(buttons,2,6);
        addComponent(gridLayout);
    }


    private TextArea getTextArea(String label,String field){
        final TextArea textArea = new TextArea(label);
        textArea.setImmediate(true);
        textArea.setWordwrap(true);
        textArea.setHeight(400.0f,Unit.PIXELS);
        textArea.setWidth(98.0f,Unit.PERCENTAGE);
        textArea.setNullRepresentation("");
        textArea.setNullRepresentation("");
        textArea.addValidator(new BeanValidator(RawAndEditModel.class, field));
        textArea.setImmediate(true);
        binder.bind(textArea,field);

        return textArea;
    }

}
