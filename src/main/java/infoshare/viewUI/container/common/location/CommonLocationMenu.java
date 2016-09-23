package infoshare.viewUI.container.common.location;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.common.location.views.AddressTypeTab;
import infoshare.viewUI.container.common.location.views.ContactTypeTab;
import infoshare.viewUI.container.common.location.views.LocationTypeTab;


/**
 * Created by hashcode on 2015/12/12.
 */
public class CommonLocationMenu extends VerticalLayout {
    private final MainLayout main;
    private TabSheet tab;

    public CommonLocationMenu(MainLayout main, String selectedTab) {
        this.main = main;
        setSizeFull();
        VerticalLayout addressTypeTab = new VerticalLayout();
        addressTypeTab.setMargin(true);
        addressTypeTab.addComponent(new AddressTypeTab(main));

        VerticalLayout contactTypeTab = new VerticalLayout();
        contactTypeTab.setMargin(true);
        contactTypeTab.addComponent(new ContactTypeTab(main));

        VerticalLayout locationTypeTab = new VerticalLayout();
        locationTypeTab.setMargin(true);
        locationTypeTab.addComponent(new LocationTypeTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(locationTypeTab, "Location Types ", null);
        tab.addTab(addressTypeTab, "Address Types", null);
        tab.addTab(contactTypeTab, "Contact Types", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(locationTypeTab);
        } else if (selectedTab.equals("ADDRESS")) {
            tab.setSelectedTab(addressTypeTab);
        } else if (selectedTab.equals("CONTACT")) {
            tab.setSelectedTab(contactTypeTab);
        }

        addComponent(tab);
    }
}
