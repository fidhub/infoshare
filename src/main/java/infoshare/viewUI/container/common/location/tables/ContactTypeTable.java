package infoshare.viewUI.container.common.location.tables;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.LocationFacade;
import infoshare.viewUI.container.MainLayout;
import infoshare.domain.location.ContactType;

import java.util.Set;

/**
 * Created by hashcode on 2015/09/07.
 */
public class ContactTypeTable extends Table {

    private final MainLayout main;

    public ContactTypeTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Contact List Name", String.class, null);


        // Add Data Columns
        Set<ContactType> locationTypes = LocationFacade.contactListService.findAll();
        for (ContactType locationType : locationTypes) {
            addItem(new Object[]{locationType.getName()}, locationType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}