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
import infoshare.client.content.content.models.ContentModel;
import infoshare.client.content.content.tables.PublishTable;
import infoshare.domain.PublishedContent;
import infoshare.filterSearch.PublishedContentFilter;
import infoshare.services.PublishedContent.Impl.PublishedContentServiceImpl;
import infoshare.services.PublishedContent.PublishedContentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/24.
 */
public class PublishView extends VerticalLayout implements Button.ClickListener,
        Property.ValueChangeListener {
    @Autowired
    private PublishedContentService publishedContentService = new PublishedContentServiceImpl();

    private final MainLayout main;
    private final PublishTable table;
    private final PublishForm form;
    private final Window popUp ;
    private Button viewContent = new Button("View Content");
    private PublishedContentFilter publishedContentFilter = new PublishedContentFilter();
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
        layout.addComponent(publishedContentFilter.field);
        layout.addComponent(viewContent);

        return layout;
    }

    private void refreshContacts(String stringFilter ) {
        try {
            table.removeAllItems();
            publishedContentFilter.findAll(stringFilter)
                    .stream()
                    .filter(cont ->cont.getStatus().equalsIgnoreCase("Published"))
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont ->cont.getState().equalsIgnoreCase("active"))
                    .collect(Collectors.toList())
                    .forEach(table::loadTable);
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
        final Window popup = new Window();
        popup.setWidth(80.0f,Unit.PERCENTAGE);
        popup.setClosable(false);
        popup.setResizable(false);
        popup.setContent(form);
        return popup;
    }

    private void ViewContentButton(){
        try {
            final PublishedContent publishedContent = publishedContentService.find(table.getValue().toString());
            final ContentModel bean = getModel(publishedContent);
            form.binder.setItemDataSource(new BeanItem<>(bean));
            UI.getCurrent().addWindow(popUp);
            popUp.setModal(true);
        }catch (Exception e){
            Notification.show("Select the content you wanna view",
                    Notification.Type.HUMANIZED_MESSAGE);
        }
    }

    private ContentModel getModel(PublishedContent val) {
        final ContentModel model = new ContentModel();
        final PublishedContent publishedContent = publishedContentService.find(val.getId());
        model.setTitle(publishedContent.getTitle());
        model.setCategory(publishedContent.getCategory());
        model.setCreator(publishedContent.getCreator());
        model.setContent(publishedContent.getContent());
        model.setContentType(publishedContent.getContentType());
        model.setSource(publishedContent.getSource());
        return model;
    }

    private void getHome() {
        main.content.setSecondComponent(new ContentMenu(main, "PUBLISHER"));
    }

    public void addListeners(){
        form.popUpCloseBtn.addClickListener((Button.ClickListener) this);
        viewContent.addClickListener((Button.ClickListener) this);
        table.addValueChangeListener((Property.ValueChangeListener) this);
        publishedContentFilter.field.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent textChangeEvent) {
                refreshContacts(textChangeEvent.getText());
            }
        });
    }

}
