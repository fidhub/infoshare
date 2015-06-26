package infoshare.client.content.content.views;

import com.vaadin.data.Property;
import com.vaadin.ui.*;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.forms.PublishForm;
import infoshare.client.content.content.tables.PublishTable;
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

    public TextArea textArea = new TextArea();
    public Button popUpCloseBtn = new Button();


    public PublishView( MainLayout mainApp) {
        this.main = mainApp;
        this.table = new PublishTable(main);
        this.form = new PublishForm();
        this.popUp = modelWindow();
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }
    private Window modelWindow(){
        final Window popup = new Window("Edit");
        popup.setWidth(80.0f,Unit.PERCENTAGE);
        popup.setHeight(90.0f, Unit.PERCENTAGE);
        final  FormLayout form = new FormLayout();

        textArea.setImmediate(true);
        textArea.setWidth(98.0f, Unit.PERCENTAGE);
        textArea.setHeight(400.0f, Unit.PIXELS);
        textArea.setEnabled(false);
        textArea.setWordwrap(true);
        form.addComponent(textArea);
        form.addComponent(popUpButtons());
        popup.setContent(form);

        return popup;
    }

    public HorizontalLayout popUpButtons(){
        final HorizontalLayout buttons = new HorizontalLayout();
        popUpCloseBtn.setCaption("Close");
        buttons.addComponent(popUpCloseBtn);

        return buttons;
    }
    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        final Button source = clickEvent.getButton();
        if(source==form.viewContent){
            ViewContentButton();
        }else if (source ==popUpCloseBtn){
            popUp.setModal(false);
            UI.getCurrent().removeWindow(popUp);
        }

    }

    private void ViewContentButton(){
        try {
            Object rowId = table.getValue();
            textArea.setValue(contentService.find(rowId + "").getContent().toString());
            UI.getCurrent().addWindow(popUp);
            popUp.setModal(true);
        }catch (Exception e){
            Notification.show("Select the content you wanna Edit",
                    Notification.Type.TRAY_NOTIFICATION);
        }
    }
    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
    }

    public void addListeners(){
        form.viewContent.addClickListener((Button.ClickListener) this);
        popUpCloseBtn.addClickListener((Button.ClickListener) this);
    }
}
