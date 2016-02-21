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

import java.util.stream.Collector;
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
    private EditedContentFilter editedContentFilter = new EditedContentFilter();
    private Button deleteCont = new Button();
    private Button viewTrash = new Button();
    private Button viewActive = new Button();
    private String state;

    public  EditView( MainLayout mainApp) {

       this.main = mainApp;
       this.table = new EditTable(main);
       this.form = new EditForm();
       this.popUp = modelWindow();
       viewActive.setVisible(false);
       state ="active";
       setSizeFull();
       setSpacing(true);
       addComponent(getLayout());
       addComponent(table);
       addListeners();
   }
    private HorizontalLayout getLayout(){
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(false);
        deleteCont.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        deleteCont.addStyleName(ValoTheme.BUTTON_SMALL);
        deleteCont.setCaption("Remove");
        deleteCont.setDescription("Delete Content");
        deleteCont.setIcon(FontAwesome.REMOVE);

        viewTrash.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        viewTrash.addStyleName(ValoTheme.BUTTON_SMALL);
        viewTrash.setCaption("Trash");
        viewTrash.setDescription("Show Deleted content");
        viewTrash.setIcon(FontAwesome.TRASH_O);

        viewActive.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        viewActive.addStyleName(ValoTheme.BUTTON_SMALL);
        viewActive.setCaption("Active");
        viewActive.setDescription("Show Active content");
        viewActive.setIcon(FontAwesome.EDIT);

        layout.addComponent(editedContentFilter.field);
        layout.addComponent(deleteCont);
        layout.addComponent(viewTrash);
        layout.addComponent(viewActive);
        return layout;
    }
    private void refreshContacts(String stringFilter ) {
        try {
            table.removeAllItems();
            editedContentFilter.findAll(stringFilter,state).stream()
                    .filter(content -> content != null)
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont -> !cont.getStatus().equalsIgnoreCase("Edited"))
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont -> !cont.getStatus().equalsIgnoreCase("active"))
                    .collect(Collectors.toList())
                    .forEach(table::loadTable);
        }catch (Exception e){
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
       if (source ==form.popUpUpdateBtn){
           saveEditedForm(form.binder);
       }else if (source ==form.popUpCancelBtn){
           popUp.setModal(false);
           UI.getCurrent().removeWindow(popUp);
           table.setValue(null);
           getHome();
       }else if(source==viewTrash){
           viewTrash.setVisible(false);
           state="Deleted";
           viewActive.setVisible(true);
           getTrash();
       }else if(source==viewActive){
           viewActive.setVisible(false);
           state="Active";
           viewTrash.setVisible(true);
           getHome();
       }
    }
    @Override
    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
        final Property property = valueChangeEvent.getProperty();

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
    private void getTrash(){
        try{
            editedContentService.findAll().stream()
                    .filter(cont -> cont.getState().equalsIgnoreCase("Deleted"))
                    .collect(Collectors.toList())
                    .stream().filter(cont -> cont.getStatus().equalsIgnoreCase("Edited"))
                    .collect(Collectors.toList()).forEach(table::loadTable);
        }catch (Exception e){
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
    private PublishedContent getNewEntity(FieldGroup binder) {
        final ContentModel bean = ((BeanItem<ContentModel>) binder.getItemDataSource()).getBean();
        final PublishedContent editedContent = new PublishedContent
                .Builder(bean.getTitle())
                .category(categoryService.find(bean.getCategory()).getId())
                .content(bean.getContent())
                .contentType(bean.getContentType())
                .creator(bean.getCreator())
                .dateCreated(bean.getDateCreated())
                .source(bean.getSource())
                .state(bean.getState())
                .status("Published")
                .build();
        return editedContent;
    }
    private EditedContent getUpdateEntity(FieldGroup binder) {
        final ContentModel bean = ((BeanItem<ContentModel>) binder.getItemDataSource()).getBean();
        final EditedContent editedContent = new EditedContent
                .Builder(bean.getTitle())
                .category(categoryService.find(bean.getCategory()).getId())
                .content(bean.getContent())
                .contentType(bean.getContentType())
                .creator(bean.getCreator())
                .dateCreated(bean.getDateCreated())
                .source(bean.getSource())
                .state(bean.getState())
                .status("Published")
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
        form.popUpUpdateBtn.addClickListener(this);
        form.popUpCancelBtn.addClickListener(this);
        deleteCont.addClickListener(this);
        viewTrash.addClickListener(this);
        viewActive.addClickListener(this);
        table.addValueChangeListener(this);
        table.addItemClickListener(item ->{
            boolean flag = true;
            if (item.isDoubleClick()) {
                if(flag) {
                    EditButton();
                    flag=false;
                }
            }
        });
        editedContentFilter.field.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent textChangeEvent) {
                refreshContacts(textChangeEvent.getText());
            }
        });
    }

}
