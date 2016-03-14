package infoshare.client.sidebar;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.VerticalLayout;
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
        setHeight("350px");

        //TODO OrganisationTree
        VerticalLayout companyMenu = new VerticalLayout();
        OrganisationTree organisationTree = new OrganisationTree(main);
        companyMenu.addComponent(organisationTree);
        addTab(companyMenu,MANAGE_ORGANISATION,null);

        //TODO CommonTree
        VerticalLayout commonMenu = new VerticalLayout();
        CommonTree commonTree = new CommonTree(main);
        commonMenu.addComponent(commonTree);
        addTab(commonMenu, COMMON_SETTINGS, null);

        //TODO ContentTree
        VerticalLayout rawMenu = new VerticalLayout();
        ContentTree contentTree = new ContentTree(main);
        rawMenu.addComponent(contentTree);
        addTab(contentTree, CONTENT, null);
        //TODO HomeTree
        //VerticalLayout homeMenu = new VerticalLayout();
        //HomeTree homeTree = new HomeTree(main);
        //homeMenu.addComponent(homeTree);
        //addTab(homeMenu, HOME_CONTENT, null);
        //TODO PasswordTree
       // VerticalLayout changePasswordMenu = new VerticalLayout();
       // PasswordTree passwordTree = new PasswordTree(main);
       // changePasswordMenu.addComponent(passwordTree);
       // addTab(passwordTree, PASSWORD, null);
        //TODO ProfileTree
        VerticalLayout profileMenu = new VerticalLayout();
        ProfileTree profileTree = new ProfileTree(main);
        profileMenu.addComponent(profileTree);
        addTab(profileTree,YOUR_PROFILE,null);
       /* //TODO SetupTree
        VerticalLayout setupMenu = new VerticalLayout();
        SetupTree setupTree = new SetupTree(main);
        setupMenu.addComponent(setupTree);
        //addTab(setupTree, SETUP, null);
        //TODO SystemsTree
        VerticalLayout systemsMenu = new VerticalLayout();
        SystemsTree systemsTree = new SystemsTree(main);
        systemsMenu.addComponent(systemsTree);
        //addTab(systemsMenu,COMMON_SETTINGS,null);*/
       //TODO UsersTree
        VerticalLayout usersMenu = new VerticalLayout();
        UsersTree usersTree = new UsersTree(main);
        usersMenu.addComponent(usersTree);
        addTab(usersMenu,MANAGE_USERS);

    }
    @Override
    public void itemClick(ItemClickEvent itemClickEvent) {

    }
}
