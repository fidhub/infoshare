package infoshare.client.content.profile.contacts.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;


/**
 * Created by hashcode on 2015/12/07.
 */
public class ContactsSummaryTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    final private MainLayout main;

    public ContactsSummaryTab(MainLayout main) {
        this.main = main;
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}