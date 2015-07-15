package infoshare.client.header.form;

import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.client.header.HeaderMenu;

import java.io.File;


/**
 * Created by codex on 2015/07/13.
 */
public class HeaderForm extends HorizontalLayout {

    private MainLayout main;
    public MenuBar menuBar = new MenuBar();
    public MenuBar.MenuItem home ;
    public MenuBar.MenuItem notif;
    public MenuBar.MenuItem user;
    public HeaderForm() {
        setSizeFull();
        addComponent(getLayout());
    }
    private HorizontalLayout getLayout(){
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setSizeFull();

        Component logo = getLogo();
        layout.addComponent(logo);
        layout.setComponentAlignment(logo,Alignment.MIDDLE_LEFT);

        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        MenuBar barMenu = getBar();
        horizontalLayout.addComponent(barMenu);

        final TextField textField = getSearch();
        horizontalLayout.addComponent(textField);

        layout.addComponent(horizontalLayout);
        layout.setComponentAlignment(horizontalLayout,Alignment.MIDDLE_RIGHT);

        return layout;
    }
    private Component getLogo(){
        final HorizontalLayout logo = new HorizontalLayout();
        FileResource resource = new FileResource(
                new File("src/main/webapp/VAADIN/themes/dashboard/headeredited.jpg"));
        Image logoImage = new Image(null,resource);

        logoImage.setHeight(80.0f,Unit.PIXELS);
        logoImage.setWidth(40.0f,Unit.PERCENTAGE);
        logo.addComponent(logoImage);
        Responsive.makeResponsive(logo);
        return logo;
    }
    private TextField getSearch(){

        final TextField searchBox = new TextField();
        searchBox.setIcon(FontAwesome.SEARCH);
        searchBox.setInputPrompt("Search");
        searchBox.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        searchBox.addStyleName(ValoTheme.TEXTAREA_SMALL);
        return searchBox;
    }
    private MenuBar getBar(){
        menuBar.addStyleName(ValoTheme.MENUBAR_SMALL);
        menuBar.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
        home = menuBar.addItem("Home", FontAwesome.HOME, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                main.content.setSecondComponent(new HeaderMenu(main));
            }
        });
        notif = menuBar.addItem("Notifications",FontAwesome.BELL_O,null);

        user = menuBar.addItem("User name",FontAwesome.USER_MD,null);

        return menuBar;
    }

}
