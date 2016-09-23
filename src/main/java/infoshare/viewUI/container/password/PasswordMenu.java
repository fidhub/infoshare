package infoshare.viewUI.container.password;

import com.vaadin.ui.VerticalLayout;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.menu.Menu;
import infoshare.viewUI.container.password.tab.PasswordTab;

/**
 * Created by hashcode on 2015/06/23.
 */
public class PasswordMenu extends Menu {

    public PasswordMenu(MainLayout app, String tab) {
        super(app, tab);

        final VerticalLayout changePasswordTab = new VerticalLayout();
        changePasswordTab.setMargin(true);
        changePasswordTab.addComponent(new PasswordTab(getMain()));

        getTab().addTab(changePasswordTab, "Change Password", null);

        switch (tab) {
            case "LANDING":
                getTab().setSelectedTab(changePasswordTab);
                break;
        }
        addComponent(getTab());

    }
}
