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

    private final String PASSWORD = "MANAGE PASSWORD";
    private final String CONTENT = "MANAGE CONTENT";
    private final String COURSES = "MANAGE COURSES";
    private final String SETUP = "SYSTEM SETUP";

    public static final String HOME_CONTENT = "HOME ";
    public static final String MANAGE_SYSTEM = "Manage SYSTEM";
    public static final String MANAGE_USERS = "Manage USERS";
    public static final String YOUR_PROFILE = "Your PROFILE";
    public static final String COMMON_SETTINGS = "Common SETTINGS";
    public static final String MANAGE_ORGANISATION = "Manage ORGANISATION";


    public SideBar(MainLayout main) {
        setSizeFull();
        this.main = main;
        addStyleName("accordion");
        setHeight("260px");



        VerticalLayout homeMenu = new VerticalLayout();
        HomeTree homeTree = new HomeTree(main);
        homeMenu.addComponent(homeTree);


        VerticalLayout profileMenu = new VerticalLayout();
        ProfileTree profileTree = new ProfileTree(main);
        profileMenu.addComponent(profileTree);

        VerticalLayout systemsMenu = new VerticalLayout();
        SystemsTree systemsTree = new SystemsTree(main);
        systemsMenu.addComponent(systemsTree);

        VerticalLayout usersMenu = new VerticalLayout();
        UsersTree usersTree = new UsersTree(main);
        usersMenu.addComponent(usersTree);

        VerticalLayout commonMenu = new VerticalLayout();
        CommonTree commonTree = new CommonTree(main);
        commonMenu.addComponent(commonTree);

        VerticalLayout companyMenu = new VerticalLayout();
        CompanyTree companyTree = new CompanyTree(main);
        companyMenu.addComponent(companyTree);

        VerticalLayout rawMenu = new VerticalLayout();
        ContentTree contentTree = new ContentTree(main);
        rawMenu.addComponent(contentTree);
        addTab(contentTree, CONTENT, null);

        VerticalLayout courseMenu = new VerticalLayout();
        CourseTree courseTree = new CourseTree(main);
        courseMenu.addComponent(courseTree);
        addTab(courseTree, COURSES, null);

        VerticalLayout setupMenu = new VerticalLayout();
        SetupTree setupTree = new SetupTree(main);
        setupMenu.addComponent(setupTree);
        addTab(setupTree, SETUP, null);

        VerticalLayout changePasswordMenu = new VerticalLayout();
        PasswordTree passwordTree = new PasswordTree(main);
        changePasswordMenu.addComponent(passwordTree);
        addTab(passwordTree, PASSWORD, null);









    }
    @Override
    public void itemClick(ItemClickEvent itemClickEvent) {

    }
}
