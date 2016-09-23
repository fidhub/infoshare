package infoshare.viewUI.container.common.util.tables;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.UtilFacade;
import infoshare.viewUI.container.MainLayout;
import infoshare.domain.util.Status;

import java.util.Set;

/**
 * Created by hashcode on 2015/10/12.
 */
public class StatusTable extends Table {


    private final MainLayout main;

    public StatusTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addContainerProperty("Status Type", String.class, null);
        addContainerProperty("Status Value", String.class, null);

        // Add Data Columns
        Set<Status> statuses = UtilFacade.statusService.findAll();
        for (Status status : statuses) {
            addItem(new Object[]{status.getName(), status.getValue()}, status.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);

    }
}
