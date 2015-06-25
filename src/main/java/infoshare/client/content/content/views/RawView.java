package infoshare.client.content.content.views;

import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.tables.EditTable;
import infoshare.client.content.content.tables.RawTable;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hashcode on 2015/06/24.
 */
public class RawView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener{
    @Autowired
    private ContentService contentService = new ContentServiceImp();

    private final MainLayout main;
    private final RawTable table;

    public RichTextArea textEditor = new RichTextArea();
    public Button popUpSaveBtn = new Button();
    public Button popUpCancelBtn = new Button();


    public RawView( MainLayout mainApp) {
        this.main = mainApp;
        this.table = new RawTable(main);
        setSizeFull();
        addComponent(table);
        addListeners();
    }
    private Window modelWindow(){
        final Window popup = new Window("Edit");
        popup.setWidth(80.0f,Unit.PERCENTAGE);
        popup.setHeight(90.0f, Unit.PERCENTAGE);
        final FormLayout form = new FormLayout();

        final Label dateCreatedLbl = new Label("date Updated");
        dateCreatedLbl.addStyleName(ValoTheme.LABEL_COLORED);

        form.addComponent(dateCreatedLbl);
        textEditor.setImmediate(true);
        textEditor.setWidth(98.0f,Unit.PERCENTAGE);
        textEditor.setHeight(400.0f, Unit.PIXELS);

        form.addComponent(textEditor);
        form.addComponent(popUpButtons());

        popup.setContent(form);
        popup.setModal(true);
        UI.getCurrent().addWindow(popup);
        return popup;
    }

    public HorizontalLayout popUpButtons(){
        final HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);

        popUpSaveBtn.setCaption("update");
        popUpSaveBtn.setIcon(FontAwesome.SAVE);
        popUpSaveBtn.setStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        popUpCancelBtn.setCaption("Cancel");
        popUpCancelBtn.setIcon(FontAwesome.CROSSHAIRS);
        popUpCancelBtn.setStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        buttons.addComponent(popUpSaveBtn);
        buttons.addComponent(popUpCancelBtn);

        return buttons;
    }
    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        final Button source = clickEvent.getButton();
        if(source==table.editBtn){
            modelWindow();
        }else if (source ==popUpSaveBtn){
            UI.getCurrent().removeWindow( modelWindow());
            Notification.show("The button was clicked",
                    Notification.Type.TRAY_NOTIFICATION);
        }else if (source ==popUpCancelBtn){
            UI.getCurrent().removeWindow(modelWindow());
        }

    }
    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
    }

    public void addListeners(){
        table.editBtn.addClickListener((Button.ClickListener)this);
        table.deleteBtn.addClickListener((Button.ClickListener) this);
        popUpSaveBtn.addClickListener((Button.ClickListener) this);
        popUpCancelBtn.addClickListener((Button.ClickListener) this);
    }
}
