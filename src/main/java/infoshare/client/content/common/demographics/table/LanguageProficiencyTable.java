package infoshare.client.content.common.demographics.table;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.DemographicsFacade;
import infoshare.client.content.MainLayout;
import infoshare.domain.demographics.LanguageProficiency;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
public class LanguageProficiencyTable extends Table {
    private final MainLayout main;


    public LanguageProficiencyTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Language Proficiency", String.class, null);

        // Add Data Columns
        Set<LanguageProficiency> languageProficiencys = DemographicsFacade.languageProficiencyService.findAll();
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
