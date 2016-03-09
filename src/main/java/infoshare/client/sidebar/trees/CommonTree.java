package infoshare.client.sidebar.trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import infoshare.client.content.MainLayout;
import infoshare.client.content.common.demographics.CommonDemographicsMenu;
import infoshare.client.content.common.location.CommonLocationMenu;
import infoshare.client.content.common.util.CommonUtilMenu;


/**
 * Created by hashcode on 2015/12/12.
 */
public class CommonTree extends Tree implements ItemClickEvent.ItemClickListener {
    private final MainLayout main;
    private static final String LANDING_TAB = "LANDING";
    public static final Object SETUP_LOCATIONS = "Setup LOCATIONS";
    public static final Object SETUP_DEMOGRAPHICS = "Setup DEMOGRAPHICS";
    public static final Object SETUP_UTILS = "Setup UTILITIES";


    public CommonTree(MainLayout main) {
        this.main = main;
        //add Items to Menu
        addItem(SETUP_LOCATIONS);
        addItem(SETUP_DEMOGRAPHICS);
        addItem(SETUP_UTILS);


        //Add Listeners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);


    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (SETUP_LOCATIONS.equals(itemId)) {
                locationView();
            } else if (SETUP_DEMOGRAPHICS.equals(itemId)) {
                demographicsView();
            }else if (SETUP_UTILS.equals(itemId)) {
                utilsView();
            }
        }
    }


    private void locationView() {
        main.content.setSecondComponent(new CommonLocationMenu(main, LANDING_TAB));

    }

    private void demographicsView() {
        main.content.setSecondComponent(new CommonDemographicsMenu(main, LANDING_TAB));
    }


    private void utilsView() {
        main.content.setSecondComponent(new CommonUtilMenu(main, LANDING_TAB));
    }

}
