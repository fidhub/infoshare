package infoshare.viewUI.container.profile.profile;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.profile.profile.views.ProfileSummaryTab;


/**
 * Created by hashcode on 2015/09/15.
 */
public class ProfileMenu extends VerticalLayout {
    private final MainLayout main;
    private TabSheet tab;

    public ProfileMenu(MainLayout main, String selectedTab) {
        this.main = main;

        VerticalLayout summaryProfile = new VerticalLayout();
        summaryProfile.setMargin(true);
        summaryProfile.addComponent(new ProfileSummaryTab(main));



        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(summaryProfile, "Summary  PROFILE", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(summaryProfile);
        }
        addComponent(tab);
    }
}
