package infoshare.client.content.setup;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;

/**
 * Created by hashcode on 2015/06/23.
 */
public class SetupMenu  extends VerticalLayout {
    private MainLayout main;
    private TabSheet tab;

    public SetupMenu(MainLayout main,String selectedTab) {
        this.main = main;
    }
}
