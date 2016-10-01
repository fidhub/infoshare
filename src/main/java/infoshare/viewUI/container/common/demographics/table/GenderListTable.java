package infoshare.viewUI.container.common.demographics.table;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.DemographicsFacade;
import infoshare.viewUI.container.MainLayout;
import infoshare.domain.demographics.Gender;

import java.util.Set;



/**
 * Created by hashcode on 2015/08/18.
 */
public class GenderListTable extends Table {
    private final MainLayout main;


    public GenderListTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);

        addContainerProperty("Gender ", String.class, null);
        // Add Data Columns
        Set<Gender> genders = DemographicsFacade.genderListService.findAll();
        for (Gender gender : genders) {
            addItem(new Object[]{gender.getName()}, gender.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }
}
