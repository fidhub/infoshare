package infoshare.client.content.common.demographics.table;

import com.vaadin.ui.Table;
import hashwork.client.content.MainLayout;
import hashwork.domain.ui.demographics.MaritalStatus;
import hashwork.services.ui.demographics.Impl.MaritalStatusListServiceImpl;
import hashwork.services.ui.demographics.MaritalStatusListService;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
public class MaritalStatusListTable extends Table {
    private final MaritalStatusListService maritalStatusListService = new MaritalStatusListServiceImpl();
    private final MainLayout main;

    public MaritalStatusListTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Status Name", String.class, null);

        // Add Data Columns
        Set<MaritalStatus> maritalStatuses = maritalStatusListService.findAll();
        for (MaritalStatus maritalStatus : maritalStatuses) {
            addItem(new Object[]{maritalStatus.getName()}, maritalStatus.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }

}
