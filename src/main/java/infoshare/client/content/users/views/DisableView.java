package infoshare.client.content.users.views;

import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.users.table.DisabledUsersTable;

/**
 * Created by THULEH on 2016/03/31.
 */
public class DisableView extends VerticalLayout implements Property.ValueChangeListener {
    private final MainLayout main;
    private final DisabledUsersTable table;
    public DisableView(MainLayout main) {
        this.main = main;
        table = new DisabledUsersTable(main);
        addComponent(table);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }

}
