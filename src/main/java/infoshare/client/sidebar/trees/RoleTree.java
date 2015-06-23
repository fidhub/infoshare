package infoshare.client.sidebar.trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import infoshare.client.content.MainLayout;

/**
 * Created by hashcode on 2015/06/23.
 */
public class RoleTree extends Tree implements ItemClickEvent.ItemClickListener {
    private final MainLayout main;
    private static final Object CHANGE_PASSWORD="Change PASSWORD";
    private static final String LANDING_TAB = "LANDING";

    public RoleTree(MainLayout main) {
        this.main = main;
    }

    @Override
    public void itemClick(ItemClickEvent itemClickEvent) {

    }
}
