package infoshare.client.content.profile.demographics;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.profile.demographics.views.DemographicsSummaryTab;
import infoshare.client.content.profile.demographics.views.DemographicsTab;
import infoshare.client.content.profile.demographics.views.LanguagesTab;


/**
 * Created by hashcode on 2015/12/07.
 */
public class DemographicsMenu extends VerticalLayout {

    private final MainLayout main;
    private TabSheet tab;

    public DemographicsMenu(MainLayout main, String selectedTab) {
        this.main = main;

        VerticalLayout demographicsTab = new VerticalLayout();
        demographicsTab.setMargin(true);
        demographicsTab.addComponent(new DemographicsTab(main));

        VerticalLayout demographicsSummaryTab = new VerticalLayout();
        demographicsSummaryTab.setMargin(true);
        demographicsSummaryTab.addComponent(new DemographicsSummaryTab(main));



        VerticalLayout languagesTab = new VerticalLayout();
        languagesTab.setMargin(true);
        languagesTab.addComponent(new LanguagesTab(main));


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(demographicsSummaryTab, "Demographics SUMMARY", null);
        tab.addTab(demographicsTab, "Personal DEMOGRAPHICS", null);
        tab.addTab(languagesTab, "Language SKILLS", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(demographicsSummaryTab);
        } else if (selectedTab.equals("DEMOGRAPHICS")) {
            tab.setSelectedTab(demographicsTab);

        }else if (selectedTab.equals("LANGUAGES")) {
            tab.setSelectedTab(languagesTab);

        }
        addComponent(tab);
    }
}
