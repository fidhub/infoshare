package infoshare.client.header.view;

import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;

/**
 * Created by codex on 2015/07/15.
 */
public class LandingHome extends Panel {

    private final MainLayout main;
    public LandingHome( MainLayout main) {
        this.main = main;
        setSizeFull();
       addStyleName(ValoTheme.PANEL_WELL);
       addStyleName(ValoTheme.PANEL_BORDERLESS);
       setHeight(400.0f, Unit.PIXELS);

    }
}
