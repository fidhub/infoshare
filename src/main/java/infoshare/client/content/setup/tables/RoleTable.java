package infoshare.client.content.setup.tables;

import com.vaadin.ui.Table;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.models.RoleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hashcode on 2015/06/24.
 */
public class RoleTable extends Table {

    private final MainLayout main;

    public RoleTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        addContainerProperty("Role Name", String.class, null);
        addContainerProperty("Description", String.class, null);
        List<RoleModel> roles = new ArrayList<>();
        for (RoleModel role : roles) {
            addItem(new Object[]{role.getRolename(),
                    role.getDscription()
            }, role.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);
//
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }
}
