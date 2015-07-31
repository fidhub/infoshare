package infoshare.client.content.setup.tables;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.domain.Address;
import infoshare.services.Address.AddressService;
import infoshare.services.Address.Impl.AddressServiceImpl;

/**
 * Created by codex on 2015/07/30.
 */
public class AddressTable extends Table {
    private AddressService service = new AddressServiceImpl();

    public AddressTable() {
        setSizeFull();
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Postal Address", String.class, null);
        addContainerProperty("Physical Address", String.class,null);
        addContainerProperty("Postal Code", String.class,null);
        addContainerProperty("Address Type",String.class,null);

        for(Address address: service.findAll()){
            addItem(new Object[]{
                    address.getPhysicalAddress(),
                    address.getPostalAddress(),
                    address.getPostalCode(),
                    address.getAddressType(),
            },address.getId());
        }
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }
}
