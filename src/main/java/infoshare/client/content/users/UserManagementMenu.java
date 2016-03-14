package infoshare.client.content.users;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.users.views.UserTab;

/**
 * Created by user9 on 2016/03/14.
 */
public class UserManagementMenu extends VerticalLayout {
    private MainLayout main;
    private TabSheet tab;

    public UserManagementMenu(MainLayout main, String selectedTab) {
        this.main = main;

        final VerticalLayout userView = new VerticalLayout();
        userView.setMargin(true);
        userView.addComponent(new UserTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(userView, "Admin Users", null);

        if (selectedTab.equalsIgnoreCase("LANDING")) {
            tab.setSelectedTab(userView);
        }

        addComponent(tab);
    }
}
