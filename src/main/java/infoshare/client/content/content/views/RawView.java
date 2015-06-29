package infoshare.client.content.content.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.ContentMenu;
import infoshare.client.content.content.forms.RawAndEditForm;
import infoshare.client.content.content.models.RawAndEditModel;
import infoshare.client.content.content.tables.EditTable;
import infoshare.client.content.content.tables.RawTable;
import infoshare.domain.Content;
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
    private final RawAndEditForm form;

    private Window popUp ;
    private Button editBtn = new Button("EDIT");

    public RawView( MainLayout mainApp) {
        this.main = mainApp;
        this.table = new RawTable(main);
        this.form = new RawAndEditForm();
        this.popUp = modelWindow();
        setSizeFull();
        setSpacing(true);
        addComponent(editBtn);
        addComponent(table);
        addListeners();
    }
    private Window modelWindow(){
        final Window popup = new Window("EDIT RAW CONTENT");
        popup.setWidth(80.0f, Unit.PERCENTAGE);
        popup.setContent(form);
        return popup;
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        final Button source = clickEvent.getButton();
        if(source==editBtn){
            EditButton();
        }else if (source ==form.popUpUpdateBtn){
            saveEditedForm(form.binder);
        }else if (source ==form.popUpCancelBtn){
            popUp.setModal(false);
            UI.getCurrent().removeWindow(popUp);
            table.setValue(null);
        }
    }
    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
        final Property property = valueChangeEvent.getProperty();
        if (property == table) {
            final Content content = contentService.find(table.getValue().toString());
            final RawAndEditModel bean = getModel(content);
            form.binder.setItemDataSource(new BeanItem<>(bean));
        }
    }
    private void getHome() {
        main.content.setSecondComponent(new ContentMenu(main, "LANDING"));
    }
    private void EditButton(){
        try {
            Object rowId = table.getValue();
            form.textEditor.setValue(contentService.find(rowId + "").getContent().toString());
            form.popUpCategoryCmb.addItem("thulebona");
            form.popUpContentTypeCmb.addItem("Edited");
            UI.getCurrent().addWindow(popUp);
            popUp.setModal(true);
        }catch (Exception e){
            Notification.show("Select the row you wanna edit",
                    Notification.Type.HUMANIZED_MESSAGE);
        }
    }
    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            popUp.setModal(false);
            table.setValue(null);
            UI.getCurrent().removeWindow(popUp);
            getHome();
            Notification.show("Record EDITED!", Notification.Type.HUMANIZED_MESSAGE);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Fill in all Fields!!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }
    }
    private Content getEntity(FieldGroup binder) {
        final RawAndEditModel bean = ((BeanItem<RawAndEditModel>) binder.getItemDataSource()).getBean();
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
    private RawAndEditModel getModel(Content val) {
        final RawAndEditModel model = new RawAndEditModel();
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
        form.popUpUpdateBtn.addClickListener((Button.ClickListener)this);
        form.popUpCancelBtn.addClickListener((Button.ClickListener) this);
        editBtn.addClickListener((Button.ClickListener) this);
    }

}
