package infoshare.client.content.profile.education;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import hashwork.client.content.MainLayout;
import hashwork.client.content.profile.education.views.ContinuingEducationTab;
import hashwork.client.content.profile.education.views.EducationHistoryTab;
import hashwork.client.content.profile.education.views.EducationSummaryTab;
import hashwork.client.content.profile.employment.views.CurriculumVitaeTab;

/**
 * Created by hashcode on 2015/12/07.
 */
public class EducationMenu extends VerticalLayout {

    private final MainLayout main;
    private TabSheet tab;

    public EducationMenu(MainLayout main, String selectedTab) {
        this.main = main;

        VerticalLayout contuingEducationTab = new VerticalLayout();
        contuingEducationTab.setMargin(true);
        contuingEducationTab.addComponent(new ContinuingEducationTab(main));

        VerticalLayout educationSummaryTab = new VerticalLayout();
        educationSummaryTab.setMargin(true);
        educationSummaryTab.addComponent(new EducationSummaryTab(main));

        VerticalLayout educationHistoryTab = new VerticalLayout();
        educationHistoryTab.setMargin(true);
        educationHistoryTab.addComponent(new EducationHistoryTab(main));

        VerticalLayout curriculunVitaeTab = new VerticalLayout();
        curriculunVitaeTab.setMargin(true);
        curriculunVitaeTab.addComponent(new CurriculumVitaeTab(main));


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(educationSummaryTab, "Education SUMMARY", null);
        tab.addTab(contuingEducationTab, "Continuing EDUCATION", null);
        tab.addTab(educationHistoryTab, "Education HISTORY", null);
        tab.addTab(curriculunVitaeTab, "Curriculum VITAE", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(educationSummaryTab);
        } else if (selectedTab.equals("CONTINUING")) {
            tab.setSelectedTab(contuingEducationTab);

        } else if (selectedTab.equals("HISTORY")) {
            tab.setSelectedTab(educationHistoryTab);

        } else if (selectedTab.equals("CV")) {
            tab.setSelectedTab(curriculunVitaeTab);

        }
        addComponent(tab);
    }
}
