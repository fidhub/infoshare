package infoshare.client.content.setup.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.SetupMenu;
import infoshare.client.content.setup.forms.ContactForm;
import infoshare.client.content.setup.models.ContactModel;
import infoshare.client.content.setup.tables.ContactTable;
import infoshare.domain.Contact;
import infoshare.services.Contact.ContactService;
import infoshare.services.Contact.Impl.ContactServiceImpl;

/**
 * Created by user9 on 2015/07/30.
 */
public class ContactView extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {
    private ContactService service = new ContactServiceImpl();

    private ContactForm form;
    private MainLayout main;
    private ContactTable table;

    public ContactView(MainLayout mainApp) {
        this.main = mainApp;
        form = new ContactForm();
        table = new ContactTable(main);
        form.edit.setVisible(false);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        final Button source = clickEvent.getButton();
        if (source == form.save){
            saveForm(form.binder);
        }else if(source == form.clear){
            getHome();
        }else if(source == form.edit){
            editor();
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
        final Property property = valueChangeEvent.getProperty();
        if( property == table){
            final Contact contact = service.find(table.getValue().toString());
            final ContactModel bean = getModel(contact);
            form.binder.setItemDataSource(new BeanItem<>(bean));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            service.save(getNewEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }catch(Exception dp){
            Notification.show(dp.toString(), Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new SetupMenu(main, "CONTACT"));
    }

    private Contact getNewEntity(FieldGroup binder) {
        final ContactModel bean = ((BeanItem<ContactModel>) binder.getItemDataSource()).getBean();
        final Contact contact = new Contact.Builder(bean.getPhone())
                .email(bean.getEmail())
                .contactType(bean.getContactType())
                .build();
        return contact;
    }

    private void editor(){
        form.binder.setReadOnly(false);
        //Buttons Behaviour
        form.save.setVisible(true);
        form.edit.setVisible(false);
    }

    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviour
        form.save.setVisible(false);
        form.edit.setVisible(true);
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener((Button.ClickListener) this);
        form.edit.addClickListener((Button.ClickListener) this);
        form.clear.addClickListener((Button.ClickListener) this);
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private ContactModel getModel(Contact contact){
        final ContactModel model = new ContactModel();
        model.setEmail(contact.getEmail());
        model.setPhone(contact.getPhone());
        model.setContactType(contact.getContactType());

        return model;
    }
}
