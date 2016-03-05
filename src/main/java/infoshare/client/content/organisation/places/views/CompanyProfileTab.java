package infoshare.client.content.organisation.places.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.organisation.places.views.company.CompanyGrid;


/**
 * Created by hashcode on 2015/12/29.
 */
public class CompanyProfileTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {
    private final MainLayout main;
    private final GridLayout grid;


    public CompanyProfileTab(MainLayout main) {

        this.main = main;
        grid = new CompanyGrid(main);
        addComponent(grid);

    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
