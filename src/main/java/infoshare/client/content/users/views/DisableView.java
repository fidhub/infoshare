package infoshare.client.content.users.views;

import com.vaadin.data.Property;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.users.table.DisabledUsersTable;
import infoshare.filterSearch.UserFilter;

import java.util.stream.Collectors;

/**
 * Created by THULEH on 2016/03/31.
 */
public class DisableView extends VerticalLayout implements Property.ValueChangeListener {
    private final MainLayout main;
    private final DisabledUsersTable table;
    public DisableView(MainLayout main) {
        this.main = main;
        table = new DisabledUsersTable(main);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        UserFilter search = new UserFilter();
        layout.addComponent(search.field);
        search.field.addTextChangeListener(textChangeEvent -> {
            table.removeAllItems();
            table.applicants.stream()
                    .filter(user -> user.getEmailAddress().contains(textChangeEvent.getText()))
                    .collect(Collectors.toSet()).forEach(table::loadTable);
        });
        addComponent(layout);
        addComponent(table);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }

}
