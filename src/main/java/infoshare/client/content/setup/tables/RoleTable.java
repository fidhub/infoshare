package infoshare.client.content.setup.tables;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.RoleFacade;
import infoshare.client.content.MainLayout;
import infoshare.domain.Role;
import infoshare.services.roles.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hashcode on 2015/06/24.
 */
public class RoleTable extends Table {

    private final MainLayout main;
    @Autowired
    private RoleService roleService = RoleFacade.roleService;

    public RoleTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Role Name", String.class, null);
        addContainerProperty("category", String.class, null);
        addContainerProperty("State", String.class, null);
        for (Role role : roleService.findAll()) {
            addItem(new Object[]{role.getName(),
                    role.getDescription(),
                    role.getState()
            }, role.getId());
        }
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }
}
