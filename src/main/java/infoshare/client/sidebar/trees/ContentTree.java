package infoshare.client.sidebar.trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.ContentMenu;

/**
 * Created by hashcode on 2015/06/23.
 */
public class ContentTree extends Tree implements ItemClickEvent.ItemClickListener {
    private final MainLayout main;
    private static final Object CONTENT = "Content Files";
    private static final String LANDING_TAB = "LANDING";

    public ContentTree(MainLayout main) {
        this.main = main;
        addItem(CONTENT);
        //Add Listerners
        addItemClickListener(this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        if (CONTENT.equals(event.getItemId())) {
            main.content.setSecondComponent(new ContentMenu(main, LANDING_TAB));
        }

    }
}
