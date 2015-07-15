package infoshare.client.content.content.views;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.ContentMenu;
import infoshare.client.content.content.forms.PublishForm;
import infoshare.client.content.content.models.PublishModel;
import infoshare.client.content.content.tables.PublishTable;
import infoshare.domain.Content;
import infoshare.filterSearch.ContentFilter;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hashcode on 2015/06/24.
 */
public class PublishView extends VerticalLayout implements Button.ClickListener,
        Property.ValueChangeListener {
    @Autowired
    private ContentService contentService = new ContentServiceImp();

    private final MainLayout main;
    private final PublishTable table;
    private final PublishForm form;
    private final Window popUp ;
    private Button viewContent = new Button("View Content");
    private ContentFilter contentFilter = new ContentFilter();
    public PublishView( MainLayout mainApp) {

        this.main = mainApp;
        this.table = new PublishTable(main);
        this.form = new PublishForm();
        this.popUp = modelWindow();
        setSpacing(true);
        setSizeFull();
        addComponent(getLayout());
        addComponent(table);
        addListeners();
    }

    private HorizontalLayout getLayout(){
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(false);
        viewContent.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        viewContent.addStyleName(ValoTheme.BUTTON_SMALL);
        viewContent.setIcon(FontAwesome.EDIT);
        layout.addComponent(contentFilter.field);
        layout.addComponent(viewContent);

        return layout;
    }
    private void refreshContacts(String stringFilter ) {
        try {
            table.removeAllItems();
            for(Content content: contentFilter.findAll(stringFilter))
                  table.loadTable(content);
        }catch (Exception e){
        }
    }
    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
        final Property property = valueChangeEvent.getProperty();
        if (property == table) {
            viewContent.setVisible(true);
        }
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
            getHome();
        }
    }
    private Window modelWindow(){
        final Window popup = new Window("VIEW Content");
        popup.setWidth(80.0f,Unit.PERCENTAGE);
        popup.setHeight(90.0f, Unit.PERCENTAGE);
        popup.setContent(form);
        return popup;
    }
    private void ViewContentButton(){
        try {
            final Content content = contentService.find(table.getValue().toString());
            final PublishModel bean = getModel(content);
            form.binder.setItemDataSource(new BeanItem<>(bean));
            UI.getCurrent().addWindow(popUp);
            popUp.setModal(true);
        }catch (Exception e){
            Notification.show("Select the content you wanna view",
                    Notification.Type.HUMANIZED_MESSAGE);
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
    private void getHome() {
        main.content.setSecondComponent(new ContentMenu(main, "PUBLISHER"));
    }
    public void addListeners(){
        form.popUpCloseBtn.addClickListener((Button.ClickListener) this);
        viewContent.addClickListener((Button.ClickListener) this);
        table.addValueChangeListener((Property.ValueChangeListener) this);
        contentFilter.field.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent textChangeEvent) {
                refreshContacts(textChangeEvent.getText());
            }
        });
    }

}
