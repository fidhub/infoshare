package infoshare.client.content.setup.views;

import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.tables.PersonDetailsTable;
import infoshare.filterSearch.UserFilter;

/**
 * Created by user9 on 2016/02/11.
 */
public class PersonDetailsView extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final PersonDetailsTable table;
    private final UserFilter userDetails;

    public PersonDetailsView(MainLayout app) {
        main = app;
        table = new PersonDetailsTable(main);
        userDetails = new UserFilter();
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(false);
        layout.addComponent(userDetails.field);
        setSpacing(false);
        addListeners();
        addComponent(layout);
        addComponent(table);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {

    }
    public void refreshContacts(String stringFilter ) {
        try {
            table.removeAllItems();
            userDetails.findAll(stringFilter,"CPUT").forEach(this.table::loadTable);
        }catch (Exception e){
        }
    }
    public void addListeners(){

        table.addValueChangeListener(this);
        userDetails.field.addTextChangeListener((FieldEvents.TextChangeListener)
                textChangeEvent -> refreshContacts(textChangeEvent.getText()));


    }
}
