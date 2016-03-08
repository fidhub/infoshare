package infoshare.client.content.common.demographics;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.common.demographics.views.*;


/**
 * Created by hashcode on 2015/08/18.
 */
public class CommonDemographicsMenu extends VerticalLayout {
    private final MainLayout main;
    private TabSheet tab;

    public CommonDemographicsMenu(MainLayout main, String selectedTab) {
        this.main = main;
        setSizeFull();
        VerticalLayout genderListTab = new VerticalLayout();
        genderListTab.setMargin(true);
        genderListTab.addComponent(new GenderListTab(main));


        VerticalLayout languageProficiencyTab = new VerticalLayout();
        languageProficiencyTab.setMargin(true);
        languageProficiencyTab.addComponent(new LanguageProficiencyTab(main));

        VerticalLayout languageTab = new VerticalLayout();
        languageTab.setMargin(true);
        languageTab.addComponent(new LanguageTab(main));



        VerticalLayout raceListTab = new VerticalLayout();
        raceListTab.setMargin(true);
        raceListTab.addComponent(new RaceListTab(main));

        VerticalLayout rolesListTab = new VerticalLayout();
        rolesListTab.setMargin(true);
        rolesListTab.addComponent(new RolesListTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(rolesListTab, "Roles LIST ", null);
        tab.addTab(genderListTab, "Gender LIST", null);

        tab.addTab(raceListTab, "Race LIST", null);
        tab.addTab(languageTab, "Language SET", null);
        tab.addTab(languageProficiencyTab, "Language  PROFICIENCY", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(rolesListTab);
        } else if (selectedTab.equals("GENDER")) {
            tab.setSelectedTab(genderListTab);
        } else if (selectedTab.equals("RACE")) {
            tab.setSelectedTab(raceListTab);
        } else if (selectedTab.equals("PROFICIENCY")) {
            tab.setSelectedTab(languageProficiencyTab);
        } else if (selectedTab.equals("LANGUAGE")) {
            tab.setSelectedTab(languageTab);
        }

        addComponent(tab);
    }
}
