package infoshare.viewUI.sidebar.trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import infoshare.app.util.security.RolesValues;
import infoshare.app.util.security.SecurityService;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.account.AccountMenu;
import infoshare.viewUI.container.home.HomeMenu;


/**
 * Created by hashcode on 2015/08/17.
 */
public class HomeTree extends Tree implements ItemClickEvent.ItemClickListener {
    private final MainLayout main;
    private static final String MANAGE_COMPANIES = "Manage Companies";
    private static final String HOME_PAGE = "Home Page";
    private static final String LANDING_TAB = "LANDING";


    public HomeTree(MainLayout main) {
        this.main = main;
        addItem(HOME_PAGE);
        if (SecurityService.securityCheck(RolesValues.ROLE_ADMIN.name())) {
            addItem(MANAGE_COMPANIES);
        }


        //Add Listeners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (HOME_PAGE.equals(itemId)) {
                homePageView();
            } else if (MANAGE_COMPANIES.equals(itemId)) {
                companiesView();
            }
        }
    }

    private void homePageView() {
        main.content.setSecondComponent(new HomeMenu(main, LANDING_TAB));
    }

    private void companiesView() {
        main.content.setSecondComponent(new AccountMenu(main, LANDING_TAB));
    }

    private void messageView() {
//        main.content.setSecondComponent(new UserManagementMenu(main, LANDING_TAB));
    }
}
