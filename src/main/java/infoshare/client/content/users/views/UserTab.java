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

    private final MainLayout main;

    public UserTab(MainLayout main) {
        this.main = main;
        addComponent(new UsersTable(main));
    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
