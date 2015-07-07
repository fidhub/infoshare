package infoshare.client.content.courseSetup.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;

/**
 * Created by codex on 2015/07/07.
 */
public class LessonView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    private MainLayout main ;

    public LessonView(MainLayout main) {
        this.main = main;

    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
