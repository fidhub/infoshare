package infoshare.client.content.setup;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.views.AddressView;
import infoshare.client.content.setup.views.ContactView;
import infoshare.client.content.setup.views.RoleView;
import infoshare.client.content.setup.views.UserView;

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
        userView.addComponent(new UserView(main));


        final VerticalLayout roleView = new VerticalLayout();
        roleView.setMargin(true);
        roleView.addComponent(new RoleView(main));

        final VerticalLayout address = new VerticalLayout();
        address.setMargin(true);
        address.addComponent(new AddressView(main));

        final VerticalLayout contact = new VerticalLayout();
        contact.setMargin(true);
        contact.addComponent(new ContactView(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(userView, "Add System USERS", null);
        tab.addTab(roleView, "Add System ROLES", null);
        tab.addTab(address, "Add Address ", null);
        tab.addTab(contact, "Add Contact ", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(userView);
        } else if (selectedTab.equals("ROLES")) {
            tab.setSelectedTab(roleView);
        } else if (selectedTab.equals("ADDRESS")) {
            tab.setSelectedTab(address);
        } else if (selectedTab.equals("CONTACT")) {
            tab.setSelectedTab(contact);
        }

        addComponent(tab);

    }
}