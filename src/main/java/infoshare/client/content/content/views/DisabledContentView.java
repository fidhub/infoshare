package infoshare.client.content.content.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.forms.EditForm;
import infoshare.client.content.content.tables.DisabledContentTable;
import infoshare.client.content.content.tables.EditTable;

/**
 * Created by codet on 2016/03/18.
 */
public class DisabledContentView  extends VerticalLayout{
    private final MainLayout main;
    private final DisabledContentTable table;
    public DisabledContentView(MainLayout main) {
        this.main = main;
        table =new DisabledContentTable(main);
        addComponent(table);
    }

}
