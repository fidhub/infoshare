package infoshare.client.content.setup.tables;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.domain.User;
import infoshare.services.users.Impl.UserServiceImpl;
import infoshare.services.users.UserService;

import java.util.List;

/**
 * Created by hashcode on 2015/06/24.
 */
public class UserTable extends Table {

    private final MainLayout main;
    private UserService userService = new UserServiceImpl();
    public UserTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Username", String.class, null);
        addContainerProperty("First Name", String.class, null);
        addContainerProperty("Last Name", String.class, null);
        addContainerProperty("Other Name", String.class, null);
        addContainerProperty("Enabled", Boolean.class, null);

        for (User user : userService.findAll()) {
            addItem(new Object[]{
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getOtherName(),
                    user.isEnable()
                    }, user.getId());
        }

        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }

}