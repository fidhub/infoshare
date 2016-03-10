package infoshare.client.sidebar.trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import infoshare.client.content.MainLayout;


/**
 * Created by hashcode on 2015/08/17.
 */
public class UsersTree extends Tree implements ItemClickEvent.ItemClickListener {
    private final MainLayout main;
    public static final Object MANAGE_USERS = "Manage System Users";
    private static final String LANDING_TAB = "LANDING";

    public UsersTree(MainLayout main) {
        this.main = main;
        //add Items to Menu
        addItem(MANAGE_USERS);
        

        //Add Listeners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (MANAGE_USERS.equals(itemId)) {
                manageUsersView();
            }
        }
    }

    private void manageUsersView() {
//        main.content.setSecondComponent(new UserManagementMenu(main, LANDING_TAB));

    }

}
