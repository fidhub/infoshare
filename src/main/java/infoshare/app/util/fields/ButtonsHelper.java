package infoshare.app.util.fields;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by hashcode on 2015/09/08.
 */
public class ButtonsHelper {
    // Style The Buttons
    public static HorizontalLayout getButtons(
            Button save,
            Button edit,
            Button cancel,
            Button update,
            Button delete) {
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        save.setSizeFull();
        save.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        edit.setSizeFull();
        edit.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        cancel.setSizeFull();
        cancel.addStyleName(ValoTheme.BUTTON_PRIMARY);
        update.setSizeFull();
        update.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        delete.setSizeFull();
        delete.addStyleName(ValoTheme.BUTTON_DANGER);

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
