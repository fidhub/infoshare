package infoshare.client.content.profile.contacts;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import hashwork.client.content.MainLayout;
import hashwork.client.content.profile.contacts.views.PersonAddressTab;
import hashwork.client.content.profile.contacts.views.ContactsSummaryTab;
import hashwork.client.content.profile.contacts.views.PersonContactsTab;

/**
 * Created by hashcode on 2015/12/07.
 */
public class ContactsMenu extends VerticalLayout {

    private final MainLayout main;
    private TabSheet tab;

    public ContactsMenu(MainLayout main, String selectedTab) {
        this.main = main;

        VerticalLayout addressTab = new VerticalLayout();
        addressTab.setMargin(true);
        addressTab.addComponent(new PersonAddressTab(main));

        VerticalLayout contactsSummaryTab = new VerticalLayout();
        contactsSummaryTab.setMargin(true);
        contactsSummaryTab.addComponent(new ContactsSummaryTab(main));

        VerticalLayout contactsTab = new VerticalLayout();
        contactsTab.setMargin(true);
        contactsTab.addComponent(new PersonContactsTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(contactsSummaryTab, "Contacts SUMMARY", null);
        tab.addTab(addressTab, "Personal ADDRESS", null);
        tab.addTab(contactsTab, "Personal  CONTACTS", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(contactsSummaryTab);
        } else if (selectedTab.equals("ADDRESS")) {
            tab.setSelectedTab(addressTab);
        } else if (selectedTab.equals("CONTACTS")) {
            tab.setSelectedTab(contactsTab);
        }
        addComponent(tab);
    }
}
