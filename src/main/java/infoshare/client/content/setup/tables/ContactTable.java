package infoshare.client.content.setup.tables;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.domain.Contact;
import infoshare.domain.User;
import infoshare.services.Contact.ContactService;
import infoshare.services.Contact.Impl.ContactServiceImpl;
import infoshare.services.users.Impl.UserServiceImpl;
import infoshare.services.users.UserService;

/**
 * Created by user9 on 2015/07/30.
 */
public class ContactTable extends Table {

    private final MainLayout main;
    private ContactService contactService = new ContactServiceImpl();
    private UserService userService = new UserServiceImpl();

    public ContactTable(MainLayout main) {
        this.main = main;
        setWidth("100%");
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Phone number", String.class, null);
        addContainerProperty("Email Address",String.class,null);
        addContainerProperty("contact Type",String.class,null);
        loadTable();

        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }

    public void loadTable() {
        User user = userService.find(AddressTable.userID);
        if (user != null) {
            for (int i = 0; i < user.getContact().size(); i++) {
                Contact contact = contactService.find(user.getContact().get(i));
                try {
                    this.addItem(new Object[]{
                            contact.getPhone(),
                            contact.getEmail(),
                            contact.getContactType()

                    }, contact.getId());
                } catch (Exception e) {
                }
            }
        }
    }

}
