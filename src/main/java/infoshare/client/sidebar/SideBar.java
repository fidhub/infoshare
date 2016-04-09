package infoshare.client.sidebar;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.app.util.security.RolesValues;
import infoshare.client.content.MainLayout;
import infoshare.client.sidebar.trees.*;

/**
 * Created by hashcode on 2015/06/23.
 */
public class SideBar extends Accordion implements ItemClickEvent.ItemClickListener {
    private final MainLayout main;

    private final String CONTENT = "MANAGE CONTENT";
    public static final String MANAGE_USERS = "MANAGE USERS";
    public static final String YOUR_PROFILE = "YOUR PROFILE";
    public static final String COMMON_SETTINGS = "COMMON SETTINGS";
    public static final String MANAGE_ORGANISATION = "MANAGE ORGANISATION";


    public SideBar(MainLayout main) {
        setSizeFull();
        this.main = main;
        this.addStyleName("accordion");
        setHeight("250px");
        //TODO ContentTree
        VerticalLayout rawMenu = new VerticalLayout();
        ContentTree contentTree = new ContentTree(main);
        rawMenu.addComponent(contentTree);
        addTab(contentTree, CONTENT, null);
        //TODO PROFILE
        VerticalLayout profileMenu = new VerticalLayout();
        ProfileTree profileTree = new ProfileTree(main);
        profileMenu.addComponent(profileTree);
        addTab(profileTree, YOUR_PROFILE, null);


        //TODO UsersTree
        VerticalLayout usersMenu = new VerticalLayout();
        UsersTree usersTree = new UsersTree(main);
        usersMenu.addComponent(usersTree);
        //TODO OrganisationTree
        VerticalLayout companyMenu = new VerticalLayout();
        OrganisationTree organisationTree = new OrganisationTree(main);
        companyMenu.addComponent(organisationTree);
        //TODO CommonTree
        VerticalLayout commonMenu = new VerticalLayout();
        CommonTree commonTree = new CommonTree(main);
        commonMenu.addComponent(commonTree);
        //TODO DISABLE IF NOT ROLE_ADMIN
        if (GetUserCredentials.isUserWithRole(RolesValues.ROLE_ADMIN.name())) {
            setHeight("330px");
            addTab(companyMenu, MANAGE_ORGANISATION, null);
            addTab(usersMenu, MANAGE_USERS);
            addTab(commonMenu, COMMON_SETTINGS, null);
        }
        //TODO DISABLE IF NOT ROLE_ADMIN
        if (GetUserCredentials.isUserWithRole(RolesValues.ORG_ADMIN.name())) {
            addTab(usersMenu, MANAGE_USERS);
        }
    }
    @Override
    public void itemClick(ItemClickEvent itemClickEvent) {

    }
}
