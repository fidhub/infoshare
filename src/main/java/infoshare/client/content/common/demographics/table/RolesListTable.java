package infoshare.client.content.common.demographics.table;

import com.vaadin.ui.Table;
import hashwork.client.content.MainLayout;
import hashwork.domain.ui.demographics.Role;
import hashwork.services.ui.demographics.Impl.RolesListServiceImpl;
import hashwork.services.ui.demographics.RolesListService;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
public class RolesListTable extends Table {
    private final MainLayout main;
    private final RolesListService rolesListService = new RolesListServiceImpl();

    public RolesListTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Role Name", String.class, null);
        addContainerProperty("Description", String.class, null);

        // Add Data Columns
        Set<Role> roles = rolesListService.findAll();
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
