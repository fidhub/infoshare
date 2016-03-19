package infoshare.client.content.common.location.tables;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
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
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Name Type", String.class, null);
        addContainerProperty("Name Code", String.class, null);

        // Add Data Columns
        Set<LocationType> locationTypes = LocationFacade.locationTypeService.findAll();
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