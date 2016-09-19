package infoshare.viewUI.container.profile.demographics.views;

import com.vaadin.data.Property;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.DemographicsFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.viewUI.container.MainLayout;
import infoshare.domain.person.Person;
import infoshare.domain.person.PersonDemographics;
import infoshare.domain.person.PersonLanguage;

import java.util.Set;
import java.util.stream.Collectors;


/**
 * Created by hashcode on 2015/12/07.
 */
public class DemographicsSummaryTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;

    public DemographicsSummaryTab(MainLayout main) {
        this.main = main;
        Person person = GetUserCredentials.getUser();
        Set<PersonDemographics> personDemographicses = PeopleFacade.personDemographicsService.find_ALL(person.getId());
        Set<PersonLanguage> personLanguages = PeopleFacade.personLanguageService.getAllLanguages(person.getId());
        Button header = new Button("Demographic Summary for " + person.getFirstName() + " " + person.getLastName());
        header.setSizeFull();
        header.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        header.addStyleName(ValoTheme.BUTTON_LARGE);

        GridLayout layout = new GridLayout(4, 10);
        layout.setSizeFull();
        layout.setSpacing(true);
        try {
            Label dateOfBirth = new Label("Date Of Birth: ", ContentMode.HTML);
            Label dateOfBirthV = new Label(personDemographicses.iterator().next().getDateOfBirth().toString(), ContentMode.HTML);
            Label MaritalStatus = new Label("Marital Status: ", ContentMode.HTML);
            Label MaritalStatusV = new Label(personDemographicses.iterator().next().getMaritalStatusId(), ContentMode.HTML);
            Label gender = new Label("Gender: ", ContentMode.HTML);
            Label genderV = new Label(DemographicsFacade.genderListService.findById(personDemographicses.iterator().next().getGenderId()).getName(), ContentMode.HTML);
            Label Language = new Label("Languages: ", ContentMode.HTML);
            Label LanguageV = new Label(getLanguage(personLanguages), ContentMode.HTML);
            //Row one
            layout.addComponent(MaritalStatus, 0, 0);
            layout.addComponent(MaritalStatusV, 1, 0);
            layout.addComponent(dateOfBirth, 2, 0);
            layout.addComponent(dateOfBirthV, 3, 0);

            //Row two
            layout.addComponent(gender, 0, 1);
            layout.addComponent(genderV, 1, 1);
            layout.addComponent(Language, 2, 1);
            layout.addComponent(LanguageV, 3, 1);
        } catch (Exception e) {
        }
        addComponent(header);
        addComponent(layout);

    }

    private String getLanguage(Set<PersonLanguage> personLanguage) {
        return personLanguage
                .stream()
                .map(value -> DemographicsFacade.languageService.findById(value.getLanguageId()).getName())
                .collect(Collectors.joining(",</br>"));
    }
    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}