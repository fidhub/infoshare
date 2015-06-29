package infoshare.client.content.systemValues.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;

/**
 * Created by codex on 2015/06/26.
 */
public class CategoryView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    private final MainLayout main;
    public CategoryView(MainLayout mainApp) {
        this.main = mainApp;
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

    }

    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
