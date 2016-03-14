package infoshare.client.content.common.demographics.table;

import com.vaadin.ui.Table;
import infoshare.app.facade.DemographicsFacade;
import infoshare.client.content.MainLayout;
import infoshare.domain.demographics.Race;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
public class RaceListTable extends Table {
    private final MainLayout main;


    public RaceListTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Race Name", String.class, null);


        // Add Data Columns
        Set<Race> races = DemographicsFacade.getRaceServiceInstance().findAll();
        for (Race locationType : races) {
            addItem(new Object[]{locationType.getName()}, locationType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }

}
