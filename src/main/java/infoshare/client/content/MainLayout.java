package infoshare.client.content;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.footer.Footer;
import infoshare.client.header.Header;
import infoshare.client.home.Index;
import infoshare.client.sidebar.Sidebar;

/**
 * Created by hashcode on 2015/06/22.
 */
public class MainLayout extends VerticalLayout{

    private final Index main;
    public final HorizontalSplitPanel content = new HorizontalSplitPanel();

    public MainLayout(Index main) {

        this.main = main;
        content.setMaxSplitPosition(20, Unit.PERCENTAGE);
        content.setLocked(true);
        content.setFirstComponent(new Sidebar(this));
        Panel landingPanel = new Panel();
        landingPanel.setSizeFull();
        landingPanel.setHeight(500.0f,Unit.PIXELS);

        content.setSecondComponent(landingPanel);

        addComponent(new Header());
        addComponent(content);
        addComponent(new Footer());

    }

}
