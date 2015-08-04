package infoshare.client.content.menu;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;

/**
 * Created by hashcode on 2015/06/23.
 */
public class Menu extends VerticalLayout{
    private  MainLayout main;
    private  TabSheet tab;


    public Menu(MainLayout app, String selectedTab) {
        main = app;
        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
    }
    public Menu() {
    }
    public MainLayout getMain() {
        return main;
    }

    public TabSheet getTab() {
        return tab;
    }
}
