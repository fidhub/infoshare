package infoshare.client.content.common.util.tables;

import com.vaadin.ui.Table;
import infoshare.app.facade.UtilFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.domain.util.Mail;

import java.util.Date;
import java.util.Set;

/**
 * Created by hashcode on 2015/11/29.
 */
public class MailTable extends Table {

    public MailTable() {

        setSizeFull();

        addContainerProperty("Date", Date.class, null);
        addContainerProperty("Key", String.class, null);
        addContainerProperty("Value", String.class, null);
        addContainerProperty("Host", String.class, null);
        addContainerProperty("Port", String.class, null);
        addContainerProperty("DomainState", String.class, null);


        // Add Data Columns
        Set<Mail> mails = UtilFacade.getMailServiceInstance().findAll(OrganisationUtil.getCompanyCode());
        for (Mail mail : mails) {
            addItem(new Object[]{
                    mail.getDate(),
                    mail.getKey(),
                    mail.getValue(),
                    mail.getHost(),
                    mail.getPort(),
                    mail.getState()}, mail.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
