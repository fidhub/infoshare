package infoshare.client.content.users.views;

import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;
import hashwork.client.content.MainLayout;
import hashwork.client.content.users.table.EmployeeTable;

/**
 * Created by hashcode on 2015/10/22.
 */
public class EmployeeTab extends VerticalLayout implements
        Property.ValueChangeListener {

    private final MainLayout main;

    public EmployeeTab(MainLayout main) {
        this.main = main;
        addComponent(new EmployeeTable(main));
    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
