package infoshare.client.sidebar.trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import infoshare.client.content.MainLayout;
import infoshare.client.content.organisation.places.OrganisationPlacesMenu;

/**
 * Created by hashcode on 2015/12/28.
 */
public class CompanyTree extends Tree implements ItemClickEvent.ItemClickListener {
    private final MainLayout main;
    private static final String LANDING_TAB = "LANDING";
    public static final Object MANAGE_PLACES = "Manage Places";



    public CompanyTree(MainLayout main) {
        this.main = main;
        //add Items to Menu
        addItem(MANAGE_PLACES);



        //Add Listeners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);


    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (MANAGE_PLACES.equals(itemId)) {
                placesView();
            }
        }
    }


    private void placesView() {
        main.content.setSecondComponent(new OrganisationPlacesMenu(main, LANDING_TAB));

    }



}
