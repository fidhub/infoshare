package infoshare.client.content.content.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.FieldBinder;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.ContentMenu;
import infoshare.client.content.content.forms.EditForm;
import infoshare.client.content.content.models.ContentModel;
import infoshare.client.content.content.tables.EditTable;
import infoshare.domain.*;
import infoshare.filterSearch.EditedContentFilter;
import infoshare.services.ContentType.ContentTypeService;
import infoshare.services.ContentType.Impl.ContentTypeServiceImpl;
import infoshare.services.EditedContent.EditedContentService;
import infoshare.services.EditedContent.Impl.EditedContentServiceImpl;
import infoshare.services.PublishedContent.Impl.PublishedContentServiceImpl;
import infoshare.services.PublishedContent.PublishedContentService;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;
import infoshare.services.source.SourceService;
import infoshare.services.source.sourceServiceImpl.SourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/24.
 */
public class EditView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener{

    @Autowired
    private EditedContentService editedContentService = new EditedContentServiceImpl();
    private PublishedContentService publishedContentService = new PublishedContentServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
    private ContentTypeService contentTypeService = new ContentTypeServiceImpl();
    private SourceService sourceService = new SourceServiceImpl();

    private final MainLayout main;
    private final EditTable table;
    private final EditForm form;
    private Window popUp ;

    private Button deleteCont ;
    private Button viewTrash;
    private EditedContentFilter editedContentFilter= new EditedContentFilter();

