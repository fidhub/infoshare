package infoshare.client.content;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
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
    private final VerticalLayout layout = new VerticalLayout();
    public final HorizontalSplitPanel content = new HorizontalSplitPanel();

    public MainLayout(Index main) {

        this.main = main;
        content.setMaxSplitPosition(20, Unit.PERCENTAGE);
        content.setLocked(true);
        content.setFirstComponent(new Sidebar(this));
        content.setSecondComponent(new Button("This Landing Page"));

        addComponent(new Header());
        addComponent(content);
        addComponent(new Footer());

    }

}
