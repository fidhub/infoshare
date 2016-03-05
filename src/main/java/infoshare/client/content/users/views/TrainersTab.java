package infoshare.client.content.users.views;

import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;
import hashwork.client.content.MainLayout;
import hashwork.client.content.users.table.TrainersTable;

/**
 * Created by hashcode on 2015/10/22.
 */
public class TrainersTab extends VerticalLayout implements
        Property.ValueChangeListener {

    private final MainLayout main;

    public TrainersTab(MainLayout main) {
        this.main = main;
        addComponent(new TrainersTable(main));
    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
