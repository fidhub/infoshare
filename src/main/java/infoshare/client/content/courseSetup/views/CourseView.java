package infoshare.client.content.courseSetup.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.courseSetup.forms.CoursesForm;
import infoshare.client.content.courseSetup.tables.CoursesTable;

/**
 * Created by codex on 2015/07/07.
 */
public class CourseView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    private final CoursesForm form;
    private final CoursesTable table;

    public CourseView(MainLayout main) {
        this.main = main;
        form = new CoursesForm();
        table = new CoursesTable();
        addComponent(form);
        addComponent(table);

    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
