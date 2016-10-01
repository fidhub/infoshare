package infoshare.viewUI.container.home;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.home.views.OrganisationInformationTab;
import infoshare.viewUI.container.home.views.LandingPageTab;
import infoshare.viewUI.container.home.views.MessageBoardTab;


/**
 * Created by hashcode on 2015/09/15.
 */
public class HomeMenu extends VerticalLayout {
    private final MainLayout main;
    private TabSheet tab;

    public HomeMenu(MainLayout main, String selectedTab) {
        this.main = main;

        VerticalLayout homePageTab = new VerticalLayout();
        homePageTab.setMargin(true);
        homePageTab.addComponent(new LandingPageTab(main));

        VerticalLayout messageBoardTab = new VerticalLayout();
        messageBoardTab.setMargin(true);
        messageBoardTab.addComponent(new MessageBoardTab(main));


        VerticalLayout structureTab = new VerticalLayout();
        structureTab.setMargin(true);
        structureTab.addComponent(new OrganisationInformationTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(homePageTab, "Home Page", null);
        tab.addTab(messageBoardTab, "Message Board", null);
        tab.addTab(structureTab, "Company Structure", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(homePageTab);
        } else if (selectedTab.equals("MESSAGE")) {
            tab.setSelectedTab(messageBoardTab);
        } else if (selectedTab.equals("STRUCTURE")) {
            tab.setSelectedTab(structureTab);
        }
        addComponent(tab);
    }
}
