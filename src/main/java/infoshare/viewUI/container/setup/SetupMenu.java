package infoshare.viewUI.container.setup;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.setup.views.PersonView;
import infoshare.viewUI.container.setup.views.RoleView;

/**
 * Created by hashcode on 2015/06/23.
 */
public class SetupMenu  extends VerticalLayout {
    private MainLayout main;
    private TabSheet tab;

    public SetupMenu(MainLayout main,String selectedTab) {

        this.main = main;

        final VerticalLayout userView = new VerticalLayout();
        userView.setMargin(true);
        userView.addComponent(new PersonView(main));

        final VerticalLayout roleView = new VerticalLayout();
        roleView.setMargin(true);
        roleView.addComponent(new RoleView(main));

               tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");



        tab.addTab(userView, "Add System Users", null);
        tab.addTab(roleView, "Add System Roles", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(userView);
        } else if (selectedTab.equals("ROLES")) {
            tab.setSelectedTab(roleView);
        }
        addComponent(tab);

    }
}