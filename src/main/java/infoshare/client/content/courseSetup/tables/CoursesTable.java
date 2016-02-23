package infoshare.client.content.courseSetup.tables;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.domain.Course;
import infoshare.services.courses.CourseService;
import infoshare.services.courses.Impl.Services;

/**
 * Created by codex on 2015/07/07.
 */
public class CoursesTable extends Table {

    private CourseService courseService = new Services();
    public CoursesTable() {
        setSizeFull();
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Course level", String.class, null);
        addContainerProperty("Description",String.class,null);

        for(Course course: courseService.findAll()){
            addItem(new Object[]{
                    course.getCourseLevel(),
                    course.getCourseDescription()
            },course.getId());
        }
        setSelectable(true);
        setImmediate(true);
        setNullSelectionAllowed(false);
    }
}
