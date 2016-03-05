package infoshare.client.content.profile.contacts.table;

import com.vaadin.ui.Table;
import hashwork.app.facade.LocationFacade;
import hashwork.app.facade.PeopleFacade;
import hashwork.app.util.security.GetUserCredentials;
import hashwork.client.content.MainLayout;
import hashwork.domain.people.PersonAddress;
import hashwork.domain.ui.location.AddressType;

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
