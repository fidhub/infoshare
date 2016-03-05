package infoshare.client.content.common.demographics;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import hashwork.client.content.MainLayout;
import hashwork.client.content.common.demographics.views.*;

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

        VerticalLayout identificationTypeTab = new VerticalLayout();
        identificationTypeTab.setMargin(true);
        identificationTypeTab.addComponent(new IdentificationTypeTab(main));

        VerticalLayout languageProficiencyTab = new VerticalLayout();
        languageProficiencyTab.setMargin(true);
        languageProficiencyTab.addComponent(new LanguageProficiencyTab(main));

        VerticalLayout languageTab = new VerticalLayout();
        languageTab.setMargin(true);
        languageTab.addComponent(new LanguageTab(main));

        VerticalLayout maritalStatusListTab = new VerticalLayout();
        maritalStatusListTab.setMargin(true);
        maritalStatusListTab.addComponent(new MaritalStatusListTab(main));

        VerticalLayout raceListTab = new VerticalLayout();
        raceListTab.setMargin(true);
        raceListTab.addComponent(new RaceListTab(main));

        VerticalLayout rolesListTab = new VerticalLayout();
        rolesListTab.setMargin(true);
        rolesListTab.addComponent(new RolesListTab(main));

        VerticalLayout tittleListTab = new VerticalLayout();
        tittleListTab.setMargin(true);
        tittleListTab.addComponent(new TitleListTab(main));


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(rolesListTab, "Roles LIST ", null);
        tab.addTab(genderListTab, "Gender LIST", null);
        tab.addTab(identificationTypeTab, "Identification TYPE", null);
        tab.addTab(raceListTab, "Race LIST", null);
        tab.addTab(languageTab, "Language SET", null);
        tab.addTab(languageProficiencyTab, "Language  PROFICIENCY", null);
        tab.addTab(maritalStatusListTab, "Marital  STATUS", null);
        tab.addTab(tittleListTab, "Title  LIST", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(rolesListTab);
        } else if (selectedTab.equals("GENDER")) {
            tab.setSelectedTab(genderListTab);
        } else if (selectedTab.equals("ID")) {
            tab.setSelectedTab(identificationTypeTab);
        } else if (selectedTab.equals("RACE")) {
            tab.setSelectedTab(raceListTab);
        } else if (selectedTab.equals("TITLE")) {
            tab.setSelectedTab(tittleListTab);
        } else if (selectedTab.equals("PROFICIENCY")) {
            tab.setSelectedTab(languageProficiencyTab);
        } else if (selectedTab.equals("LANGUAGE")) {
            tab.setSelectedTab(languageTab);
        } else if (selectedTab.equals("MARITAL")) {
            tab.setSelectedTab(maritalStatusListTab);
        }

        addComponent(tab);
    }
}
