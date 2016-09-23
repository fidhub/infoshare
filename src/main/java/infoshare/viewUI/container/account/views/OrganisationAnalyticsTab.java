package infoshare.viewUI.container.account.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import infoshare.viewUI.container.MainLayout;


/**
 * Created by hashcode on 2015/11/16.
 */
public class OrganisationAnalyticsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;

    public OrganisationAnalyticsTab(MainLayout main) {
        this.main = main;
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
