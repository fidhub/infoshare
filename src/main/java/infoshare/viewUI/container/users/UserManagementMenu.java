package infoshare.viewUI.container.users;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.users.views.ActiveUsersTab;
import infoshare.viewUI.container.users.views.DisableView;

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
        userView.addComponent(new ActiveUsersTab(main));

        final VerticalLayout disableView = new VerticalLayout();
        disableView.setMargin(true);
        disableView.addComponent(new DisableView(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(userView, "Admin Users", null);
        tab.addTab(disableView, "Disabled Users", null);

        if (selectedTab.equalsIgnoreCase("LANDING")) {
            tab.setSelectedTab(userView);
        } else if (selectedTab.equalsIgnoreCase("DISABLE")) {
            tab.setSelectedTab(disableView);
        }

        addComponent(tab);
    }
}
