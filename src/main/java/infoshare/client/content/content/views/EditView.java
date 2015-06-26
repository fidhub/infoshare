package infoshare.client.content.content.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.forms.EditForm;
import infoshare.client.content.content.models.EditModel;
import infoshare.client.content.content.tables.EditTable;
import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.Object;

/**
 * Created by hashcode on 2015/06/24.
 */
public class EditView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener{
    @Autowired
    private ContentService contentService = new ContentServiceImp();


    private final MainLayout main;
    private final EditTable table;
    private final EditForm form;

    public RichTextArea textEditor = new RichTextArea();
    public Button popUpSaveBtn = new Button();
    public Button popUpCancelBtn = new Button();
    private Window popUp ;


   public EditView( MainLayout mainApp) {
       this.main = mainApp;
       this.table = new EditTable(main);
       this.form = new EditForm();
       this.popUp = modelWindow();
       setSizeFull();
       addComponent(form);
       addComponent(table);
       addListeners();
   }
    private Window modelWindow(){
        final Window popup = new Window("View Content");
        popup.setWidth(80.0f,Unit.PERCENTAGE);
        popup.setHeight(90.0f, Unit.PERCENTAGE);

        final  FormLayout formLayout = new FormLayout();
        textEditor.setImmediate(true);
        textEditor.setWidth(98.0f, Unit.PERCENTAGE);
        textEditor.setHeight(400.0f, Unit.PIXELS);
        textEditor.setNullRepresentation("");
        textEditor.addValidator(new BeanValidator(EditModel.class, "content"));
        textEditor.setImmediate(true);
        form.binder.bind(textEditor, "content");

        formLayout.addComponent(textEditor);
        formLayout.addComponent(popUpButtons());
        popup.setContent(formLayout);

        return popup;
    }

   public HorizontalLayout popUpButtons(){
       final HorizontalLayout buttons = new HorizontalLayout();
       buttons.setSpacing(true);

       popUpSaveBtn.setCaption("update");
       popUpCancelBtn.setCaption("Cancel");

       buttons.addComponent(popUpSaveBtn);
       buttons.addComponent(popUpCancelBtn);

       return buttons;
   }
   @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
       final Button source = clickEvent.getButton();
       if(source==form.editBtn){
           EditButton();
       }else if (source ==popUpSaveBtn){
           saveEditedForm(form.binder);
           popUp.setModal(false);
           UI.getCurrent().removeWindow(popUp);
           Notification.show("The button was clicked",
                   Notification.Type.TRAY_NOTIFICATION);
       }else if (source ==popUpCancelBtn){
           popUp.setModal(false);
           UI.getCurrent().removeWindow(popUp);
       }else if (source ==form.deleteBtn){
           deleteForm(form.binder);
       }
    }
    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
        final Property property = valueChangeEvent.getProperty();
        if (property==table){
            form.editBtn.setVisible(true);
            form.deleteBtn.setVisible(true);
        }
    }
    private void getHome() {
        //main.content.setSecondComponent(new SetupMenu(main, "LANDING"));
    }
    private void deleteForm(FieldGroup binder) {
        final Content content = getEntity(binder);
        contentService.remove(content);
        getHome();
    }
    private void EditButton(){
        try {
            Object rowId = table.getValue();
            textEditor.setValue(contentService.find(rowId + "").getContent().toString());
            UI.getCurrent().addWindow(popUp);
            popUp.setModal(true);
        }catch (Exception e){
            Notification.show("Select the content you wanna Edit",
                    Notification.Type.TRAY_NOTIFICATION);
        }
    }
    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
           // userService.merge(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }catch(Exception dp){
            Notification.show("Username is already taken!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }
    private Content getEntity(FieldGroup binder) {
        final EditModel bean = ((BeanItem<EditModel>) binder.getItemDataSource()).getBean();
        final Content content = new Content.Builder(bean.getContent())
                .category(bean.getCategory())
                .contentType(bean.getContentType())
                .creator(bean.getCreator())
                .dateCreated(bean.getDateCreated())
                .source(bean.getSource())
                .id(bean.getId())
                .build();
        return content;
    }
    public void addListeners(){
        form.editBtn.addClickListener((Button.ClickListener)this);
        form.deleteBtn.addClickListener((Button.ClickListener) this);
        popUpSaveBtn.addClickListener((Button.ClickListener) this);
        popUpCancelBtn.addClickListener((Button.ClickListener) this);
    }

}
