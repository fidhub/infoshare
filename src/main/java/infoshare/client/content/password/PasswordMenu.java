package infoshare.client.content.password;

import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.menu.Menu;
import infoshare.client.content.password.tab.PasswordTab;

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
