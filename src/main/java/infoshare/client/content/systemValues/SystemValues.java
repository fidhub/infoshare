package infoshare.client.content.systemValues;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.systemValues.views.CategoryView;
import infoshare.client.content.systemValues.views.ContentSourceView;
import infoshare.client.content.systemValues.views.ContentTypeView;
import infoshare.client.content.MainLayout;

/**
 * Created by codex on 2015/06/25.
 */
public class SystemValues extends VerticalLayout {

    private MainLayout main;
    private TabSheet tab;

    public SystemValues(MainLayout main, String selectedTab) {

        this.main = main;

        final VerticalLayout contentTypeView = new VerticalLayout();
        contentTypeView.setMargin(true);
        contentTypeView.addComponent(new ContentTypeView(main));

        final VerticalLayout contentSourceView = new VerticalLayout();
        contentSourceView.setMargin(true);
        contentSourceView.addComponent(new ContentSourceView(main));

        final VerticalLayout categoryView = new VerticalLayout();
        categoryView.setMargin(true);
        categoryView.addComponent(new CategoryView(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(contentTypeView, "Add Content Type", null);
        tab.addTab(contentSourceView, "Add content Source", null);
        tab.addTab(categoryView, "Add Content Category", null);

        if (selectedTab.equalsIgnoreCase("LANDING")) {
            tab.setSelectedTab(contentTypeView);
        }else if(selectedTab.equalsIgnoreCase("SOURCE")){
            tab.setSelectedTab(contentSourceView);
        }else if(selectedTab.equalsIgnoreCase("CATEGORY")){
            tab.setSelectedTab(categoryView);
        }

        addComponent(tab);

    }
}
