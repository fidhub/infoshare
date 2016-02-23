package infoshare.client.content.setup.tables;

import com.vaadin.ui.Table;
import infoshare.domain.Address;
import infoshare.domain.User;
import infoshare.services.Contact.AddressService;
import infoshare.services.Contact.Impl.AddressServiceImpl;
import infoshare.services.users.Impl.UserServiceImpl;
import infoshare.services.users.UserService;

/**
 * Created by codex on 2015/07/30.
 */
public class AddressTable extends Table {

    private AddressService service = new AddressServiceImpl();
    private UserService userService = new UserServiceImpl();
    public static String userID;

    public AddressTable() {
        setWidth("100%");
        addContainerProperty("Postal Address", String.class, null);
        addContainerProperty("Physical Address", String.class, null);
        addContainerProperty("Postal Code", String.class, null);
        addContainerProperty("Address Type", String.class, null);
        loadTable();
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }

    public void loadTable() {
        User user = userService.find(userID);
        if (user != null) {
            for (int i = 0; i < user.getAddress().size(); i++) {
                Address address = service.find(user.getAddress().get(i));
                try {
                    this.addItem(new Object[]{
                            address.getPostalAddress(),
                            address.getPhysicalAddress(),
                            address.getPostalCode(),
                            address.getAddressType()
                    }, address.getId());
                } catch (Exception e) {
                }
            }
        }
    }
}
