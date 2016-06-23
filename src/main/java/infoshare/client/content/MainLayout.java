package infoshare.client.content;

import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.client.content.home.HomeMenu;
import infoshare.client.content.home.Index;
import infoshare.client.footer.Footer;
import infoshare.client.header.Header;
import infoshare.client.sidebar.SideBar;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by hashcode on 2015/06/22.
 */
public class MainLayout extends VerticalLayout {

    private final Index main;
    public final String company = GetUserCredentials.getUser().getOrg();
    public final HorizontalSplitPanel content = new HorizontalSplitPanel();

    public MainLayout(Index main, Page page) {
        this.main = main;
        Responsive.makeResponsive(this);
        content.setMaxSplitPosition(25, Unit.PERCENTAGE);
        content.setLocked(true);
        content.setFirstComponent(new SideBar(this));
        content.setSecondComponent(new HomeMenu(this, "LANDING"));
        addComponent(new Header(this, page));
        addComponent(content);
        addComponent(new Footer());

    }


}
