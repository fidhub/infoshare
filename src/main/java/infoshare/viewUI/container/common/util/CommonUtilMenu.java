package infoshare.viewUI.container.common.util;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.common.util.views.MailTab;
import infoshare.viewUI.container.common.util.views.StatusTab;
import infoshare.viewUI.container.common.util.views.StorageUrlTab;


/**
 * Created by hashcode on 2015/12/12.
 */
public class CommonUtilMenu extends VerticalLayout {
    private final MainLayout main;
    private TabSheet tab;

    public CommonUtilMenu(MainLayout main, String selectedTab) {
        this.main = main;
        setSizeFull();


        VerticalLayout statusTab = new VerticalLayout();
        statusTab.setMargin(true);
        statusTab.addComponent(new StatusTab(main));

        VerticalLayout linksTab = new VerticalLayout();
        linksTab.setMargin(true);
        linksTab.addComponent(new StorageUrlTab(main));

        VerticalLayout mailTab = new VerticalLayout();
        mailTab.setMargin(true);
        mailTab.addComponent(new MailTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(mailTab, "Mail SETTINGS", null);
        tab.addTab(statusTab, "Status STATE", null);
        tab.addTab(linksTab, "System LINKS", null);


        if (selectedTab.equals("STATUS")) {
            tab.setSelectedTab(statusTab);
        } else if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(mailTab);
        } else if (selectedTab.equals("LINKS")) {
            tab.setSelectedTab(linksTab);
        }
        addComponent(tab);
    }
}
