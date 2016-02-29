package infoshare.client.content.setup.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.forms.ContactForm;
import infoshare.client.content.setup.models.ContactModel;
import infoshare.client.content.setup.tables.AddressTable;
import infoshare.client.content.setup.tables.ContactTable;
import infoshare.domain.Contact;
import infoshare.services.Contact.ContactService;
import infoshare.services.Contact.Impl.ContactServiceImpl;
import infoshare.services.users.Impl.UserServiceImpl;
import infoshare.services.users.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Songezo on 2015-08-18.
 */
public class ContactView extends Window implements Button.ClickListener, Property.ValueChangeListener {

    private final ContactForm contactForm;
    private final ContactTable contactTable;
    private ContactService contactService = new ContactServiceImpl();
    private UserService userService = new UserServiceImpl();
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
            setEditContactFormProperties();
        }
        else if (source == contactForm.edit){
            setUpdatedProperties();
        }
        else if (source == contactForm.update){
            saveEditedForm(contactForm.binder);
        }
        else if (source == contactForm.exit){
            this.setModal(false);
            getUI().removeWindow(this);
        }else if (source == contactForm.save){
            saveForm(contactForm.binder);
            getHome();
        }
    }

    private void getHome() {
        contactForm.binder.setItemDataSource(new BeanItem<>(getClear()));
        contactTable.loadTable();
        contactTable.setValue(null);
    }

    private ContactModel getClear(){
        ContactModel model = new ContactModel();
        model.setContactType(null);
        model.setEmail(null);
        model.setPhone(null);
        return model;
    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

        final Property property = valueChangeEvent.getProperty();
        if (property == contactTable){
            try {
                final Contact contact = contactService.findById(contactTable.getValue().toString());
                final ContactModel bean = getContactModel(contact);
                contactForm.binder.setItemDataSource(new BeanItem<>(bean));
                setReadContactFormProperties();
               // setUpdatedProperties();
            }catch (Exception e){}
        }
    }

    private User getUserUpdateEntity(String contactID) {
        final User bean = userService.findById(AddressTable.userID);
        List<String> contacts = new ArrayList<>();

        if (bean.getContact()!= null) {
            for (String ID : bean.getContact()) {
                Contact contact = contactService.findById(ID);
                if (contact != null) {
                    contacts.add(contact.getId());
                }
            }
        }
        contacts.add(contactID);

        final User user = new User.Builder(bean.getLastName())
                .firstname(bean.getFirstName())
                .role(bean.getRole())
                .enable(bean.isEnable())
                .password(bean.getPassword())
                .username(bean.getUsername())
                .othername(bean.getOtherName())
                .address(bean.getAddress())//Todo : no route for entity yet
                .contact(contacts)//Todo : no route for entity yet
                .id(AddressTable.userID)
                .build();
        return user;
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            Contact contact = contactService.save(getContactNewEntity(binder));
            userService.update(getUserUpdateEntity(contact.getId()));
            getHome();
            Notification.show("Contact ADDED!", Notification.Type.HUMANIZED_MESSAGE);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        } catch(Exception dp){
            Notification.show("Un-expected error", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }
    }

    private Contact getContactNewEntity(FieldGroup binder) {
        final ContactModel bean = ((BeanItem<ContactModel>)binder.getItemDataSource()).getBean();
        final Contact contact = new Contact.Builder(bean.getPhone())
                .email(bean.getEmail())
                .contactType(bean.getContactType())
                .build();
        return contact;
    }

    private void addListeners() {

        contactForm.save.addClickListener(this);
        contactForm.edit.addClickListener(this);
        contactForm.cancel.addClickListener(this);
        contactForm.update.addClickListener(this);
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
        contactForm.binder.setReadOnly(false);
        contactForm.save.setVisible(false);
        contactForm.edit.setVisible(false);
        contactForm.cancel.setVisible(true);
        contactForm.update.setVisible(true);
        contactForm.exit.setVisible(false);
        ///saveEditedForm();
    }


    private void setEditContactFormProperties(){
        contactForm.binder.setReadOnly(false);
        contactForm.save.setVisible(true);
        contactForm.edit.setVisible(false);
        contactForm.cancel.setVisible(true);
        contactForm.update.setVisible(false);
        contactForm.exit.setVisible(true);
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            contactService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private Contact getUpdateEntity(FieldGroup binder) {
        final ContactModel bean = ((BeanItem<ContactModel>) binder.getItemDataSource()).getBean();
        final Contact contact = new Contact.Builder(bean.getPhone())
                .email(bean.getEmail())
                .contactType(bean.getContactType())
                .build();
        return contact;
    }

}
