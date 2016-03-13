package infoshare.client.content.organisation.view;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.organisation.form.AddOrganisationAdminForm;
import infoshare.client.content.organisation.model.AddOrganisationModel;
import infoshare.client.content.organisation.table.AddOrganisationAdminTable;

/**
 * Created by THULEBONA on 2016/03/13.
 */
public class AddOrganisationAdminView  extends VerticalLayout implements Button.ClickListener,Property.ValueChangeListener {
   private MainLayout main;
    private AddOrganisationAdminTable table;
    private AddOrganisationAdminForm form;

    public AddOrganisationAdminView(MainLayout main) {
        this.main = main;
        this.table = new AddOrganisationAdminTable(main);
        this.form = new AddOrganisationAdminForm();
        addComponent(form);
        addComponent(table);
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
