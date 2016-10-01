package infoshare.viewUI.container.content;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.app.util.security.RolesValues;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.content.views.DisabledContentView;
import infoshare.viewUI.container.content.views.EditView;
import infoshare.viewUI.container.content.views.PublishView;
import infoshare.viewUI.container.content.views.RawView;

/**
 * Created by hashcode on 2015/06/23.
 */
public class ContentMenu extends VerticalLayout {
    private MainLayout main;
    private TabSheet tab;

    public ContentMenu(MainLayout mainApp,String selectedTab)
    {
        this.main = mainApp;
        final VerticalLayout editorView = new VerticalLayout();
        editorView.setMargin(true);
        editorView.addComponent(new EditView(main));

        final VerticalLayout publisherView = new VerticalLayout();
        publisherView.setMargin(true);
        publisherView.addComponent(new PublishView(main));

        final VerticalLayout rawView = new VerticalLayout();
        rawView.setMargin(true);
        rawView.addComponent(new RawView(main));

        final VerticalLayout disabledContentView = new VerticalLayout();
        disabledContentView.setMargin(true);
        disabledContentView.addComponent(new DisabledContentView(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(rawView,"Raw Content",null);
        if (!GetUserCredentials.isUserWithRole(RolesValues.ROLE_EDITOR.name())) {
            tab.addTab(editorView, "Edited Content", null);
            tab.addTab(publisherView, "Published Content", null);
            tab.addTab(disabledContentView, "Deleted Content", null);
        }


        if(selectedTab.equalsIgnoreCase("LANDING")){
            tab.setSelectedTab(rawView);
        }else if(selectedTab.equalsIgnoreCase("EDITOR")){
            tab.setSelectedTab(editorView);
        }else if(selectedTab.equalsIgnoreCase("PUBLISHER")){
            tab.setSelectedTab(publisherView);
        }else if(selectedTab.equalsIgnoreCase("Deleted")){
            tab.setSelectedTab(disabledContentView);
        }
        addComponent(tab);
    }
}
