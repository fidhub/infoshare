package infoshare.client.header;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;

/**
 * Created by hashcode on 2015/06/23.
 */
public class Header extends VerticalLayout {

    public Header() {
        this.setSizeFull();
        Component header = headerPanel();
        this.addComponent(header);
    }

    private Component headerPanel(){
        final HorizontalLayout headLayout = new HorizontalLayout();
        headLayout.setWidth("100%");


        final Panel headerPanel = new Panel("Info Share");
        headerPanel.setWidth("100%");
        headerPanel.setHeight("150px");

        Component logo = logo();
        headLayout.addComponent(logo);
        headLayout.setComponentAlignment(logo,Alignment.TOP_LEFT);

        Component menuItems = menuItems();
        headLayout.addComponent(menuItems);
        headLayout.setComponentAlignment(menuItems,Alignment.MIDDLE_RIGHT);

        headerPanel.setContent(headLayout);
        return headerPanel;
    }

    private Component logo(){
        final HorizontalLayout logo = new HorizontalLayout();
        FileResource resource = new FileResource(
                new File("src/main/webapp/VAADIN/themes/dashboard/favicon.ico"));
        Image logoImage = new Image(null,resource);
        logoImage.setHeight("50px");
        logo.addComponent(logoImage);
        return logo;
    }
    private Component menuItems(){

        final HorizontalLayout menuItems = new HorizontalLayout();
        menuItems.addStyleName("fields");
        Responsive.makeResponsive(menuItems);
        menuItems.setSizeUndefined();

        final  Link home =new Link(null,new ExternalResource("http://www.cput.ac.za"));
        home.setIcon(FontAwesome.HOME);
        home.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        home.addStyleName(ValoTheme.BUTTON_LARGE);
        home.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        menuItems.addComponent(home);

        final  MenuBar barMenu = new MenuBar();
        barMenu.addStyleName(ValoTheme.MENUBAR_SMALL);
        barMenu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);

        MenuBar.MenuItem messages = barMenu.addItem("Notification",null);
        messages.setIcon(FontAwesome.BELL);
        messages.addItem("",null);

        final MenuBar.MenuItem  UserDetails = barMenu.addItem(" User name",null); // from rest api
        UserDetails.setIcon(FontAwesome.USER);
        UserDetails.addItem("",null);
        UserDetails.addItem("My Account",null);
        UserDetails.addItem("Sing out",null);

        menuItems.addComponent(barMenu);

        final TextField searchBox = new TextField(null,"Search");
        searchBox.setIcon(FontAwesome.SEARCH);
        searchBox.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        searchBox.addStyleName(ValoTheme.TEXTAREA_SMALL);
        menuItems.addComponent(searchBox);

        return menuItems;
    }

}