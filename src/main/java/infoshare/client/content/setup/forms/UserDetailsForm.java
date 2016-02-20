package infoshare.client.content.setup.forms;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by user9 on 2016/02/11.
 */
public class UserDetailsForm extends FormLayout {
    public TextField field = new TextField();

    public UserDetailsForm() {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(false);
        layout.addComponent(getField());
      addComponent(layout);
    }
    private TextField getField(){
        field.setInputPrompt("Filter content ...");
        field.setWidth("260px");
        field.setIcon(FontAwesome.FILTER);
        field.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        field.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        return field;
    }
}
