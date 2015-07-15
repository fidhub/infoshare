package infoshare.client.header;

import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.header.view.LandingHome;

/**
 * Created by codex on 2015/07/15.
 */
public class HeaderMenu  extends VerticalLayout{

    private MainLayout main;

    public HeaderMenu(MainLayout main) {

        this.main = main;
        final VerticalLayout header = new VerticalLayout();
        header.setMargin(true);
        header.addComponent(new LandingHome(main));

        addComponent(header);

    }
}
