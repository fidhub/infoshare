package infoshare.client.content.profile.employment;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import hashwork.client.content.MainLayout;
import hashwork.client.content.profile.demographics.views.DemographicsSummaryTab;
import hashwork.client.content.profile.education.views.CurriculumVitaeTab;
import hashwork.client.content.profile.employment.views.EmploymentHistoryTab;

/**
 * Created by hashcode on 2015/12/07.
 */
public class EmploymentMenu extends VerticalLayout {

    private final MainLayout main;
    private TabSheet tab;

    public EmploymentMenu(MainLayout main, String selectedTab) {
        this.main = main;

        VerticalLayout curriculumTab = new VerticalLayout();
        curriculumTab.setMargin(true);
        curriculumTab.addComponent(new CurriculumVitaeTab(main));

        VerticalLayout employmentHistoryTab = new VerticalLayout();
        employmentHistoryTab.setMargin(true);
        employmentHistoryTab.addComponent(new EmploymentHistoryTab(main));

        VerticalLayout summaryTab = new VerticalLayout();
        summaryTab.setMargin(true);
        summaryTab.addComponent(new DemographicsSummaryTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(summaryTab, "Employment SUMMARY", null);
        tab.addTab(employmentHistoryTab, "Employment HISTORY", null);
        tab.addTab(curriculumTab, "Curriculum VITAE", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(summaryTab);
        } else if (selectedTab.equals("CV")) {
            tab.setSelectedTab(curriculumTab);
        } else if (selectedTab.equals("EMPLOYMENT")) {
            tab.setSelectedTab(employmentHistoryTab);
        }
        addComponent(tab);
    }
}
