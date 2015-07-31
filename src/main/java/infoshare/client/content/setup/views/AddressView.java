package infoshare.client.content.setup.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.setup.forms.AddressForm;
import infoshare.client.content.setup.tables.AddressTable;
import infoshare.domain.Address;

/**
 * Created by codex on 2015/07/30.
 */
public class AddressView extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {
    private MainLayout main;
    private AddressForm form;
    private AddressTable table;
    public AddressView(MainLayout main) {
        this.main = main;
        this.form = new AddressForm();
        this.table = new AddressTable();
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener((Button.ClickListener) this);
        form.clear.addClickListener((Button.ClickListener) this);
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }
}
