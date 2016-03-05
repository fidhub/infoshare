package infoshare.client.content.account;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import hashwork.client.content.MainLayout;
import hashwork.client.content.account.views.CompanyAnalyticsTab;
import hashwork.client.content.account.views.DisabledCompaniesTab;
import hashwork.client.content.account.views.ManageCompaniesTab;

/**
 * Created by hashcode on 2015/09/15.
 */
public class AccountMenu extends VerticalLayout {
    private final MainLayout main;
    private TabSheet tab;

    public AccountMenu(MainLayout main, String selectedTab) {
        this.main = main;

        VerticalLayout manageCompaniesTab = new VerticalLayout();
        manageCompaniesTab.setMargin(true);
        manageCompaniesTab.addComponent(new ManageCompaniesTab(main));

        VerticalLayout companyAnalyticsTab = new VerticalLayout();
        companyAnalyticsTab.setMargin(true);
        companyAnalyticsTab.addComponent(new CompanyAnalyticsTab(main));


        VerticalLayout disabledCompaniesTab = new VerticalLayout();
        disabledCompaniesTab.setMargin(true);
        disabledCompaniesTab.addComponent(new DisabledCompaniesTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(manageCompaniesTab, "Manage COMPANIES", null);
        tab.addTab(disabledCompaniesTab, "Disabled COMPANIES", null);
        tab.addTab(companyAnalyticsTab, "Company ANALYTICS", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(manageCompaniesTab);
        } else if (selectedTab.equals("MANAGE")) {
            tab.setSelectedTab(companyAnalyticsTab);
        } else if (selectedTab.equals("DISABLED")) {
            tab.setSelectedTab(disabledCompaniesTab);
        }
        addComponent(tab);
    }
}
