package infoshare.client.header.form;

import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;

import java.io.File;


/**
 * Created by codex on 2015/07/13.
 */
public class HeaderForm extends HorizontalLayout {

    private MainLayout main;
    public Button home = new Button();
    public Button notify = new Button();
    public Button user = new Button();

    public HeaderForm(MainLayout main) {
        this.main = main;
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

        final HorizontalLayout buttons = getBar() ;
        final TextField textField = getSearch();
        buttons.addComponent(textField);

        layout.addComponent(buttons);
        layout.setComponentAlignment(buttons,Alignment.MIDDLE_RIGHT);

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
    private HorizontalLayout getBar(){
        final  HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(false);
        home.setCaption("Home");
        home.setDescription("Home/Landing Page");
        home.setIcon(FontAwesome.HOME);
        home.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        home.addStyleName(ValoTheme.BUTTON_SMALL);

        notify.setCaption("Notifications");
        notify.setDescription("Notifications (2 unread)");
        notify.setIcon(FontAwesome.BELL_O);
        notify.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        notify.addStyleName(ValoTheme.NOTIFICATION_BAR);
        notify.addStyleName("unread");
        notify.addStyleName("notifications");
        notify.addStyleName(ValoTheme.BUTTON_SMALL);

        user.setCaption("User name");
        user.setDescription("Your user name)");
        user.setIcon(FontAwesome.USER_MD);
        user.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        user.addStyleName(ValoTheme.BUTTON_SMALL);

        layout.addComponent(home);
        layout.addComponent(notify);
        layout.addComponent(user);
        return layout;
    }



}
