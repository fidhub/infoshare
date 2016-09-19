package infoshare.viewUI.container.profile.contacts.table;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.LocationFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.viewUI.container.MainLayout;
import infoshare.domain.location.AddressType;
import infoshare.domain.person.PersonAddress;

import java.util.Date;
import java.util.Set;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonAddressTable extends Table {
    private final MainLayout main;

    public PersonAddressTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        final String personId = GetUserCredentials.getUser().getId();

        addContainerProperty("Date Created", Date.class, null);
        addContainerProperty("Address Type", String.class, null);
        addContainerProperty("Address", String.class, null);
        addContainerProperty("Postal Code", String.class, null);

        Set<PersonAddress> personAddresses = PeopleFacade.personAddressService.findAll(personId);

        personAddresses.parallelStream().forEach(item -> {
            addItem(new Object[]{
                    item.getDate(),
                    addressType(item.getAddressTypeId()),
                    item.getDescription(),
                    item.getPostalCode(),
            }, item.getId());

        });


        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }

    private String addressType(String addressTypeId) {
        AddressType addressType = LocationFacade.addressTypeService.findById(addressTypeId);
        if (addressType != null)
            return addressType.getName();
        return "Type Not Set";


    }

}
