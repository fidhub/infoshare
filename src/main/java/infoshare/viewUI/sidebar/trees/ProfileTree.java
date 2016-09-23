package infoshare.viewUI.sidebar.trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.profile.contacts.ContactsMenu;
import infoshare.viewUI.container.profile.demographics.DemographicsMenu;
import infoshare.viewUI.container.profile.password.PasswordMenu;
import infoshare.viewUI.container.profile.profile.ProfileMenu;


/**
 * Created by hashcode on 2015/08/17.
 */
public class ProfileTree extends Tree implements ItemClickEvent.ItemClickListener {
    private final MainLayout main;
    public static final Object PROFILE = "Profile Summary";
    public static final Object CONTACTS = "Your Contacts";
    public static final Object DEMOGRAOHICS = "Your Demographics";
    public static final Object CHANGE_PASSSWORD = "Change Password";
    private static final String LANDING_TAB = "LANDING";

    public ProfileTree(MainLayout main) {
        this.main = main;
        addItem(PROFILE);
        addItem(DEMOGRAOHICS);
        addItem(CONTACTS);
        addItem(CHANGE_PASSSWORD);

        //Add Listeners
        addItemClickListener(this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (PROFILE.equals(itemId)) {
                profileView();
            } else if (DEMOGRAOHICS.equals(itemId)) {
                demographicsView();

            } else if (CONTACTS.equals(itemId)) {
                contactsView();

            } else if (CHANGE_PASSSWORD.equals(itemId)) {
                passwordView();

            }
        }
    }



    private void contactsView() {
        main.content.setSecondComponent(new ContactsMenu(main, LANDING_TAB));

    }

    private void demographicsView() {
        main.content.setSecondComponent(new DemographicsMenu(main, LANDING_TAB));

    }

    private void profileView() {
        main.content.setSecondComponent(new ProfileMenu(main, LANDING_TAB));
    }

    private void passwordView() {
        main.content.setSecondComponent(new PasswordMenu(main, LANDING_TAB));

    }
}
