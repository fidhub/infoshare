package infoshare.client.content.users.views;

import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;
import hashwork.client.content.MainLayout;
import hashwork.client.content.users.table.ApplicantsTable;

/**
 * Created by hashcode on 2015/10/22.
 */
public class JobApplicatsTab extends VerticalLayout implements
        Property.ValueChangeListener {

    private final MainLayout main;
    private final ApplicantsTable table;

    public JobApplicatsTab(MainLayout main) {
        this.main = main;
        table = new ApplicantsTable(main);

        addComponent(table);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
