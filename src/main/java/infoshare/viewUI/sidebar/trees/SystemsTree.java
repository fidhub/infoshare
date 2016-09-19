package infoshare.viewUI.sidebar.trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import infoshare.viewUI.container.MainLayout;


/**
 * Created by hashcode on 2015/08/17.
 */
public class SystemsTree extends Tree implements ItemClickEvent.ItemClickListener {
    private final MainLayout main;
    private static final String LANDING_TAB = "LANDING";
    public static final Object SETUP_LOCATIONS = "Setup Locations";
    public static final Object SETUP_DEMOGRAPHICS = "Setup Demographics";
    public static final Object SETUP_UTILS = "Setup Utilities";
    public static final Object SETUP_OFFICE = "Setup Office";



    public SystemsTree(MainLayout main) {
        this.main = main;
        //add Items to Menu
        addItem(SETUP_LOCATIONS);
        addItem(SETUP_OFFICE);
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
            }  else if (SETUP_DEMOGRAPHICS.equals(itemId)) {
                demographicsView();
            } else if (SETUP_UTILS.equals(itemId)) {
                utilsView();
            }
        }
    }


    private void locationView() {
//        main.content.setSecondComponent(new LocationMenu(main, LANDING_TAB));
    }

    private void demographicsView() {
//        main.content.setSecondComponent(new CommonDemographicsMenu(main, LANDING_TAB));
    }


    private void utilsView() {
//        main.content.setSecondComponent(new UtilitiesMenu(main, LANDING_TAB));
    }


}
