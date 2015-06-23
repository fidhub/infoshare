package infoshare.client.sidebar;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by hashcode on 2015/06/23.
 */
public class Sidebar extends VerticalLayout{
    public Sidebar() {
        addComponent(new Button("Header"));
    }
}
