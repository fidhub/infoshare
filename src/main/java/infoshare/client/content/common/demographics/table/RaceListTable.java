package infoshare.client.content.common.demographics.table;

import com.vaadin.ui.Table;
import hashwork.client.content.MainLayout;
import hashwork.domain.ui.demographics.Race;
import hashwork.services.ui.demographics.Impl.RaceListServiceImpl;
import hashwork.services.ui.demographics.RaceListService;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
public class RaceListTable extends Table {
    private final MainLayout main;
    private final RaceListService raceListService = new RaceListServiceImpl();

    public RaceListTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Race Name", String.class, null);


        // Add Data Columns
        Set<Race> races = raceListService.findAll();
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
