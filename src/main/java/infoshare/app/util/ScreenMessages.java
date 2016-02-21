package infoshare.app.util;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

/**
 * Created by hashcode on 2015/12/04.
 */
public class ScreenMessages {

    public static Notification getMessage(String message) {
        Notification notif = new Notification(
                "Message: ",
                message, Notification.Type.ERROR_MESSAGE);
        notif.setDelayMsec(2000);
        notif.setPosition(Position.MIDDLE_CENTER);
        notif.show(Page.getCurrent());
        return notif;
    }


}
