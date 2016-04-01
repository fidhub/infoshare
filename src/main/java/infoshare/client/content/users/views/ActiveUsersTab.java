package infoshare.client.content.users.views;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.client.content.users.forms.UserForm;
import infoshare.client.content.users.table.UsersTable;
import infoshare.filterSearch.UserFilter;


/**
 * Created by hashcode on 2015/10/22.
 */
public class ActiveUsersTab extends VerticalLayout implements Button.ClickListener{
    private UsersTable table;
    private final MainLayout main;
    private Button addNewUser;
    private UserFilter search ;
    private UserForm form;
    public final VerticalLayout contentPanel = new VerticalLayout();
    public ActiveUsersTab(MainLayout main) {
        this.main = main;
        table = new UsersTable(main,this);
        form = new UserForm(main);
        addNewUser = new Button();
        search = new UserFilter();
        addNewUser.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        addNewUser.setIcon(FontAwesome.USER_PLUS);
        addNewUser.setSizeFull();
        addNewUser.setCaption(" Add new User");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.addComponent(search.field);
        layout.addComponent(addNewUser);
        contentPanel.removeAllComponents();
        contentPanel.addComponent(layout);
        contentPanel.addComponent(table);
        addComponent(contentPanel);
        addNewUser.addClickListener(this);


    }
     @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
         final Button source = clickEvent.getButton();
         if (source == form.save) {
             contentPanel.removeAllComponents();
             contentPanel.addComponent(form);
         }if (source == addNewUser) {
             contentPanel.removeAllComponents();
             contentPanel.addComponent(form);
         }
    }
}
