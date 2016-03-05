package infoshare.client.content.common.demographics.table;

import com.vaadin.ui.Table;
import hashwork.client.content.MainLayout;
import hashwork.domain.ui.demographics.Title;
import hashwork.services.ui.demographics.Impl.TitleListServiceImpl;
import hashwork.services.ui.demographics.TitleListService;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
public class TitleListTable extends Table {
    private final MainLayout main;
    private final TitleListService titleListService = new TitleListServiceImpl();

    public TitleListTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Title List", String.class, null);


        // Add Data Columns
        Set<Title> titles = titleListService.findAll();

        for (Title name : titles) {
            addItem(new Object[]{name.getName()}, name.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }


}
