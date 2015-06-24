package infoshare.client.home;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import infoshare.App;
import infoshare.client.content.content.tables.EditTable;
import infoshare.client.content.login.LoginView;
import infoshare.client.header.Header;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hashcode on 2015/06/22.
 */
@Theme("dashboard")
@SpringUI(path = "")
public class Index extends UI {

    @Autowired
    App.MyService myService;

    @Override

    protected void init(VaadinRequest request) {
        getPage().setTitle("Login Page");
        Responsive.makeResponsive(this);
        setContent(new LoginView(this));
        setOverlayContainerLabel(" Events");

    }


}