    public  EditView( MainLayout mainApp) {

       this.main = mainApp;
       this.table = new EditTable(main);
       this.form = new EditForm();
       this.popUp = modelWindow();
       setSizeFull();
       setSpacing(true);
       addComponent(getLayout());
       addComponent(table);
       addListeners();
   }
    private HorizontalLayout getLayout(){
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(false);
        deleteCont = new Button("Delete");
        viewTrash = new Button("view Trash");
        deleteCont.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        deleteCont.addStyleName(ValoTheme.BUTTON_SMALL);
        deleteCont.setIcon(FontAwesome.TRASH_O);
        viewTrash.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        viewTrash.addStyleName(ValoTheme.BUTTON_SMALL);
        viewTrash.setIcon(FontAwesome.EDIT);
        layout.addComponent(editedContentFilter.field);
        layout.addComponent(deleteCont);
        layout.addComponent(viewTrash);
        return layout;
    }
    private void refreshContacts(String stringFilter ) {
        try {
            table.removeAllItems();
            editedContentFilter.findAll(stringFilter).stream()
                    .filter(cont -> cont.getState().equalsIgnoreCase("active"))
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont -> cont.getStatus().equalsIgnoreCase("Edited"))
                    .collect(Collectors.toList())
                    .forEach(table::loadTable);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private Window modelWindow(){
        final Window popup = new Window();
        popup.setWidth(80.0f,Unit.PERCENTAGE);
        popup.setClosable(false);
        popup.setResizable(false);
        loadComboBoxs();
        popup.setContent(form);
        return popup;
    }
   @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
       final Button source = clickEvent.getButton();
       if(source==deleteCont){
         saveTrash(form.binder);
          }else if (source ==form.popUpUpdateBtn){
           saveEditedForm(form.binder);
       }else if (source ==form.popUpCancelBtn){
           popUp.setModal(false);
           UI.getCurrent().removeWindow(popUp);
           table.setValue(null);
           getHome();
       }else if(source== viewTrash) {
           table.removeAllItems();
           editedContentService.findAll()
                   .stream()
                   .filter(cont -> cont.getState().equalsIgnoreCase("deleted"))
                   .collect(Collectors.toList()).forEach(table::loadTable);
       }
    }
    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
        final Property property = valueChangeEvent.getProperty();
        if (property == table) {
        }
    }
    private void loadComboBoxs() {
        for (Category category : categoryService.findAll()) {
            form.popUpCategoryCmb.addItem(category.getId());
            form.popUpCategoryCmb.setItemCaption(category.getId(), category.getName());
        }

        for (ContentType contentType : contentTypeService.findAll()) {
            form.popUpContentTypeCmb.addItem(contentType.getId());
            form.popUpContentTypeCmb.setItemCaption(contentType.getId(), contentType.getName());
        }
        for (Source source : sourceService.findAll()) {
            form.popUpSourceCmb.addItem(source.getId());
            form.popUpSourceCmb.setItemCaption(source.getId(), source.getName());
        }
    }
    private void getHome() {
        main.content.setSecondComponent(new ContentMenu(main, "EDITOR"));
    }
    private void EditButton(){
        try {
            final ContentModel bean = getModel(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(bean));
            UI.getCurrent().addWindow(popUp);
            popUp.setModal(true);
        }catch (Exception e){
            Notification.show("Select the row you wanna edit",
                    Notification.Type.HUMANIZED_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            try {
                publishedContentService.save(getNewEntity(binder));
                editedContentService.merge(getUpdateEntity(binder));
                popUp.setModal(false);
                table.setValue(null);
                UI.getCurrent().removeWindow(popUp);
                getHome();
                Notification.show("Record EDITED!", Notification.Type.HUMANIZED_MESSAGE);
            }catch (Exception e){
                Notification.show("Please select the Category",Notification.Type.HUMANIZED_MESSAGE);
            }

        } catch (FieldGroup.CommitException e) {
            Notification.show("Fill in all Fields!!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }
    }
    public void saveTrash(FieldGroup binder){
        try {
            binder.commit();
            try {
                editedContentService.merge(getMoveToCrash(binder));  getHome();
            }catch (Exception e){
                Notification.show("Please select the Category",Notification.Type.HUMANIZED_MESSAGE);
            }

        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }
    }
    private PublishedContent getNewEntity(FieldGroup binder) {

        final ContentModel bean = ((BeanItem<ContentModel>) binder.getItemDataSource()).getBean();
        bean.setDateCreated(editedContentService.find(table.getValue().toString()).getDateCreated());
        final PublishedContent editedContent = new PublishedContent
                .Builder(bean.getTitle())
                .category(categoryService.find(bean.getCategory()).getId())
                .content(bean.getContent())
                .contentType(bean.getContentType())
                .creator(bean.getCreator())
                .dateCreated(bean.getDateCreated())
                .source(table.getValue().toString())
                .state(bean.getState())
                .status("Published")
               // .id(table.getValue().toString())
                .build();
        return editedContent;
    }
    private EditedContent getUpdateEntity(FieldGroup binder) {

        final ContentModel bean = ((BeanItem<ContentModel>) binder.getItemDataSource()).getBean();
        bean.setDateCreated(editedContentService.find(table.getValue().toString()).getDateCreated());
        final EditedContent editedContent = new EditedContent
                .Builder(bean.getTitle())
                .category(categoryService.find(bean.getCategory()).getId())
                .content(bean.getContent())
                .contentType(bean.getContentType())
                .creator(bean.getCreator())
                .dateCreated(bean.getDateCreated())
                .source(table.getValue().toString())
                .state(bean.getState())
                .status("Published")
                .id(table.getValue().toString())
                .build();

        return editedContent;
    }
    private EditedContent getMoveToCrash(FieldGroup binder) {

        final ContentModel bean = ((BeanItem<ContentModel>) binder.getItemDataSource()).getBean();
        bean.setState("Deleted");
        final EditedContent editedContent = new EditedContent
                .Builder(bean.getTitle())
                .category(bean.getCategory())
                .content(bean.getContent())
                .contentType(bean.getContentType())
                .creator(bean.getCreator())
                .dateCreated(bean.getDateCreated())
                .source(bean.getSource())
                .state(bean.getState())
                .status(bean.getStatus())
                .id(table.getValue().toString())
                .build();
        return editedContent;
    }
    private ContentModel getModel(String val) {
        final ContentModel model = new ContentModel();
        final EditedContent editedContent = editedContentService.find(val.toString());
        model.setTitle(editedContent.getTitle());
        model.setCategory(editedContent.getCategory());
        model.setCreator(editedContent.getCreator());
        model.setContent(editedContent.getContent());
        model.setContentType(editedContent.getContentType());
        model.setSource(editedContent.getSource());
        model.setStatus(editedContent.getStatus());
        model.setState(editedContent.getState());
        return model;
    }
    public void addListeners(){
        form.popUpUpdateBtn.addClickListener((Button.ClickListener)this);
        form.popUpCancelBtn.addClickListener((Button.ClickListener) this);
        deleteCont.addClickListener((Button.ClickListener) this);
        viewTrash.addClickListener((Button.ClickListener) this);
        table.addValueChangeListener((Property.ValueChangeListener)this);
        table.addItemClickListener(event1 -> {
            boolean flag=true;
            if (event1.isDoubleClick())
            {
                if(flag) {
                   EditButton();
                   flag=false;
                }
            }
        });
        editedContentFilter.field.addTextChangeListener(textChangeEvent -> refreshContacts(textChangeEvent.getText()));
    }

}
