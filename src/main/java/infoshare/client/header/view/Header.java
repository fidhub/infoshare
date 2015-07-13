package infoshare.client.header.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.client.header.form.HeaderForm;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;

/**
 * Created by hashcode on 2015/06/23.
 */
public class Header extends VerticalLayout implements
        Button.ClickListener {

    private ContentService content = new ContentServiceImp();
    private MainLayout main ;
    private HeaderForm form;
    public Header() {
        setSizeFull();
        setSpacing(true);
        form = new HeaderForm();
        addComponent(getHeaderPanel());
    }

    private Panel getHeaderPanel(){
        final Panel headerPanel = new Panel();
        headerPanel.setSizeFull();
        headerPanel.setHeight(150.0f,Unit.PIXELS);
        headerPanel.addStyleName(ChameleonTheme.PANEL_LIGHT);
        headerPanel.addStyleName(ChameleonTheme.PANEL_BUBBLE);
        headerPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);

        headerPanel.setContent(form);
        return headerPanel;
    }
    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        final Button source = clickEvent.getButton();

    }

}