package infoshare.app.util.fields;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by hashcode on 2015/09/08.
 */
public class ButtonsHelper extends FormLayout {
    // Style The Buttons
    public static HorizontalLayout getButtons(Button save, Button edit, Button cancel, Button update, Button delete) {
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        save.setSizeFull();
        save.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        //save.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        save.addStyleName(ValoTheme.BUTTON_SMALL);
        edit.setSizeFull();
        edit.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        //edit.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        edit.addStyleName(ValoTheme.BUTTON_SMALL);
        cancel.setSizeFull();
        cancel.addStyleName(ValoTheme.BUTTON_PRIMARY);
        //cancel.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        cancel.addStyleName(ValoTheme.BUTTON_SMALL);
        update.setSizeFull();
        update.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        //update.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        update.addStyleName(ValoTheme.BUTTON_SMALL);
        delete.setSizeFull();
        delete.addStyleName(ValoTheme.BUTTON_DANGER);
        //delete.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        delete.addStyleName(ValoTheme.BUTTON_SMALL);

        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);

        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);
        edit.setVisible(false);
        return buttons;
    }
}
