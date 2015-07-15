package infoshare.client.sidebar.trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import infoshare.client.content.MainLayout;
import infoshare.client.content.courseSetup.CoursesMenu;

/**
 * Created by hashcode on 2015/06/23.
 */
public class CourseTree extends Tree implements ItemClickEvent.ItemClickListener {
    private final MainLayout main;
    private static final Object COURSES="Course";
    private static final String LANDING_TAB = "LANDING";

    public CourseTree(MainLayout main) {
        this.main = main;
        addItem(COURSES);
        //Add Listerners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        if (COURSES.equals(event.getItemId())) {
            main.content.setSecondComponent(new CoursesMenu(main, LANDING_TAB));
        }


    }
}
