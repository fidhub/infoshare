package infoshare.client.content.account;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.account.views.DisabledOrganisationTab;
import infoshare.client.content.account.views.ManageOrganisationTab;
import infoshare.client.content.account.views.OrganisationAnalyticsTab;

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
        manageCompaniesTab.addComponent(new ManageOrganisationTab(main));

        VerticalLayout companyAnalyticsTab = new VerticalLayout();
        companyAnalyticsTab.setMargin(true);
        companyAnalyticsTab.addComponent(new OrganisationAnalyticsTab(main));

        VerticalLayout disabledCompaniesTab = new VerticalLayout();
        disabledCompaniesTab.setMargin(true);
        disabledCompaniesTab.addComponent(new DisabledOrganisationTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(manageCompaniesTab, "Manage Organisation", null);
        tab.addTab(disabledCompaniesTab, "Disabled Organisation", null);
        tab.addTab(companyAnalyticsTab, "Organisation Analytics", null);

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
