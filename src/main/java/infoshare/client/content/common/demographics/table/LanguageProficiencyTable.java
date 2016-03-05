package infoshare.client.content.common.demographics.table;

import com.vaadin.ui.Table;
import hashwork.client.content.MainLayout;
import hashwork.domain.ui.demographics.LanguageProficiency;
import hashwork.services.ui.demographics.Impl.LanguageProficiencyServiceImpl;
import hashwork.services.ui.demographics.LanguageProficiencyService;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
public class LanguageProficiencyTable extends Table {
    private final MainLayout main;
    private final LanguageProficiencyService languageProficiencyService = new LanguageProficiencyServiceImpl();

    public LanguageProficiencyTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Language Proficiency", String.class, null);


        // Add Data Columns
        Set<LanguageProficiency> languageProficiencys = languageProficiencyService.findAll();
        for (LanguageProficiency proficiency : languageProficiencys) {
            addItem(new Object[]{proficiency.getName()}, proficiency.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }

}
