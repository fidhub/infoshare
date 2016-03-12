package infoshare.client.content.organisation;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.organisation.view.OrganisationActiveView;
import infoshare.client.content.organisation.view.OrganisationDisabledView;


/**
 * Created by hashcode on 2015/12/28.
 */
public class OrganisationMenu extends VerticalLayout {
    private final MainLayout main;
    private TabSheet tab;

    public OrganisationMenu(MainLayout main, String selectedTab) {
        this.main = main;
        setSizeFull();

        VerticalLayout activeCompany = new VerticalLayout();
        activeCompany.setMargin(true);
        activeCompany.addComponent(new OrganisationActiveView(main));

        VerticalLayout disabledCompany = new VerticalLayout();
        disabledCompany.setMargin(true);
        disabledCompany.addComponent(new OrganisationDisabledView(main));


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(activeCompany, "Active Organisation ", null);
        tab.addTab(disabledCompany, "Retired Organisation ", null);
        tab.addTab(null, "Add Organisation Admin ", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(activeCompany);
        } else if (selectedTab.equals("Retired")) {
            tab.setSelectedTab(disabledCompany);
        }

        addComponent(tab);
    }
}
