package infoshare.client.content.content;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;

/**
 * Created by hashcode on 2015/06/23.
 */
public class ContentMenu extends VerticalLayout {
    private MainLayout main;
    private TabSheet tab;

    public ContentMenu(MainLayout main,String selectedTab) {
        this.main = main;
    }
}
