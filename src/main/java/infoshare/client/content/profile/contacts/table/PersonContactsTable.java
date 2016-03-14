package infoshare.client.content.profile.contacts.table;

import com.vaadin.ui.Table;
import infoshare.app.facade.LocationFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.facade.UtilFacade;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.client.content.MainLayout;
import infoshare.domain.location.ContactType;
import infoshare.domain.person.PersonContact;
import infoshare.domain.util.Status;

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

        Set<PersonContact> personContacts = PeopleFacade.getPersonContactServiceInstance().findAll(personId);

        personContacts.parallelStream().forEach(item -> addItem(new Object[]{
                item.getDate(),
                contactType(item.getAddressTypeId()),
                item.getContactValue(),
                getStatus(item.getStatus()),
        }, item.getId()));
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }

    private String getStatus(String statusId) {
        Status status = UtilFacade.getStatusServiceInstance().findById(statusId);
        if (status != null)
            return status.getValue();
        return "Type Not Set";

    }

    private String contactType(String addressTypeId) {
        ContactType contactType = LocationFacade.getContactTypeServiceInstance().findById(addressTypeId);
        if (contactType != null)
            return contactType.getName();
        return "Type Not Set";
    }

}
