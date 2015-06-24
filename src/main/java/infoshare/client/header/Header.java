package infoshare.client.header;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by hashcode on 2015/06/23.
 */
public class Header extends VerticalLayout{
    public Header() {
        setSizeFull();
        addStyleName(ValoTheme.MENU_PART);
        addComponent(new Button("Header"));
    }
}
