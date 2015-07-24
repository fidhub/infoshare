package infoshare.client.content.content.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.ContentMenu;
import infoshare.client.content.content.forms.RawAndEditForm;
import infoshare.client.content.content.models.RawAndEditModel;
import infoshare.client.content.content.tables.EditTable;
import infoshare.domain.Category;
import infoshare.domain.Content;
import infoshare.domain.ContentType;
import infoshare.domain.Source;
import infoshare.filterSearch.ContentFilter;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;
import infoshare.services.ContentType.ContentTypeService;
import infoshare.services.ContentType.Impl.ContentTypeServiceImpl;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;
import infoshare.services.source.SourceService;
import infoshare.services.source.sourceServiceImpl.SourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hashcode on 2015/06/24.
 */
public class EditView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener{

    @Autowired
    private ContentService contentService = new ContentServiceImp();
    private CategoryService categoryService = new CategoryServiceImpl();
    private ContentTypeService contentTypeService = new ContentTypeServiceImpl();
    private SourceService sourceService = new SourceServiceImpl();

    private final MainLayout main;
    private final EditTable table;
    private final RawAndEditForm form;

    private Window popUp ;
    private Button editBtn = new Button("UPDATE");
    private ContentFilter contentFilter = new ContentFilter();

    public EditView( MainLayout mainApp) {

       this.main = mainApp;
       this.table = new EditTable(main);
       this.form = new RawAndEditForm();
       this.popUp = modelWindow();
       editBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
       editBtn.setIcon(FontAwesome.EDIT);
       setSizeFull();
       setSpacing(true);
       addComponent(getLayout());
       addComponent(table);
       addListeners();
   }
    private HorizontalLayout getLayout(){
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(false);
        editBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        editBtn.addStyleName(ValoTheme.BUTTON_SMALL);
        editBtn.setIcon(FontAwesome.EDIT);
        layout.addComponent(contentFilter.field);
        layout.addComponent(editBtn);
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
    private Window modelWindow(){
        final Window popup = new Window("UPDATE EDITED CONTENT");
        popup.setWidth(80.0f,Unit.PERCENTAGE);
        popup.setClosable(false);
        popup.setResizable(false);
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
           getHome();
       }
    }
    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
        final Property property = valueChangeEvent.getProperty();
        if (property == table) {
             editBtn.setVisible(true);
        }
    }
    private void loadComboBoxs(){
        for(Category category:categoryService.findAll()){
            if(category!= null)
                form.popUpCategoryCmb.addItem(category.getName());
        }

        for(ContentType category:contentTypeService.findAll()){
            if(category!= null)
                form.popUpContentTypeCmb.addItem(category.getContentTyeName());
        }
        for(Source source:sourceService.findAll()){
            if(source!= null)
                form.popUpSourceCmb.addItem(source.getName());
        }

    }
    private void getHome() {
        main.content.setSecondComponent(new ContentMenu(main, "EDITOR"));
    }
    private void EditButton(){
        loadComboBoxs();
        try {
            final Content content = contentService.find(table.getValue().toString());
            final RawAndEditModel bean = getModel(content);
            form.binder.setItemDataSource(new BeanItem<>(bean));

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
            contentService.merge(getUpdateEntity(binder));
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
    private Content getUpdateEntity(FieldGroup binder) {

        final RawAndEditModel bean = ((BeanItem<RawAndEditModel>) binder.getItemDataSource()).getBean();
        bean.setDateCreated(contentService.find(table.getValue().toString()).getDateCreated());
        final Content content = new Content.Builder(bean.getTitle())
                .category(bean.getCategory())
                .content(bean.getContent())
                .contentType(bean.getContentType())
                .creator(bean.getCreator())
                .dateCreated(bean.getDateCreated())
                .source(bean.getSource())
                .id(table.getValue().toString())
                .build();
        return content;
    }
    private RawAndEditModel getModel(Content val) {
        final RawAndEditModel model = new RawAndEditModel();
        final Content content = contentService.find(val.getId());
        model.setTitle(content.getTitle());
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
        table.addValueChangeListener((Property.ValueChangeListener)this);
        contentFilter.field.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent textChangeEvent) {
                refreshContacts(textChangeEvent.getText());
            }
        });
    }
}
