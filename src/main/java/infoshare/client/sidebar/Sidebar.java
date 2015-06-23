package infoshare.client.sidebar;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.sidebar.trees.*;

/**
 * Created by hashcode on 2015/06/23.
 */
public class Sidebar extends Accordion implements ItemClickEvent.ItemClickListener {
    private final MainLayout main;

    private final String PASSWORD = "Manage PASSWORD";
    private final String USERS = "Manage USERS";
    private final String CONTENT = "Manage CONTENT";
    private final String EDIT = "Edit CONTENT";
    private final String PUBLISH = "Publish CONTENT";
    private final String ROLES = "Manage ROLES";
    private final String COURSES = "Manage COURSES";
    private final String SETUP = "System SETUP";


    public Sidebar(MainLayout main) {
        setSizeFull();
        this.main = main;
        setHeight("350px");


        VerticalLayout rawMenu = new VerticalLayout();
        ContentTree contentTree = new ContentTree(main);
        rawMenu.addComponent(contentTree);
        addTab(contentTree, CONTENT, null);

        VerticalLayout editMenu = new VerticalLayout();
        EditTree editTree = new EditTree(main);
        editMenu.addComponent(editTree);
        addTab(editTree, EDIT, null);

        VerticalLayout publishMenu = new VerticalLayout();
        PublishTree publishTree = new PublishTree(main);
        publishMenu.addComponent(publishTree);
        addTab(publishTree, PUBLISH, null);

        VerticalLayout courseMenu = new VerticalLayout();
        CourseTree courseTree = new CourseTree(main);
        courseMenu.addComponent(courseTree);
        addTab(courseTree, COURSES, null);

        VerticalLayout setupMenu = new VerticalLayout();
        SetupTree setupTree = new SetupTree(main);
        setupMenu.addComponent(setupTree);
        addTab(setupTree, SETUP, null);

        VerticalLayout rolesMenu = new VerticalLayout();
        RoleTree roleTree = new RoleTree(main);
        rolesMenu.addComponent(roleTree);
        addTab(roleTree, ROLES, null);


        VerticalLayout usersMenu = new VerticalLayout();
        UserTree userTree = new UserTree(main);
        usersMenu.addComponent(userTree);
        addTab(userTree, USERS, null);
        VerticalLayout changePasswordMenu = new VerticalLayout();
        PasswordTree passwordTree = new PasswordTree(main);
        changePasswordMenu.addComponent(passwordTree);
        addTab(passwordTree, PASSWORD, null);


    }

    @Override
    public void itemClick(ItemClickEvent itemClickEvent) {

    }
}
