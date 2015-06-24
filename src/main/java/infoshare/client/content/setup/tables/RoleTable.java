package infoshare.client.content.setup.tables;

import com.vaadin.ui.Table;
import infoshare.client.content.MainLayout;
import infoshare.domain.Role;
import infoshare.services.roles.Impl.RoleServiceImpl;
import infoshare.services.roles.RoleService;

import java.util.List;

/**
 * Created by hashcode on 2015/06/24.
 */

public class RoleTable extends Table {

    RoleService roleService= new RoleServiceImpl();

    public RoleTable() {
    }

    private  MainLayout main;



    public RoleTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        addContainerProperty("Role Name", String.class, null);
        addContainerProperty("Description", String.class, null);
        System.out.println(" The ROLE FOR THE TABLE "+ roleService);
        List<Role> roles = roleService.findAll(); // FROM REST
        for (Role role : roles) {
            addItem(new Object[]{
                    role.getRolename(),
                    role.getDescription()
                    },
                    role.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);
//
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }


}
