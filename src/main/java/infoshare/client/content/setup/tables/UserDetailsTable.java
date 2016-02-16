package infoshare.client.content.setup.tables;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.MainLayout;
import infoshare.domain.Content;
import infoshare.domain.Role;
import infoshare.domain.User;
import infoshare.services.roles.Impl.RoleServiceImpl;
import infoshare.services.roles.RoleService;
import infoshare.services.users.Impl.UserServiceImpl;
import infoshare.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by user9 on 2016/02/11.
 */
public class UserDetailsTable extends Table{
    private final MainLayout main;
    @Autowired
    private UserService userService = new UserServiceImpl();

    public UserDetailsTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("UserName", String.class, null);
        addContainerProperty("First name", String.class, null);
        addContainerProperty("Last name", String.class, null);
        userService.findAll().forEach(this::loadTable);

        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }
    public void loadTable(User user) {
             try {
                 addItem(new Object[]{
                         user.getUsername(),
                         user.getFirstName(),
                         user.getLastName()
                 }, user.getId());
                } catch (Exception r) {
                }
            }


}
