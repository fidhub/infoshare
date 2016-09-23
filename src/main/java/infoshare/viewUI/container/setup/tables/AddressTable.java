package infoshare.viewUI.container.setup.tables;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.domain.person.Person;

/**
 * Created by codex on 2015/07/30.
 */
public class AddressTable extends Table {
    public static String userID;

    public AddressTable() {
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Postal Person Address", String.class, null);
        addContainerProperty("Physical Person Address", String.class, null);
        addContainerProperty("Postal Code", String.class, null);
        addContainerProperty("Person Address Type", String.class, null);
        loadTable();
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }

    public void loadTable() {
        Person user = PeopleFacade.personService.getPersonById(OrganisationUtil.getCompanyCode(),userID);
    }
}
