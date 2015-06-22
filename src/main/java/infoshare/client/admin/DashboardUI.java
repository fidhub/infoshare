package infoshare.client.admin;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import infoshare.App;
import infoshare.client.admin.event.DashboardEventBus;
import infoshare.client.admin.view.LoginView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hashcode on 2015/04/15.
 */
@Theme("dashboard")
@SpringUI(path = "/admin")
public class DashboardUI extends UI {
    private final DashboardEventBus dashboardEventbus = new DashboardEventBus();
    @Autowired
    App.MyService myService;

    @Override

    protected void init(VaadinRequest request) {
        Responsive.makeResponsive(this);
        setContent(new LoginView());

    }

    public static DashboardEventBus getDashboardEventbus() {
        return ((DashboardUI) getCurrent()).dashboardEventbus;
    }






}
