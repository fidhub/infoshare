package infoshare.client.content.content.views;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.forms.PublishForm;
import infoshare.client.content.content.models.PublishModel;
import infoshare.client.content.content.tables.PublishTable;
import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hashcode on 2015/06/24.
 */
public class PublishView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener{
    @Autowired
    private ContentService contentService = new ContentServiceImp();

    private final MainLayout main;
    private final PublishTable table;
    private final PublishForm form;
    private final Window popUp ;
    private Button viewContent = new Button("View Content");

    public PublishView( MainLayout mainApp) {
        this.main = mainApp;
        this.table = new PublishTable(main);
        this.form = new PublishForm();
        this.popUp = modelWindow();
        setSizeFull();
        setSpacing(true);
        addComponent(viewContent);
        addComponent(table);
        addListeners();
    }
    private Window modelWindow(){
        final Window popup = new Window("VIEW Content");
        popup.setWidth(80.0f,Unit.PERCENTAGE);
        popup.setHeight(90.0f, Unit.PERCENTAGE);
        popup.setContent(form);
        return popup;
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        final Button source = clickEvent.getButton();
        if(source==viewContent){
            ViewContentButton();
        }else if (source ==form.popUpCloseBtn){
            popUp.setModal(false);
            UI.getCurrent().removeWindow(popUp);
            table.setValue(null);
        }

    }

    private void ViewContentButton(){
        try {
            Object rowId = table.getValue();
            form.textArea.setValue(contentService.find(rowId + "").getContent().toString());
            UI.getCurrent().addWindow(popUp);
            popUp.setModal(true);
        }catch (Exception e){
            Notification.show("Select the content you wanna view",
                    Notification.Type.HUMANIZED_MESSAGE);
        }
    }
    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
        final Property property = valueChangeEvent.getProperty();
        if (property == table) {
            final Content content = contentService.find(table.getValue().toString());
            final PublishModel bean = getModel(content);
            form.binder.setItemDataSource(new BeanItem<>(bean));
        }
    }
    private PublishModel getModel(Content val) {
        final PublishModel model = new PublishModel();
        final Content content = contentService.find(val.getId());
        model.setTitle(content.getTitle());
        model.setId(content.getId());
        model.setCategory(content.getCategory());
        model.setCreator(content.getCreator());
        model.setContent(content.getContent());
        model.setContentType(content.getContentType());
        model.setSource(content.getSource());
        return model;
    }

    public void addListeners(){
        form.popUpCloseBtn.addClickListener((Button.ClickListener) this);
        viewContent.addClickListener((Button.ClickListener) this);
    }
}
