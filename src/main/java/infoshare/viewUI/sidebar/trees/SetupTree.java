package infoshare.viewUI.sidebar.trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.setup.SetupMenu;
import infoshare.viewUI.container.common.systemValues.SystemValues;

/**
 * Created by hashcode on 2015/06/23.
 */
public class SetupTree extends Tree implements ItemClickEvent.ItemClickListener {
    private final MainLayout main;
    private static final Object USERS="Users";
    private static final Object SYSTEM_VALUES ="System Values";
    private static final String LANDING_TAB = "LANDING";


    public SetupTree(MainLayout main) {
        this.main = main;
        addItem(USERS);
        addItem(SYSTEM_VALUES);
        //Add Listerners
        addItemClickListener(this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        if (USERS.equals(event.getItemId())) {
            main.content.setSecondComponent(new SetupMenu(main, LANDING_TAB));
        }
        if (SYSTEM_VALUES.equals(event.getItemId())) {
            main.content.setSecondComponent(new SystemValues(main, LANDING_TAB));
        }
    }
}
