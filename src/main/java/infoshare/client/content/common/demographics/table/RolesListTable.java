package infoshare.client.content.common.demographics.table;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.DemographicsFacade;
import infoshare.app.util.DomainState;
import infoshare.client.content.MainLayout;
import infoshare.domain.demographics.Role;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/08/18.
 */
public class RolesListTable extends Table {
    private final MainLayout main;


    public RolesListTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Role Name", String.class, null);
        addContainerProperty("Description", String.class, null);

        // Add Data Columns
        Set<Role> roles = DemographicsFacade.rolesListService.findAll().stream()
                .filter(role->role.getState().equalsIgnoreCase(DomainState.ACTIVE.name()))
                .collect(Collectors.toSet());
        for (Role role : roles) {
            addItem(new Object[]{role.getName(), role.getDescription()}, role.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

}
