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
    private final String CONTENT = "Manage CONTENT";
    private final String COURSES = "Manage COURSES";
    private final String SETUP = "System SETUP";


    public Sidebar(MainLayout main) {
        setSizeFull();
        this.main = main;
        setHeight("250px");

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
