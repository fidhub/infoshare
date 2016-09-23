package infoshare.viewUI.container.content.views;

import com.vaadin.ui.VerticalLayout;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.content.tables.DisabledContentTable;

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
