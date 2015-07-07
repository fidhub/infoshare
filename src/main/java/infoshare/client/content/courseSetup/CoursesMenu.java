package infoshare.client.content.courseSetup;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.courseSetup.views.CourseView;
import infoshare.client.content.courseSetup.views.LessonView;

/**
 * Created by hashcode on 2015/06/23.
 */
public class CoursesMenu extends VerticalLayout {
    private MainLayout main;
    private TabSheet tab;

    public CoursesMenu(MainLayout main,String selectedTab) {
        this.main = main;


        final VerticalLayout addLesson = new VerticalLayout();
        addLesson.setMargin(true);
        addLesson.addComponent(new LessonView(main));

        final VerticalLayout addCourse = new VerticalLayout();
        addCourse.setMargin(true);
        addCourse.addComponent(new CourseView(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(addLesson, "Add Lessons", null);
        tab.addTab(addCourse, "Add Courses", null);

        if (selectedTab.equalsIgnoreCase("LANDING")) {
            tab.setSelectedTab(addLesson);
        }else if(selectedTab.equalsIgnoreCase("addCourse")){
            tab.setSelectedTab(addCourse);
        }
        addComponent(tab);
    }
}
