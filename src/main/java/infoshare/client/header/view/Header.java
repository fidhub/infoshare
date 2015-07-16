package infoshare.client.header.view;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.client.header.form.HeaderForm;
import infoshare.client.header.landing_page.LandingHome;
import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;

/**
 * Created by hashcode on 2015/06/23.
 */
public class Header extends VerticalLayout implements Button.ClickListener {

    private ContentService contentService = new ContentServiceImp();
    private MainLayout main ;
    private HeaderForm form;
    private Window  notifications;
    public Header(MainLayout main) {
        this.main = main;
        setSizeFull();
        setSpacing(true);
        form = new HeaderForm(main);
        addComponent(getHeaderPanel());
        addListener();
    }

    private Panel getHeaderPanel(){
        final Panel headerPanel = new Panel();
        headerPanel.setSizeFull();
        headerPanel.setHeight(150.0f,Unit.PIXELS);
        headerPanel.addStyleName(ChameleonTheme.PANEL_LIGHT);
        headerPanel.addStyleName(ChameleonTheme.PANEL_BUBBLE);
        headerPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);

        headerPanel.setContent(form);
        return headerPanel;
    }


    @Override
    public void buttonClick(ClickEvent clickEvent) {
        Button source = clickEvent.getButton();
        if(source == form.home){
            main.content.setSecondComponent(new LandingHome(main));
        }else if(source == form.notify){
                if (notifications != null && notifications.getUI() != null)
                    notifications.close();
                 else {
                    buildNotifications(clickEvent);
                    getUI().addWindow(notifications);
                    notifications.focus();
                }

        }
    }
    private void buildNotifications( ClickEvent event) {
        notifications = new Window("notifications");
        VerticalLayout layout = new VerticalLayout();
        notifications.setWidth("300px");
        notifications.setResizable(false);
        notifications.setClosable(false);
        notifications.setDraggable(false);
        notifications.setPositionX(event.getClientX() - 200);
        notifications.setPositionY(event.getClientY() + 25);
        notifications.addStyleName("notifications");
        notifications.setCloseShortcut(ShortcutAction.KeyCode.ESCAPE, null);

        Table t = new Table();
        Responsive.makeResponsive(t);
        t.setWidth(100.0f,Unit.PERCENTAGE);
        t.addContainerProperty("",Label.class,null);
        t.addStyleName(ValoTheme.TABLE_BORDERLESS);
        t.addStyleName(ValoTheme.TABLE_NO_STRIPES);
        t.addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        t.addStyleName(ValoTheme.TABLE_SMALL);
        t.setSelectable(true);
        for (Content content : contentService.findAll()){
            t.addItem(new Object[]{new Label(
                    "<b>"
                            + content.getTitle()
                            + " created a new report</b><br><span>25 minutes ago</span><br>"
                            + content.getCreator(), ContentMode.HTML)
            }, content.getId());
        }

        notifications.setContent(t);
    }

    private void addListener(){
        form.home.addClickListener((Button.ClickListener)this);
        form.notify.addClickListener((Button.ClickListener)this);
        form.user.addClickListener((Button.ClickListener)this);
    }

}