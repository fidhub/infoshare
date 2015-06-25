package infoshare.client.content.content.views;

import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.tables.EditTable;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hashcode on 2015/06/24.
 */
public class EditView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener{
    @Autowired
    private ContentService contentService = new ContentServiceImp();

    private final MainLayout main;
    private final EditTable table;
    private RichTextArea textEditor = new RichTextArea();
    private Button popUpSavebtn = new Button();
    private Button popUpCancelbtn = new Button();

   public EditView( MainLayout mainApp) {
       this.main = mainApp;
       this.table = new EditTable(main);
       setSizeFull();
       addComponent(table);
       addListeners();
   }
    private Window modelWindow(){
        final Window popup = new Window("Edit");
        popup.setWidth(80.0f,Unit.PERCENTAGE);
        popup.setHeight(90.0f, Unit.PERCENTAGE);
        final  FormLayout form = new FormLayout();

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
    public Component popUpButtons(){
        final HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);

        popUpSavebtn.setCaption("update");
        popUpCancelbtn.setCaption("Cancel");

        buttons.addComponent(popUpSavebtn);
        buttons.addComponent(popUpCancelbtn);

        return buttons;
    }
   @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
       final Button source = clickEvent.getButton();
       if(source==table.editBtn){
         modelWindow();
       }else if (source ==popUpSavebtn){
           UI.getCurrent().removeWindow( modelWindow());
           Notification.show("The button was clicked",
                   Notification.Type.TRAY_NOTIFICATION);
       }else if (source ==popUpCancelbtn){
           UI.getCurrent().removeWindow( modelWindow());
       }

    }
    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
    }

    public void addListeners(){
        table.editBtn.addClickListener((Button.ClickListener)this);
        table.deleteBtn.addClickListener((Button.ClickListener) this);
        popUpSavebtn.addClickListener((Button.ClickListener) this);
    }

}
