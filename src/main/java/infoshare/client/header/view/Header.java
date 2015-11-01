package infoshare.client.header.view;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.LayoutEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.*;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.ContentMenu;
import infoshare.client.content.content.views.RawView;
import infoshare.client.header.landing_page.LandingHome;
import infoshare.domain.Contact;
import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;

/**
 * Created by hashcode on 2015/06/23.
 */
public class Header extends VerticalLayout implements Button.ClickListener {

    private ContentService contentService = new ContentServiceImp();
    private MainLayout main ;
    private Window notifications;
    private Window userProfile;
    public Button home = new Button();
    public Button notify = new Button();
    public Button user = new Button();
    private Table notificationTable = new Table();
    public Header(MainLayout main) {
        this.main = main;
        setSizeFull();
        setSpacing(true);
        addComponent(getHeaderPanel());
        addListener();
        refreshNotification();
    }

    private Panel getHeaderPanel(){
        final Panel headerPanel = new Panel();
        headerPanel.setSizeFull();
        headerPanel.setHeight(150.0f, Unit.PIXELS);
        headerPanel.addStyleName(ChameleonTheme.PANEL_LIGHT);
        headerPanel.addStyleName(ChameleonTheme.PANEL_BUBBLE);
        headerPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);

        headerPanel.setContent(getLayout());
        return headerPanel;
    }
    @Override
    public  void buttonClick(ClickEvent clickEvent) {
        Button source = clickEvent.getButton();
        if(source == home){
            if (notifications != null && notifications.getUI() != null) {
                notifications.close();
            }
            if (userProfile != null && userProfile.getUI() !=null) {
                userProfile.close();
            }
            main.content.setSecondComponent(new LandingHome(main));
            home.getUI();
        }else if(source == notify){
          notificationButton(clickEvent);
        }else if(source ==user){
          userButton(clickEvent);
        }
    }
    private void notificationButton(ClickEvent clickEvent ){
        if (userProfile != null && userProfile.getUI() !=null) {
            userProfile.close();
        }
        if (notifications != null && notifications.getUI() != null) {
            notifications.close();
        } else {
            refreshNotification();
            buildNotifications(clickEvent);
            getUI().addWindow(notifications);
            notifications.focus();
            main.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
                @Override
                public void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                    notifications.close();
                    main.removeLayoutClickListener(this);
                }
            });
        }
    }
    private void userButton(ClickEvent clickEvent){
        if (notifications != null && notifications.getUI() != null) {
            notifications.close();
        }
        if (userProfile != null && userProfile.getUI() !=null) {
            userProfile.close();
        }else {
            buildUser(clickEvent);
            getUI().addWindow(userProfile);
            userProfile.focus();
            main.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
                @Override
                public void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                    userProfile.close();
                    main.removeLayoutClickListener(this);
                }
            });
        }
    }
    public  void buildNotifications( ClickEvent event) {
        notifications = new Window();
        final VerticalLayout layout = new VerticalLayout();
        layout.setHeight(100.0f, Unit.PERCENTAGE);

        HorizontalLayout footer = new HorizontalLayout();
        footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
        footer.setWidth("100%");
        Button more = new Button("View All Raw content",
                event1 -> {
                    main.content.setSecondComponent(new ContentMenu(main, "LANDING"));
                    notifications.close();
                });
        more.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        more.addStyleName(ValoTheme.BUTTON_TINY);
        footer.addComponent(more);
        footer.setComponentAlignment(more, Alignment.TOP_CENTER);


        notificationTable.setWidth("100%");
        notificationTable.setHeight(360.0f, Unit.PIXELS);
        notificationTable.addContainerProperty("Notifications", Label.class, null);
        notificationTable.addStyleName(ValoTheme.TABLE_BORDERLESS);
        notificationTable.addStyleName(ValoTheme.TABLE_NO_STRIPES);
        notificationTable.addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        notificationTable.addStyleName(ValoTheme.TABLE_SMALL);
        notificationTable.setSelectable(true);
        notificationTable.setNullSelectionAllowed(false);
        notificationTable.setImmediate(true);

        refreshNotification();
        notificationTable.addItemClickListener(event1 -> {
            if (event1.isDoubleClick())
            {
                RawView rawView = new RawView(main);
                notifications.close();
                rawView.tableId = notificationTable.getValue().toString();
                rawView.EditButton();
            }
        });
        layout.addComponent(notificationTable);
        layout.setComponentAlignment(notificationTable, Alignment.TOP_CENTER);
        layout.addComponent(footer);
        layout.setComponentAlignment(footer,Alignment.BOTTOM_CENTER);

        notifications.setContent(layout);
        notifications.setWidth("300px");
        notifications.addStyleName("notifications");
        notifications.setHeight(400.0f,Unit.PIXELS);
        notifications.setResizable(false);
        notifications.setClosable(false);
        notifications.setDraggable(false);
        notifications.setPositionX(event.getClientX() - (event.getRelativeX() + 150));
        notifications.setPositionY((event.getClientY() + 40) - event.getRelativeY());
        notifications.setCloseShortcut(ShortcutAction.KeyCode.ESCAPE, null);

    }
    private void buildUser(ClickEvent event) {
        userProfile = new Window();
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        Button profile = new Button("Your profile");
        profile.addStyleName(ValoTheme.BUTTON_TINY);
        profile.setIcon(FontAwesome.USER);
        profile.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        profile.setSizeFull();

        Button signOut = new Button( "Sign out");
        signOut.addStyleName(ValoTheme.BUTTON_TINY);
        signOut.setIcon(FontAwesome.SIGN_OUT);
        signOut.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        signOut.setSizeFull();
        signOut.addClickListener(clickEvent -> {
            VaadinSession.getCurrent().close();
            Page.getCurrent().reload();
        });

        layout.addComponent(profile);
        layout.addComponent(signOut);

        userProfile.setContent(layout);
        userProfile.setWidth("80px");
        userProfile.addStyleName("notifications");
        userProfile.setResizable(false);
        userProfile.setClosable(false);
        userProfile.setDraggable(false);
        userProfile.setPositionX(event.getClientX() - event.getRelativeX() );
        userProfile.setPositionY((event.getClientY() + 40) - event.getRelativeY());
        userProfile.setCloseShortcut(ShortcutAction.KeyCode.ESCAPE, null);

    }
    private void addListener(){
        home.addClickListener((Button.ClickListener)this);
        notify.addClickListener((Button.ClickListener) this);
        user.addClickListener((Button.ClickListener)this);
    }

    private String differInTime(Date dateCreated){
        Date date1 = new Date();
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(dateCreated);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(date1);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        long diff = date1.getTime() - dateCreated.getTime();
        long diffSeconds = diff    / 1000 % 60;
        long diffMinutes = diff   / (60 * 1000) % 60;
        long diffHours   = diff  / (60 * 60 * 1000) % 24;
        long diffDays    = diff / (24 * 60 * 60 * 1000);

        if(diffYear > 0)
            return diffYear +" Years ago";
        else if (diffMonth >0)
            return diffMonth +" Month(s) ago";
        else if(diffDays > 0)
            return diffDays +" Days ago";
         if(diffHours > 0)
            return diffHours +" Hours ago";
         else if(diffMinutes > 0)
            return diffMinutes + " Minutes ago";
          else
            return diffSeconds + " seconds ago";
    }

    public void refreshNotification() {
        int i = 0 ;
            for (Content content: contentService.findAll()) {

                if (content.getSource().equalsIgnoreCase("mobile")) {
                    UrlPath.isEdited = RestApiConnectorClass.readAll(UrlPath.ContentLinks.isEdited + content.getId(), Boolean.class);
                    if (!UrlPath.isEdited.contains(true)) {
                        try {
                            i++;
                            notify.addStyleName("notifications");
                            notify.addStyleName("unread");
                            notify.setCaption(i + "");
                            notify.setDescription(i + " un-edited tips content");
                            notificationTable.addItem(new Object[]{new Label(
                                    "<b>" + content.getCreator().toUpperCase()
                                            + "</b> created a new tip <br><span><i>"
                                            + differInTime(content.getDateCreated()) + "</i></span><br>"
                                            + "<b> TITLE: </b><i>" + content.getTitle() + "</i>", ContentMode.HTML)
                            }, content.getId());
                        } catch (Exception r) {
                        }
                    }
                }
            }
        if(i==0){
            notify.removeStyleName("unread");
            notify.setCaption("");
            notify.setDescription("No new tips");
        }
    }

    private HorizontalLayout getLayout(){
        final HorizontalLayout layout = new HorizontalLayout();
        layout.addStyleName("dashboard-view");
        layout.setSpacing(true);
        layout.setSizeFull();

        Component logo = getLogo();
        layout.addComponent(logo);
        layout.setComponentAlignment(logo,Alignment.MIDDLE_LEFT);

        final HorizontalLayout barMenu = new HorizontalLayout() ;
        barMenu.setSpacing(true);

        barMenu.addComponent(getBar());
        final TextField textField = getSearch();
        barMenu.addComponent(user);
        barMenu.addComponent(textField);
        layout.addComponent(barMenu);
        layout.setComponentAlignment(barMenu, Alignment.MIDDLE_RIGHT);

        return layout;
    }
    private Component getLogo(){
        final HorizontalLayout logo = new HorizontalLayout();
        FileResource resource = new FileResource(
                new File("src/main/webapp/VAADIN/themes/dashboard/Kujali Logo.png"));
        Image logoImage = new Image(null,resource);
        logoImage.addStyleName("logo-header-image");
        logoImage.setHeight(80.0f, Unit.PIXELS);
        logoImage.setWidth(40.0f, Unit.PERCENTAGE);
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
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(false);
        home.setCaption("Home");
        home.setDescription("Home Page");
        home.setIcon(FontAwesome.HOME);
        home.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        home.addStyleName(ValoTheme.BUTTON_SMALL);

        user.setCaption("User name");
        user.setDescription("Your user name)");
        user.setIcon(FontAwesome.USER_MD);
        user.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        user.addStyleName(ValoTheme.BUTTON_SMALL);

        notify.addStyleName("notifications");
        notify.addStyleName("unread");
        notify.setIcon( new FileResource(
                new File("src/main/webapp/VAADIN/themes/dashboard/img/notifications.png")));
        notify.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        notify.addStyleName(ValoTheme.BUTTON_SMALL);

        layout.addComponent(home);
        layout.addComponent(notify);

        return layout;
    }

}