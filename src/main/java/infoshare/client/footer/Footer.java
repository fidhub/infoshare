package infoshare.client.footer;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by hashcode on 2015/06/23.
 */
public class Footer extends VerticalLayout{

    public Footer() {
        setSizeFull();
        Component footer = footerPanel();
        this.addComponent(footer);

    }

    private Component footerPanel(){
        final HorizontalLayout footLayout = new HorizontalLayout();
        footLayout.setSpacing(true);
        footLayout.setWidth("100%");
        footLayout.setHeight("100%");

        final Panel footerPanel = new Panel();
        footerPanel.setWidth("100%");
        footerPanel.setHeight("70%");

        Component footerItems = footerItems();
        footLayout.addComponent(footerItems);

        Component centerItems = centerItems();
        footLayout.addComponent(centerItems);

        Component rightItems = rightIthems();
        footLayout.addComponent(rightItems);

        footLayout.setComponentAlignment(rightItems,Alignment.TOP_RIGHT);
        footLayout.setComponentAlignment(centerItems,Alignment.TOP_CENTER);
        footerPanel.setContent(footLayout);

        return footerPanel;
    }

    private Component footerComponents(){
        final HorizontalLayout down = new HorizontalLayout();
        down.setSpacing(true);
        down.addStyleName("footer-Panel");
       //down.setSizeUndefined();
        Responsive.makeResponsive(down);
        down.addComponent(footerComponents());

        return down;
    }

    private Component footerItems(){
        final HorizontalLayout footItems = new HorizontalLayout();
        footItems.setSpacing(true);
        footItems.addStyleName("items");
        Responsive.makeResponsive(footItems);
        //footItems.setSizeUndefined();

        footItems.addComponent(new Label("@2015 Care Givers, gov."));
        //final Link about = new Link("About Us", new ExternalResource("http://www.homeleigh.co.za/"));
        //final Link terms = new Link("Terms", new ExternalResource("http://www.homeleigh.co.za/"));
        final Link about = new Link("About Us", new com.vaadin.server.ExternalResource("http://www.homeleigh.co.za/"));
        final Link terms = new Link("Terms", new com.vaadin.server.ExternalResource("http://www.homeleigh.co.za/"));


        footItems.addComponent(terms);
        footItems.addComponent(about);

        return footItems;
    }

    private Component rightIthems(){
        final HorizontalLayout rightItms = new HorizontalLayout();
        rightItms.setSpacing(true);
        rightItms.addStyleName("rightItems");
        Responsive.makeResponsive(rightItms);
        //rightItms.setSizeUndefined();

        final Link contact = new Link("Contact Us", new ExternalResource("http://www.homeleigh.co.za/"));
        final Link gooleSite = new Link("Google Sites", new ExternalResource("http://www.nursingworld.org/"));

        rightItms.addComponent(contact);
        rightItms.addComponent(gooleSite);


        return rightItms;
    }

    private Component centerItems(){
        final HorizontalLayout CenterItems = new HorizontalLayout();
        CenterItems.setSpacing(true);
        CenterItems.addStyleName("centerItems");
        Responsive.makeResponsive(CenterItems);
        //CenterItems.setSizeUndefined();

        final Link home =new Link("Home",new ExternalResource("http://www.cput.ac.za"));
        home.setIcon(FontAwesome.HOME);
        home.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        home.addStyleName(ValoTheme.BUTTON_LARGE);

        CenterItems.addComponent(home);

        return CenterItems;
    }


}
