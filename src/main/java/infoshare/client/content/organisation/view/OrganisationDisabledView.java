package infoshare.client.content.organisation.view;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.organisation.table.OrganizationDisabledTable;

/**
 * Created by user9 on 2016/03/12.
 */
public class OrganisationDisabledView extends VerticalLayout implements Button.ClickListener,Property.ValueChangeListener{

    private final MainLayout main;
    private final OrganizationDisabledTable table;

    public OrganisationDisabledView(MainLayout mainApp) {
        this.main = mainApp;
        this.table = new OrganizationDisabledTable(main);
        addComponent(table);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {

    }
}
