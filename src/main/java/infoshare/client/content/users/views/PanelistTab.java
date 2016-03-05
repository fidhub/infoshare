package infoshare.client.content.users.views;

import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;
import hashwork.client.content.MainLayout;
import hashwork.client.content.users.table.PanelistTable;

/**
 * Created by hashcode on 2015/10/22.
 */
public class PanelistTab extends VerticalLayout implements
        Property.ValueChangeListener {

    private final MainLayout main;

    public PanelistTab(MainLayout main) {
        this.main = main;
        addComponent(new PanelistTable(main));
    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
