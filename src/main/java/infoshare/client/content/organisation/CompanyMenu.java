package infoshare.client.content.organisation;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import hashwork.client.content.MainLayout;
import hashwork.client.content.company.places.views.AddContactsTab;
import hashwork.client.content.company.places.views.CompanyProfileTab;
import hashwork.client.content.company.places.views.DepartmentsTab;
import hashwork.client.content.company.places.views.OfficesTab;


/**
 * Created by hashcode on 2015/12/28.
 */
public class CompanyMenu extends VerticalLayout {
    private final MainLayout main;
    private TabSheet tab;

    public CompanyMenu(MainLayout main, String selectedTab) {
        this.main = main;
        setSizeFull();
        VerticalLayout deprtmentsTab = new VerticalLayout();
        deprtmentsTab.setMargin(true);
        deprtmentsTab.addComponent(new DepartmentsTab(main));

        VerticalLayout companyProfileTab = new VerticalLayout();
        companyProfileTab.setMargin(true);
        companyProfileTab.addComponent(new CompanyProfileTab(main));

        VerticalLayout officesTab = new VerticalLayout();
        officesTab.setMargin(true);
        officesTab.addComponent(new OfficesTab(main));

        VerticalLayout addContactsTab = new VerticalLayout();
        addContactsTab.setMargin(true);
        addContactsTab.addComponent(new AddContactsTab(main));


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(companyProfileTab, "Company PROFILE", null);
        tab.addTab(deprtmentsTab, "Departments", null);
        tab.addTab(officesTab, "Offices", null);
        tab.addTab(addContactsTab, "Add CONTACTS", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(companyProfileTab);
        } else if (selectedTab.equals("DEPT")) {
            tab.setSelectedTab(deprtmentsTab);
        } else if (selectedTab.equals("OFFICES")) {
            tab.setSelectedTab(officesTab);
        } else if (selectedTab.equals("CONTACTS")) {
            tab.setSelectedTab(addContactsTab);
        }

        addComponent(tab);
    }
}
