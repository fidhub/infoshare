package infoshare.client.header.Table;


import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.client.header.view.Header;
import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;

import java.util.Date;


/**
 * Created by user9 on 2015/08/13.
 */
public class NotificationTable extends Table{
    private ContentService contentService = new ContentServiceImp();

    public NotificationTable() {
        setSizeFull();
        setHeight(360.0f, Unit.PIXELS);
        addContainerProperty("Notifications", Label.class, null);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
       for (Content content: contentService.findAll()) {
           if(content.getContentType().equalsIgnoreCase("raw"))
                loadTable(content);
       }

        setSelectable(true);
        setNullSelectionAllowed(false);
        setImmediate(true);
        setNullSelectionAllowed(false);
    }
    private void loadTable(Content content){
        try {
          //  Header.refreshNotification();
                addItem(new Object[]{new Label(
                        "<b>" + content.getCreator().toUpperCase()
                                + "</b> created a new tip <br><span><i>"
                                + differInTime(content.getDateCreated()) + "</i></span><br>"
                                + "<b> Title: </b><i>" + content.getTitle() + "</i><br>", ContentMode.HTML)
                }, content.getId());
        }catch (Exception e) {}

    }
    private String differInTime(Date dateCreated){
        Date date1 = new Date();

        long diff = date1.getTime() - dateCreated.getTime();
        long diffSeconds = diff    / 1000 % 60;
        long diffMinutes = diff   / (60 * 1000) % 60;
        long diffHours   = diff  / (60 * 60 * 1000) % 24;
        long diffDays    = diff / (24 * 60 * 60 * 1000);

        if(diffDays > 0)
            return diffDays +" Days ago";
        if(diffHours > 0)
            return diffHours +" Hours ago";
        else if(diffMinutes > 0)
            return diffMinutes + " Minutes ago";
        else
            return diffSeconds + " seconds ago";
    }

}
