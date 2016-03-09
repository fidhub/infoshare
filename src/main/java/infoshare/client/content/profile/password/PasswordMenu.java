package infoshare.client.content.profile.password;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.profile.password.views.PasswordTab;


/**
 * Created by hashcode on 2015/12/07.
 */
public class PasswordMenu extends VerticalLayout {

    private final MainLayout main;
    private TabSheet tab;

    public PasswordMenu(MainLayout main, String selectedTab) {
        this.main = main;

        VerticalLayout passwordTab = new VerticalLayout();
        passwordTab.setMargin(true);
        passwordTab.addComponent(new PasswordTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(passwordTab, "Change PASSWORD", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(passwordTab);
        }
        addComponent(tab);
    }
}
