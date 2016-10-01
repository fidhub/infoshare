package infoshare.viewUI.container;

import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.viewUI.container.home.HomeMenu;
import infoshare.viewUI.container.home.Index;
import infoshare.viewUI.footer.Footer;
import infoshare.viewUI.header.Header;
import infoshare.viewUI.sidebar.SideBar;

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
