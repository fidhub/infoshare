package infoshare.filterSearch;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.PeopleFacade;
import infoshare.domain.person.Person;
import infoshare.services.people.PersonService;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by user9 on 2016/02/11.
 */
public class UserFilter {
    private PersonService personService = PeopleFacade.personService;
    public TextField field = new TextField();
    public UserFilter() {
        getField();
    }
    public synchronized Set<Person> findAll(String stringFilter,String company,String role) {
        return  personService.getPersonByCompany(company).stream()
                .filter(cont->PeopleFacade.personRoleService.findPersonRoles(cont.getId()).contains(role))
                .collect(Collectors.toSet()).stream()
                .filter(user->user.getEmailAddress().contains(stringFilter))
                .collect(Collectors.toSet()).stream()
                .filter(user->user.getFirstName().contains(stringFilter))
                .collect(Collectors.toSet());

    }
    private TextField getField(){
        field.setInputPrompt("Use User email or FirstName ...");
        field.setWidth("260px");
        field.setIcon(FontAwesome.FILTER);
        field.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        field.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        return field;
    }
}
