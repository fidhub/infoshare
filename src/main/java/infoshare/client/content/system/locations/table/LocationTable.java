package infoshare.client.content.system.locations.table;

import com.vaadin.ui.Table;
import infoshare.app.facade.LocationFacade;
import infoshare.client.content.MainLayout;
import infoshare.domain.organisation.Location;

import java.util.Set;

/**
 * Created by hashcode on 2015/09/07.
 */
public class LocationTable extends Table {
    
    private final MainLayout main;

    public LocationTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Name", String.class, null);
        addContainerProperty("Code", String.class, null);
        addContainerProperty("Latitude", String.class, null);
        addContainerProperty("Longitude", String.class, null);
        addContainerProperty("Type", String.class, null);
        addContainerProperty("Parent", String.class, null);
        addContainerProperty("Children", Long.class, null);
        // Add Data Columns
        Set<Location> locations = LocationFacade.locationService.findAll();
        for (Location location : locations) {
            addItem(new Object[]{location.getName(),
                    location.getCode(),
                    location.getLatitude(),
                    location.getLongitude(),
                    getLocationType(location.getLocationTypeId()),
                    getParentName(location.getParentId()),
                    LocationFacade.locationService.numberofChildred(location.getId())}, location.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

    private String getParentName(String parentId) {
        if (parentId != null) {
            return LocationFacade.locationService.findById(parentId).getName();
        }
        return null;
    }

    private String getLocationType(String locationTypeId) {
        if (locationTypeId != null) {
            return LocationFacade.locationTypeService.findById(locationTypeId).getName();
        }
        return null;
    }
}