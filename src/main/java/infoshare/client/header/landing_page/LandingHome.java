package infoshare.client.header.landing_page;

import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;

/**
 * Created by codex on 2015/07/15.
 */
public class LandingHome extends Panel {

    private MainLayout main;
    public LandingHome(MainLayout main) {
        this.main = main;
        setSizeFull();
        addStyleName(ValoTheme.PANEL_SCROLL_INDICATOR);
        addStyleName(ValoTheme.PANEL_BORDERLESS);
        setHeight(500.0f, Unit.PIXELS);

    }
}
