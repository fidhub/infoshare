package infoshare.client.content.organisation.users;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.organisation.places.views.AddContactsTab;
import infoshare.client.content.organisation.places.views.OrganisationProfileTab;


/**
 * Created by hashcode on 2015/12/29.
 */
public class OrganisationMembersMenu extends VerticalLayout {
    private final MainLayout main;
    private TabSheet tab;

    public OrganisationMembersMenu(MainLayout main, String selectedTab) {
        this.main = main;
        setSizeFull();

        VerticalLayout companyProfileTab = new VerticalLayout();
        companyProfileTab.setMargin(true);
        companyProfileTab.addComponent(new OrganisationProfileTab(main));



        VerticalLayout addContactsTab = new VerticalLayout();
        addContactsTab.setMargin(true);
        addContactsTab.addComponent(new AddContactsTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(companyProfileTab, "Organisation MEMBERS", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(companyProfileTab);
        } else if (selectedTab.equals("CONTACTS")) {
            tab.setSelectedTab(addContactsTab);
        }

        addComponent(tab);
    }
}
