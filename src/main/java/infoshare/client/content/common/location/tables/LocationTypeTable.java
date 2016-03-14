package infoshare.client.content.common.location.tables;

import com.vaadin.ui.Table;
import infoshare.app.facade.LocationFacade;
import infoshare.client.content.MainLayout;
import infoshare.domain.location.LocationType;

import java.util.Set;

/**
 * Created by hashcode on 2015/09/07.
 */
public class LocationTypeTable extends Table {


    private final MainLayout main;

    public LocationTypeTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Name Type", String.class, null);
        addContainerProperty("Name Code", String.class, null);

        // Add Data Columns
        Set<LocationType> locationTypes = LocationFacade.getLocationTypeServiceInstance().findAll();
        for (LocationType locationType : locationTypes) {
            addItem(new Object[]{locationType.getName(), locationType.getCode()}, locationType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}