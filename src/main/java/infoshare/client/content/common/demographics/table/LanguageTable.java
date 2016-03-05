package infoshare.client.content.common.demographics.table;

import com.vaadin.ui.Table;
import hashwork.client.content.MainLayout;
import hashwork.domain.ui.demographics.Language;
import hashwork.services.ui.demographics.Impl.LanguageServiceImpl;
import hashwork.services.ui.demographics.LanguageService;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
public class LanguageTable extends Table {
    private final LanguageService languageService = new LanguageServiceImpl();
    private final MainLayout main;

    public LanguageTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Language", String.class, null);


        // Add Data Columns
        Set<Language> languages = languageService.findAll();
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
