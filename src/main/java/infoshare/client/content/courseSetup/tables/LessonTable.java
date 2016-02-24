package infoshare.client.content.courseSetup.tables;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.domain.Lesson;
import infoshare.services.courses.CourseService;
import infoshare.services.courses.Impl.Services;

/**
 * Created by codex on 2015/07/09.
 */
public  class LessonTable extends Table {

    private CourseService courseService = new Services();

    public LessonTable() {
        setSizeFull();
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Lesson",String.class,null);
        addContainerProperty("Description ",String.class,null);

        setSelectable(true);
        setNullSelectionAllowed(false);
        setImmediate(true);
    }
    public void loadTable( String id ){
        try {
            removeAllItems();
            for (Lesson lesson : courseService.findById(id).getLessons())
                addItem(new Object[]{
                        lesson.getLesson(),
                        lesson.getDescription()
                }, lesson.getId());
        }catch (Exception e){
        }
    }
}
