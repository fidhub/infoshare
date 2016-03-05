package infoshare.client.content.profile.contacts.table;

import com.vaadin.ui.Table;
import hashwork.app.facade.LocationFacade;
import hashwork.app.facade.PeopleFacade;
import hashwork.app.facade.UtilFacade;
import hashwork.app.util.security.GetUserCredentials;
import hashwork.client.content.MainLayout;
import hashwork.domain.people.PersonContact;
import hashwork.domain.ui.location.ContactType;
import hashwork.domain.ui.util.Status;

import java.util.Date;
import java.util.Set;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonContactsTable extends Table {
    private final MainLayout main;

    public PersonContactsTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        final String personId = GetUserCredentials.getUser().getId();

        addContainerProperty("Date Created", Date.class, null);
        addContainerProperty("Contact Type", String.class, null);
        addContainerProperty("Contact", String.class, null);
        addContainerProperty("Status", String.class, null);

        Set<PersonContact> personContacts = PeopleFacade.personContactService.findAll(personId);

        personContacts.parallelStream().forEach(item -> {
            addItem(new Object[]{
                    item.getDate(),
                    contactType(item.getAddressTypeId()),
                    item.getContactValue(),
                    getStatus(item.getStatus()),
            }, item.getId());

        });
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }

    private String getStatus(String statusId) {
        Status status = UtilFacade.statusService.findById(statusId);
        if (status != null)
            return status.getValue();
        return "Type Not Set";

    }

    private String contactType(String addressTypeId) {
        ContactType contactType = LocationFacade.contactListService.findById(addressTypeId);
        if (contactType != null)
            return contactType.getName();
        return "Type Not Set";
    }

}
