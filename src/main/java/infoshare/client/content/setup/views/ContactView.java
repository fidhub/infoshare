package infoshare.client.content.setup.views;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.SetupMenu;
import infoshare.client.content.setup.forms.ContactForm;
import infoshare.client.content.setup.models.ContactModel;
import infoshare.client.content.setup.tables.ContactTable;
import infoshare.domain.Contact;
import infoshare.services.Contact.ContactService;
import infoshare.services.Contact.Impl.ContactServiceImpl;

/**
 * Created by Songezo on 2015-08-18.
 */
public class ContactView extends Window implements Button.ClickListener, Property.ValueChangeListener {

    private final ContactForm contactForm;
    private final ContactTable contactTable;
    private ContactService contactService = new ContactServiceImpl();
    private MainLayout main;
    public ContactView(MainLayout main) {
        this.main = main;
        contactForm = new ContactForm();
        contactTable = new ContactTable(main);
        addListeners();
        setEditContactFormProperties();
        setWidth(50.0f, Unit.PERCENTAGE);
        setClosable(false);
        setResizable(false);
        setDraggable(false);
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(contactForm);
        layout.addComponent(contactTable);
        contactForm.update.setVisible(false);
        setEditContactFormProperties();
        setContent(layout);

    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        final Button source = clickEvent.getButton();
        if (source == contactForm.cancel){
            getHome();
        }else if (source == contactForm.edit){
            setUpdatedProperties();
        }
        else if (source == contactForm.exit){

        }
    }

    private void getHome() {
        main.content.setSecondComponent(new SetupMenu(main, "LANDING"));
    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

        final Property property = valueChangeEvent.getProperty();
        if (property == contactTable){
            try {
                final Contact contact = contactService.find(contactTable.getValue().toString());
                final ContactModel bean = getContactModel(contact);
                contactForm.binder.setItemDataSource(new BeanItem<>(bean));
                setReadContactFormProperties();
               // setUpdatedProperties();
            }catch (Exception e){}
        }
    }

    private void addListeners() {

        contactForm.save.addClickListener(this);
        contactForm.edit.addClickListener(this);
        contactForm.cancel.addClickListener(this);
        //contactForm.update.addClickListener(this);
        contactForm.exit.addClickListener(this);
        contactTable.addValueChangeListener(this);

    }

    public ContactModel getContactModel(Contact contact){
        ContactModel model = new ContactModel();
        model.setPhone(contact.getPhone());
        model.setEmail(contact.getEmail());
        model.setContactType(contact.getContactType());
        return model;
    }

    private void setReadContactFormProperties(){
        contactForm.binder.setReadOnly(true);
        contactForm.save.setVisible(false);
        contactForm.edit.setVisible(true);
        contactForm.cancel.setVisible(true);
        contactForm.update.setVisible(false);
        contactForm.exit.setVisible(false);
    }

    private void setUpdatedProperties(){
        contactForm.binder.setReadOnly(true);
        contactForm.save.setVisible(false);
        contactForm.edit.setVisible(false);
        contactForm.cancel.setVisible(true);
        contactForm.update.setVisible(true);
        contactForm.exit.setVisible(false);
    }


    private void setEditContactFormProperties(){
        contactForm.binder.setReadOnly(false);
        contactForm.save.setVisible(true);
        contactForm.edit.setVisible(false);
        contactForm.cancel.setVisible(true);
        //contactForm.update.setVisible(false);
        contactForm.exit.setVisible(true);
    }

}
