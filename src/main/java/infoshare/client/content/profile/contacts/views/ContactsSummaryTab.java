package infoshare.client.content.profile.contacts.views;

import com.vaadin.data.Property;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.client.content.MainLayout;
import infoshare.domain.person.Person;
import infoshare.domain.person.PersonAddress;
import infoshare.domain.person.PersonContact;

import java.util.Set;
import java.util.stream.Collectors;


/**
 * Created by hashcode on 2015/12/07.
 */
public class ContactsSummaryTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;

    public ContactsSummaryTab(MainLayout main) {
        this.main = main;
        Person person = GetUserCredentials.getUser();
        Set<PersonAddress> personAddresses = PeopleFacade.personAddressService.findAll(person.getId());
        Set<PersonContact> personContacts = PeopleFacade.personContactService.findAll(person.getId());
        Button header = new Button("Contacts Summary for "+ person.getFirstName()+" "+person.getLastName());
        header.setSizeFull();
        header.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        header.addStyleName(ValoTheme.BUTTON_LARGE);

        GridLayout layout = new GridLayout(4,10);
        layout.setSizeFull();
        Label Address = new Label("Address: ", ContentMode.HTML);
        Label AddressV = new Label(getAddresses(personAddresses), ContentMode.HTML);
        Label Contact = new Label("Contacts: ", ContentMode.HTML);
        Label ContactV = new Label(getContacts(personContacts), ContentMode.HTML);
        layout.addComponent(Address,0,0);
        layout.addComponent(AddressV,1,0);
        layout.addComponent(Contact,2,0);
        layout.addComponent(ContactV,3,0);
        layout.setSpacing(true);

        addComponent(header);
        addComponent(layout);
    }
    private String getAddresses(Set<PersonAddress> personAddresses) {
        return personAddresses
                .stream()
                .map(value -> value.getDescription()+"</br>"+value.getPostalCode())
                .collect(Collectors.joining(",</br>"));
    }
    private String getContacts(Set<PersonContact> personContacts) {
        return personContacts
                .stream()
                .map(value -> value.getContactValue())
                .collect(Collectors.joining(",</br>"));
    }
    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}