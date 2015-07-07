package infoshare.client.content.courseSetup.tables;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Table;

/**
 * Created by codex on 2015/07/07.
 */
public class CoursesTable extends Table {

    public CoursesTable() {
        setSizeFull();
        Responsive.makeResponsive(this);
        addContainerProperty("Course level", String.class, null);
        addContainerProperty("Description",String.class,null);
    }
}
