package infoshare.client.content.ContentType.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.ContentType.forms.ContentTypeForm;
import infoshare.client.content.ContentType.tables.ContentTypeTable;
import infoshare.client.content.MainLayout;
import infoshare.services.ContentType.ContentTypeService;
import infoshare.services.ContentType.Impl.ContentTypeServiceImpl;

/**
 * Created by codex on 2015/06/25.
 */
public class ContentTypeView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener {

    private ContentTypeService contentTypeService = new ContentTypeServiceImpl();

    private final MainLayout main;
    private final ContentTypeForm form;
    private final ContentTypeTable table;

    public ContentTypeView(MainLayout mainApp) {
        this.main = mainApp;
        this.form = new ContentTypeForm();
        this.table = new ContentTypeTable(main);
        setSizeFull();
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
