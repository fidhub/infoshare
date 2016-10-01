package infoshare.viewUI.sidebar.trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.account.AccountMenu;

/**
 * Created by hashcode on 2015/12/28.
 */
public class OrganisationTree extends Tree implements ItemClickEvent.ItemClickListener {
    private final MainLayout main;
    private static final String LANDING_TAB = "LANDING";
    public static final Object MANAGE_ORGANIZATION = "Manage Organisation";



    public OrganisationTree(MainLayout main) {
        this.main = main;
        //add Items to Menu
        addItem(MANAGE_ORGANIZATION);
        //Add Listeners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);


    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (MANAGE_ORGANIZATION.equals(itemId)) {
                organisationView();
            }
        }
    }


    private void organisationView() {
        main.content.setSecondComponent(new AccountMenu(main, LANDING_TAB));

    }



}
