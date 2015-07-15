package infoshare.client.content.courseSetup.tables;

import com.vaadin.ui.Table;
import infoshare.domain.Lesson;
import infoshare.services.courses.CourseService;
import infoshare.services.courses.Impl.CourseServiceImpl;

/**
 * Created by codex on 2015/07/09.
 */
public class LessonTable extends Table {

    private CourseService courseService = new CourseServiceImpl();

    public LessonTable() {
        setSizeFull();
        addContainerProperty("Lesson",String.class,null);
        addContainerProperty("Description ",String.class,null);

        setSelectable(true);
        setNullSelectionAllowed(false);
        setImmediate(true);
    }
    public void loadTable( String id ){
        try {
            removeAllItems();
            for (Lesson lesson : courseService.find(id).getLessons())
                addItem(new Object[]{
                        lesson.getLesson(),
                        lesson.getDescription()
                }, lesson.getId());
        }catch (Exception e){
        }
    }
}
