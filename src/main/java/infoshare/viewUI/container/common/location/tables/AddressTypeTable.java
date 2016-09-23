package infoshare.viewUI.container.common.location.tables;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.LocationFacade;
import infoshare.viewUI.container.MainLayout;
import infoshare.domain.location.AddressType;

import java.util.Set;


/**
 * Created by hashcode on 2015/09/07.
 */
public class AddressTypeTable extends Table {


    private final MainLayout main;

    public AddressTypeTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Address Type", String.class, null);


        // Add Data Columns
        Set<AddressType> addressTypes = LocationFacade.addressTypeService.findAll();
        for (AddressType addressType : addressTypes) {
            addItem(new Object[]{addressType.getName()}, addressType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}