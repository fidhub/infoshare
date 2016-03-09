package infoshare.client.home;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import infoshare.client.content.MainLayout;

/**
 * Created by hashcode on 2015/06/22.
 */
@Theme("dashboard")
@SpringUI(path = "")
public class Index extends UI {


    @Override
    protected void init(VaadinRequest request) {
//        getPage().setTitle("Login Page");
        Responsive.makeResponsive(this);
        setContent(new MainLayout(this, getPage()));
        setOverlayContainerLabel(" Events");

    }



}
