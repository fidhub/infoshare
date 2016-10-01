package infoshare.viewUI.container.common.systemValues;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.viewUI.container.common.systemValues.views.CategoryView;
import infoshare.viewUI.container.common.systemValues.views.ContentSourceView;
import infoshare.viewUI.container.common.systemValues.views.ContentTypeView;
import infoshare.viewUI.container.MainLayout;

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

        tab.addTab(contentTypeView, "Content Type", null);
       // tab.addTab(contentSourceView, "Content Source", null);
        tab.addTab(categoryView, "Content Category", null);

        if (selectedTab.equalsIgnoreCase("LANDING")) {
            tab.setSelectedTab(contentTypeView);
        }/*else if(selectedTab.equalsIgnoreCase("SOURCE")){
            tab.setSelectedTab(contentSourceView);
        }*/else if(selectedTab.equalsIgnoreCase("CATEGORY")){
            tab.setSelectedTab(categoryView);
        }

        addComponent(tab);

    }
}
