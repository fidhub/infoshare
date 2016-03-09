package infoshare.client.content.setup.tables;

import com.vaadin.ui.Table;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.domain.person.Person;
import infoshare.services.Contact.AddressService;
import infoshare.services.Contact.Impl.AddressServiceImpl;

/**
 * Created by codex on 2015/07/30.
 */
public class AddressTable extends Table {

    private AddressService service = new AddressServiceImpl();
    public static String userID;

    public AddressTable() {
        setWidth("100%");
        addContainerProperty("Postal PersonAddress", String.class, null);
        addContainerProperty("Physical PersonAddress", String.class, null);
        addContainerProperty("Postal Code", String.class, null);
        addContainerProperty("PersonAddress Type", String.class, null);
        loadTable();
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }

    public void loadTable() {
        Person user = PeopleFacade.personService.getPersonById(OrganisationUtil.getCompanyCode(),userID);

    }
}
