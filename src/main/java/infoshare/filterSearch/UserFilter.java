package infoshare.filterSearch;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.PeopleFacade;
import infoshare.domain.person.Person;
import infoshare.services.people.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by user9 on 2016/02/11.
 */
public class UserFilter {
    private PersonService personService = PeopleFacade.getPersonServiceInstance();
    public TextField field = new TextField();
    public UserFilter() {
        getField();
    }
    public synchronized List<Person> findAll(String stringFilter,String company) {
        ArrayList arrayList = new ArrayList();
        for (Person person : personService.getPersonByCompany(company)) {

            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || person.getFirstName().toString().toLowerCase().trim()
                        .contains(stringFilter.toLowerCase())
                        || person.getMiddleName().toString().toLowerCase().trim()
                        .contains(stringFilter.toLowerCase())
                        || person.getLastName().toString().toLowerCase().trim()
                        .contains(stringFilter.toLowerCase())
                        || person.getEmailAddress().toString().toLowerCase().trim()
                        .contains(stringFilter.toLowerCase())
                        || person.getEnabled().toString().toLowerCase().trim()
                        .contains(stringFilter.toLowerCase());

                if (passesFilter) {
                    arrayList.add(person);
                }
            } catch (Exception ex) {
                Logger.getLogger(ex.getLocalizedMessage());
            }
        }

        return arrayList;
    }
    private TextField getField(){
        field.setInputPrompt("Filter Users ...");
        field.setWidth("260px");
        field.setIcon(FontAwesome.FILTER);
        field.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        field.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        return field;
    }
}
