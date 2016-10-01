package infoshare.viewUI.sidebar.trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.common.demographics.CommonDemographicsMenu;
import infoshare.viewUI.container.common.location.CommonLocationMenu;
import infoshare.viewUI.container.common.util.CommonUtilMenu;
import infoshare.viewUI.container.common.systemValues.SystemValues;


/**
 * Created by hashcode on 2015/12/12.
 */
public class CommonTree extends Tree implements ItemClickEvent.ItemClickListener {
    private final MainLayout main;
    private static final String LANDING_TAB = "LANDING";
    public static final Object SETUP_LOCATIONS = "Setup Locations";
    public static final Object SETUP_DEMOGRAPHICS = "Setup Demographics";
    public static final Object SETUP_UTILS = "Setup Utilities";
    private static final Object SYSTEM_VALUES ="System Values";



    public CommonTree(MainLayout main) {
        this.main = main;
        //add Items to Menu

        addItem(SETUP_LOCATIONS);
        addItem(SETUP_DEMOGRAPHICS);
        addItem(SETUP_UTILS);
        addItem(SYSTEM_VALUES);


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
            }else if (SYSTEM_VALUES.equals(itemId)) {
                utilsValues();
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
    private void utilsValues() {
        main.content.setSecondComponent(new SystemValues(main, LANDING_TAB));
    }

}
