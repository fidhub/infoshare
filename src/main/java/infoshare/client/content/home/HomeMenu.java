package infoshare.client.content.home;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.home.views.OrganisationInformationTab;
import infoshare.client.content.home.views.LandindPageTab;
import infoshare.client.content.home.views.MessageBoardTab;


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
        homePageTab.addComponent(new LandindPageTab(main));

        VerticalLayout messageBoardTab = new VerticalLayout();
        messageBoardTab.setMargin(true);
        messageBoardTab.addComponent(new MessageBoardTab(main));


        VerticalLayout structureTab = new VerticalLayout();
        structureTab.setMargin(true);
        structureTab.addComponent(new OrganisationInformationTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(homePageTab, "Home PAGE", null);
        tab.addTab(messageBoardTab, "Message BOARD", null);
        tab.addTab(structureTab, "Company STRUCTURE", null);

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
