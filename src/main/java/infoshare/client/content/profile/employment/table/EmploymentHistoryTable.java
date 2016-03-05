package infoshare.client.content.profile.employment.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import hashwork.app.facade.PeopleFacade;
import hashwork.app.util.security.GetUserCredentials;
import hashwork.client.content.MainLayout;
import hashwork.domain.people.PersonEmploymentHistory;

import java.util.Date;
import java.util.Set;

/**
 * Created by hashcode on 2015/12/20.
 */
public class EmploymentHistoryTable extends Table {
    private final MainLayout main;

    public EmploymentHistoryTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        final String personId = GetUserCredentials.getUser().getId();


        addContainerProperty("Date Created", Date.class, null);
        addContainerProperty("Company Name", String.class, null);
        addContainerProperty("Start Date", Date.class, null);
        addContainerProperty("End Date", Date.class, null);
        addContainerProperty("Details", Button.class, null);

        Set<PersonEmploymentHistory> personEmploymentHistories = PeopleFacade.personEmployementHistoryService.findAll(personId);

        personEmploymentHistories.parallelStream().forEach(item -> {

            Button details = new Button("Details");
            details.setStyleName(ValoTheme.BUTTON_LINK);
            details.setData(item.getId());
            details.addClickListener(event -> {


            });

            addItem(new Object[]{
                    item.getDate(),
                    item.getCompanyName(),
                    item.getStartDate(),
                    item.getEndDate(),
                    details
            }, item.getId());

        });
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }


}
