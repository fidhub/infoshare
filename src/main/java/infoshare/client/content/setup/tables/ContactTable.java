package infoshare.client.content.setup.tables;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.domain.Contact;
import infoshare.services.Contact.ContactService;
import infoshare.services.Contact.Impl.ContactServiceImpl;

/**
 * Created by user9 on 2015/07/30.
 */
public class ContactTable extends Table {

    private final MainLayout main;
    private ContactService contactService = new ContactServiceImpl();
    public ContactTable(MainLayout main) {
        this.main = main;
        setWidth("100%");
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Phone number",String.class,null);
        addContainerProperty("Email Address",String.class,null);
        addContainerProperty("contact Type",String.class,null);

        for(Contact contact : contactService.findAll()){
            addItem(new Object[]{
                    contact.getPhone(),
                    contact.getEmail(),
                    contact.getContactType()
            },contact.getId());
        }
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }


}
