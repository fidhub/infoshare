package infoshare.client.content.setup.tables;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.PeopleFacade;
import infoshare.client.content.MainLayout;
import infoshare.domain.person.Person;
import infoshare.services.people.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hashcode on 2015/06/24.
 */
public class PersonTable extends Table {

    private final MainLayout main;
    @Autowired
    private PersonService personService = PeopleFacade.getPersonServiceInstance();

    public PersonTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("First name", String.class, null);
        addContainerProperty("Middle Name", String.class, null);
        addContainerProperty("Last name", String.class, null);
        addContainerProperty("Email PersonAddress", String.class, null);
        addContainerProperty("Enabled", Boolean.class, null);
        personService.getPersonByCompany("CPUT").forEach(this::loadTable);
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }
    public void loadTable(Person person) {
        try {
            addItem(new Object[]{
                    person.getFirstName(),
                    person.getMiddleName(),
                    person.getLastName(),
                    person.getEmailAddress(),
                    person.getEnabled()
            }, person.getId());
        } catch (Exception r) {
        }
    }


}