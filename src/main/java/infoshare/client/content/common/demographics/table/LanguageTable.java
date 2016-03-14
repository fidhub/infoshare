package infoshare.client.content.common.demographics.table;

import com.vaadin.ui.Table;
import infoshare.app.facade.DemographicsFacade;
import infoshare.client.content.MainLayout;
import infoshare.domain.demographics.Language;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
public class LanguageTable extends Table {

    private final MainLayout main;

    public LanguageTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Language", String.class, null);


        // Add Data Columns
        Set<Language> languages = DemographicsFacade.getLanguageServiceInstance().findAll();
        for (Language language : languages) {
            addItem(new Object[]{language.getName()}, language.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }

}
