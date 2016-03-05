package infoshare.client.content.profile.profile.views;

import com.vaadin.data.Property;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import hashwork.app.facade.DemographicsFacade;
import hashwork.app.facade.PeopleFacade;
import hashwork.app.util.security.GetUserCredentials;
import hashwork.client.content.MainLayout;
import hashwork.domain.people.Person;
import hashwork.domain.ui.demographics.Role;
import hashwork.domain.ui.demographics.Title;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/12/07.
 */
public class ProfileSummaryTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;

    public ProfileSummaryTab(MainLayout main) {
        final Person person = GetUserCredentials.getUser();
        Set<Role> userRoles = PeopleFacade.personService.getRoles(person.getId());
        final Title titlevalue = DemographicsFacade.titleListService.findById(person.getTitle());

        this.main = main;
        setSizeFull();

        final GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        final Button header = new Button(person.getFirstName() + " " + person.getLastName());
        header.setStyleName(ValoTheme.BUTTON_HUGE);
        header.setSizeFull();

        final Button account = new Button("Account State");

        account.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        account.setSizeFull();

        Label title = new Label("Title", ContentMode.HTML);
        Label titleV = new Label(getTitle(titlevalue), ContentMode.HTML);

        Label lastName = new Label("Last Name", ContentMode.HTML);
        Label lastNameV = new Label(person.getLastName(), ContentMode.HTML);

        Label firstName = new Label("First Name", ContentMode.HTML);
        Label firstNameV = new Label(person.getFirstName(), ContentMode.HTML);

        Label company = new Label("Company", ContentMode.HTML);
        Label companyV = new Label(person.getCompany(), ContentMode.HTML);

        Label emailAddress = new Label("Email Address", ContentMode.HTML);
        Label emailAddressV = new Label(person.getEmailAddress(), ContentMode.HTML);


        Label roles = new Label("Roles", ContentMode.HTML);
        Label rolesV = new Label(getRoles(userRoles), ContentMode.HTML);


        Label middleName = new Label("Other Names", ContentMode.HTML);
        Label middleNameV = new Label(person.getMiddleName(), ContentMode.HTML);


        Label enabled = new Label("Is Account Active", ContentMode.HTML);
        Label enabledV = new Label(person.getEnabled().toString(), ContentMode.HTML);

        Label accountExpired = new Label("Has Account Expired", ContentMode.HTML);
        Label accountExpiredV = new Label(person.getAccountNonExpired().toString(), ContentMode.HTML);

        Label credentialsNonExpired = new Label("Has Password Expired", ContentMode.HTML);
        Label credentialsNonExpiredV = new Label(person.getCredentialsNonExpired().toString(), ContentMode.HTML);

        Label accountNonLocked = new Label("Is Account Locked", ContentMode.HTML);
        Label accountNonLockedV = new Label(person.getAccountNonLocked().toString(), ContentMode.HTML);

        Label divider = new Label("<hr/>", ContentMode.HTML);

        grid.addComponent(header, 0, 0, 3, 0);
        // First Row
        grid.addComponent(title, 0, 1);
        grid.addComponent(titleV, 1, 1);
        grid.addComponent(lastName, 2, 1);
        grid.addComponent(lastNameV, 3, 1);


        //Second Row
        grid.addComponent(firstName, 0, 2);
        grid.addComponent(firstNameV, 1, 2);
        grid.addComponent(middleName, 2, 2);
        grid.addComponent(middleNameV, 3, 2);

        //Third Row
        grid.addComponent(company, 0, 3);
        grid.addComponent(companyV, 1, 3);
        grid.addComponent(emailAddress, 2, 3);
        grid.addComponent(emailAddressV, 3, 3);

        // Fourth Row
        grid.addComponent(roles, 0, 4);
        grid.addComponent(rolesV, 1, 4, 3, 4);

        //Fifth Row
        grid.addComponent(divider, 0, 5, 3, 5);
        grid.addComponent(account, 0, 6, 3, 6);

        //Fifth Row
        grid.addComponent(enabled, 0, 7);
        grid.addComponent(enabledV, 1, 7);
        grid.addComponent(accountExpired, 2, 7);
        grid.addComponent(accountExpiredV, 3, 7);

        //Six Row
        grid.addComponent(credentialsNonExpired, 0, 8);
        grid.addComponent(credentialsNonExpiredV, 1, 8);
        grid.addComponent(accountNonLocked, 2, 8);
        grid.addComponent(accountNonLockedV, 3, 8);


        addComponent(grid);
    }

    private String getTitle(Title titlevalue) {
        if (titlevalue != null)
            return titlevalue.getName();
        return null;
    }

    private String getRoles(Set<Role> roles) {
        return roles
                .stream()
                .map(value -> value.getDescription())
                .collect(Collectors.joining(",</br>"));
    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
