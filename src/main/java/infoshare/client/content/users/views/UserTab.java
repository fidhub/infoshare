package infoshare.client.content.users.views;

import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.users.table.UsersTable;


/**
 * Created by hashcode on 2015/10/22.
 */
public class UserTab extends VerticalLayout implements
        Property.ValueChangeListener {
    private UsersTable table;
    private final MainLayout main;
    public UserTab(MainLayout main) {
        this.main = main;
        this.table = new UsersTable(main);
        addComponent(table);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
