package infoshare.client.content.setup.tables;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;


/**
 * Created by user9 on 2015/07/30.
 */
public class ContactTable extends Table {

    private final MainLayout main;


    public ContactTable(MainLayout main) {
        this.main = main;
        setWidth("100%");
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Phone number", String.class, null);
        addContainerProperty("Email PersonAddress",String.class,null);
        addContainerProperty("contact Type",String.class,null);
        loadTable();

        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }

    public void loadTable() {

    }

}
