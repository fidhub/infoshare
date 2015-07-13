package infoshare.client.header;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.client.home.Index;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;

import java.io.File;

/**
 * Created by hashcode on 2015/06/23.
 */
@Theme("mytheme")
public class Header extends VerticalLayout implements
        Button.ClickListener {

    private ContentService content = new ContentServiceImp();
    private MainLayout main ;
    private Button home;
    public Header() {
        this.setSizeFull();
        setSpacing(true);
        this.addComponent(headerPanel());
        addEvent();
    }

    private Component headerPanel(){
        final HorizontalLayout headLayout = new HorizontalLayout();
        headLayout.setWidth("100%");

        final Panel headerPanel = new Panel();
        headerPanel.setWidth("100%");
        headerPanel.setHeight(120.0f,Unit.PIXELS);
        headerPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);

        Component logo = logo();
        headLayout.addComponent(logo);
        headLayout.setComponentAlignment(logo,Alignment.BOTTOM_CENTER);
        Component menuItems = menuItems();
        headLayout.addComponent(menuItems);
        headLayout.setComponentAlignment(menuItems,Alignment.MIDDLE_RIGHT);

        headerPanel.setContent(headLayout);
        return headerPanel;
    }

    private Component logo(){
        final HorizontalLayout logo = new HorizontalLayout();
        FileResource resource = new FileResource(
                new File("src/main/webapp/VAADIN/themes/dashboard/header.png"));
        Image logoImage = new Image(null,resource);
        Responsive.makeResponsive(logoImage);
        logoImage.setHeight(80.0f,Unit.PIXELS);
        logoImage.setWidth(100.0f,Unit.PERCENTAGE);
        logo.addComponent(logoImage);
        return logo;
    }
    private Component menuItems(){

        final HorizontalLayout menuItems = new HorizontalLayout();
        Responsive.makeResponsive(menuItems);
        menuItems.setSizeUndefined();

        home =new Button("HOME");
        home.setIcon(FontAwesome.HOME);
        home.setStyleName("header-panel");
       /* home.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        home.addStyleName(ValoTheme.BUTTON_SMALL);*/
        menuItems.addComponent(home);

        final  MenuBar barMenu = new MenuBar();
        barMenu.addStyleName(ValoTheme.MENUBAR_SMALL);
        barMenu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);

        MenuBar.MenuItem messages = barMenu.addItem("Notification",null);
        messages.setIcon(FontAwesome.BELL);

        messages.addItem("",null);
        /*for (Content cont: content.findAll()){
             messages.addItem(cont.getCategory()+"      "+cont.getTitle(),null);
             messages.addSeparator();
        }*/


        final MenuBar.MenuItem  UserDetails = barMenu.addItem(" User name",null); // from rest api
        UserDetails.setIcon(FontAwesome.USER_MD);
        UserDetails.addItem("",null);
        UserDetails.addItem("My Account",null);
        UserDetails.addItem("Sing out",null);

        menuItems.addComponent(barMenu);

        final TextField searchBox = new TextField();
        searchBox.setIcon(FontAwesome.SEARCH);
        searchBox.setInputPrompt("Search");
        searchBox.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        searchBox.addStyleName(ValoTheme.TEXTAREA_SMALL);
        menuItems.addComponent(searchBox);

        return menuItems;
    }


    private void getHome(){
        Index mainApp = new Index();
        main = new MainLayout(mainApp);
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        final Button source = clickEvent.getButton();
        if (home == source) {
            getHome();
        }
    }
    private void addEvent(){
        home.addClickListener((Button.ClickListener)this);
    }
}