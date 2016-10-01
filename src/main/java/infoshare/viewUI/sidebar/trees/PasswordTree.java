package infoshare.viewUI.sidebar.trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.password.PasswordMenu;

/**
 * Created by hashcode on 2015/06/23.
 */
public class PasswordTree extends Tree implements ItemClickListener {
    private final MainLayout main;
    private static final Object CHANGE_PASSWORD = "Change Password";
    private static final String LANDING_TAB = "LANDING";


    public PasswordTree(MainLayout main) {
        this.main = main;
        addItem(CHANGE_PASSWORD);
        //Add Listerners
        addItemClickListener(this);

    }

    @Override
    public void itemClick(ItemClickEvent event) {
        if (CHANGE_PASSWORD.equals(event.getItemId())) {
            main.content.setSecondComponent(new PasswordMenu(main, LANDING_TAB));
        }
    }
}
