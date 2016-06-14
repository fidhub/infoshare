package infoshare.client.header;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.LayoutEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.*;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.ContentFacade;
import infoshare.app.facade.OrganisationFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.ContentMenu;
import infoshare.client.content.content.views.RawView;
import infoshare.client.content.home.HomeMenu;
import infoshare.domain.content.RawContent;
import infoshare.services.ContentFiles.content.RawContentService;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/23.
 */
//@PreserveOnRefresh
public class Header extends VerticalLayout implements Button.ClickListener , ItemClickEvent.ItemClickListener{

    private static RawContentService rawContentService = ContentFacade.rawContentService;
    private static MainLayout main ;
    private static Window notifications;
    private static Window userProfile;
    public static Button home = new Button();
    public static Button notify = new Button();
    public static Button user = new Button();
    private static Table notificationTable = new Table();
    private  final Page page;

    public Header(MainLayout main,Page page) {
        this.main = main;
        this.page = page;
        setSizeFull();
        setSpacing(true);
        addComponent(getHeaderPanel());
        addListener();
        Responsive.makeResponsive(this);
        refreshNotification();
    }

    private static Panel getHeaderPanel(){
        final Panel headerPanel = new Panel();
        headerPanel.setSizeFull();
        headerPanel.setHeight(150.0f, Unit.PIXELS);
        headerPanel.addStyleName(ChameleonTheme.PANEL_LIGHT);
        headerPanel.addStyleName(ChameleonTheme.PANEL_BUBBLE);
        headerPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        Responsive.makeResponsive(headerPanel);
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
            main.content.setSecondComponent(new HomeMenu(main,"LANDING"));
            home.getUI();
        }else if(source == notify){
          notificationButton(clickEvent);
        }else if(source ==user){
          userButton(clickEvent);
        }
    }

    private void notificationButton(ClickEvent clickEvent){
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

    public static void buildNotifications( ClickEvent event) {
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
        notificationTable.setPageLength(5);

        refreshNotification();

        notificationTable.addItemClickListener(event1 -> {
            if (event1.isDoubleClick()) {
                notifications.close();
                RawView.tableId = notificationTable.getValue().toString();
                new RawView(main).EditButton();
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

    }

    private void buildUser(ClickEvent event) {
        userProfile = new Window();
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        Button signOut = new Button( "Sign out");
        signOut.addStyleName(ValoTheme.BUTTON_TINY);
        signOut.setIcon(FontAwesome.SIGN_OUT);
        signOut.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        signOut.setSizeFull();
        signOut.addClickListener(clickEvent -> page.setLocation("/logout"));

        layout.addComponent(signOut);

        userProfile.setContent(layout);
        userProfile.setWidth("80px");
        userProfile.addStyleName("notifications");
        userProfile.setResizable(false);
        userProfile.setClosable(false);
        userProfile.setDraggable(false);
        userProfile.setPositionX(event.getClientX() - event.getRelativeX() );
        userProfile.setPositionY((event.getClientY() + 40) - event.getRelativeY());

    }

    private void addListener(){
        home.addClickListener(this);
        notify.addClickListener(this);
        user.addClickListener(this);
    }

    private static String differInTime(Date dateCreated){
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

    public static void refreshNotification() {
        int i = 0 ;
            for (RawContent rawContent :rawContentService.findAll(OrganisationUtil.getCompanyCode())
                    .stream()
                    .filter(cont ->cont.getState().equalsIgnoreCase("Active"))
                    .collect(Collectors.toList())
                    .stream()
                .filter(cont -> cont.getStatus().equalsIgnoreCase("Raw"))
                .collect(Collectors.toList())){
                try {

                    notificationTable.addItem(new Object[]{new Label(
                            "<b>" + rawContent.getCreator().toUpperCase()
                                    + "<br><span><i>"
                                    + differInTime(rawContent.getDateCreated())
                                    + "</i></span><br>"
                                    + "<b> TITLE: </b><i>" + rawContent.getTitle()
                                    + "</i>", ContentMode.HTML)
                    }, rawContent.getId());
                    i++;
                    notify.addStyleName("notifications");
                    notify.addStyleName("unread");
                    notify.setCaption(i + "");
                    notify.setDescription(i + " un-edited tips content");
                } catch (Exception r) {
                }
            }

        if(i==0){
            notify.removeStyleName("unread");
            notify.setCaption("");
            notify.setDescription("No new tips");
            notificationTable.removeAllItems();
        }
    }

    private static HorizontalLayout getLayout(){
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
        barMenu.addComponent(user);
        layout.addComponent(barMenu);
        layout.setComponentAlignment(barMenu, Alignment.MIDDLE_RIGHT);

        return layout;
    }

    private static Component getLogo(){
        final HorizontalLayout logo = new HorizontalLayout();
        Image logoImage;
        try {

            ExternalResource resource = new ExternalResource(OrganisationFacade.companyLogosService
                            .findById(OrganisationUtil.getCompanyCode(),OrganisationUtil.getCompanyCode()).getUrl());
           logoImage = new Image(null,resource);
        }catch (Exception e) {
            FileResource resource = new FileResource(
                    new File("src/main/webapp/VAADIN/themes/dashboard/Kujali Logo.png"));
            logoImage= new Image(null,resource);
        }

        logoImage.addStyleName("logo-header-image");
        logoImage.setHeight(80.0f, Unit.PIXELS);
        logoImage.setWidth(40.0f, Unit.PERCENTAGE);
        logo.addComponent(logoImage);
        Responsive.makeResponsive(logo);
        return logo;
    }

    private static HorizontalLayout getBar(){
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(false);
        home.setCaption("Home");
        home.setDescription("Home Page");
        home.setIcon(FontAwesome.HOME);
        home.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        home.addStyleName(ValoTheme.BUTTON_SMALL);

        user.setCaption(GetUserCredentials.username());
        user.setDescription(GetUserCredentials.username());
        user.setIcon(FontAwesome.USER_MD);
        user.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        user.addStyleName(ValoTheme.BUTTON_SMALL);
        notify.addStyleName("notifications");
        notify.addStyleName("unread");
        notify.setIcon( new FileResource(
                new File("src/main/webapp/VAADIN/themes/dashboard/notifications.png")));
        notify.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        notify.addStyleName(ValoTheme.BUTTON_SMALL);

        layout.addComponent(home);
        layout.addComponent(notify);

        return layout;
    }

    @Override
    public void itemClick(ItemClickEvent event) {

    }
}