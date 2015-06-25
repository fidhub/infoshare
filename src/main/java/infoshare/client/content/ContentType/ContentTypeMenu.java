package infoshare.client.content.ContentType;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.ContentType.views.ContentTypeView;
import infoshare.client.content.MainLayout;

/**
 * Created by codex on 2015/06/25.
 */
public class ContentTypeMenu extends VerticalLayout {

    private MainLayout main;
    private TabSheet tab;

    public ContentTypeMenu(MainLayout main,String selectedTab) {

        this.main = main;

        final VerticalLayout contentTypeView = new VerticalLayout();
        contentTypeView.setMargin(true);
        contentTypeView.addComponent(new ContentTypeView(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(contentTypeView, "Content Type", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(contentTypeView);
        }

        addComponent(tab);

    }
}
