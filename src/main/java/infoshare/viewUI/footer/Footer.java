
package infoshare.viewUI.footer;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hashcode on 2015/06/23.
 */
public class Footer extends VerticalLayout {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
    public Footer() {
        setSizeFull();

        Label footer = new Label("<p align='center'> &copy; All right Reserved Kujali Living Hub "
                + dateFormat.format(new Date()), ContentMode.HTML);
        footer.addStyleName(ValoTheme.LABEL_H4);
        footer.addStyleName(ValoTheme.LABEL_COLORED);
        this.addComponent(footer);

    }
}