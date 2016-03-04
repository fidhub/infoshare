package infoshare.client.content.content.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.CategoryFacade;
import infoshare.app.facade.ContentFacade;
import infoshare.app.facade.ContentTypeFacade;
import infoshare.app.facade.SourceFacade;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.ContentMenu;
import infoshare.client.content.content.forms.EditForm;
import infoshare.client.content.content.models.ContentModel;
import infoshare.client.content.content.tables.EditTable;
import infoshare.domain.content.*;
import infoshare.factories.content.PublishedContentFactory;
import infoshare.filterSearch.EditedContentFilter;
import infoshare.services.ContentFiles.ContentType.ContentTypeService;
import infoshare.services.ContentFiles.content.EditedContentService;
import infoshare.services.ContentFiles.content.PublishedContentService;
import infoshare.services.ContentFiles.category.CategoryService;
import infoshare.services.ContentFiles.source.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/24.
 */
public class EditView extends VerticalLayout implements Button.ClickListener, Property.ValueChangeListener{

    @Autowired
    private EditedContentService editedContentService = ContentFacade.editedContentService;
    private PublishedContentService publishedContentService = ContentFacade.publishedContentService;
    private CategoryService categoryService = CategoryFacade.categoryService;
    private ContentTypeService contentTypeService = ContentTypeFacade.contentTypeService;
    private SourceService sourceService = SourceFacade.sourceService;

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
       deleteCont.setVisible(true);
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
        deleteCont.setDescription("Delete ContentFiles");
        deleteCont.setIcon(FontAwesome.REMOVE);

        viewTrash.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        viewTrash.addStyleName(ValoTheme.BUTTON_SMALL);
        viewTrash.setCaption("Trash");
        viewTrash.setDescription("Show Deleted content");
        viewTrash.setIcon(FontAwesome.TRASH_O);

        viewActive.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        viewActive.addStyleName(ValoTheme.BUTTON_SMALL);
        viewActive.setCaption("Show Active");
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
                    .filter(cont -> cont.getState().equalsIgnoreCase(state))
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont -> cont.getStatus().equalsIgnoreCase("Edited"))
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
           if(state.equalsIgnoreCase("Active")){
           getHome();
           }else {
               getTrash();
           }
       }else if(source==viewTrash){
           viewTrash.setVisible(false);
           state="Deleted";
           deleteCont.setVisible(false);
           viewActive.setVisible(true);
           getTrash();
       }else if(source==viewActive){
           getHome();
           viewActive.setVisible(false);
           state="Active";
       }else if (source ==deleteCont){
           if(table.getValue().toString()!=null) {
               ConfirmDialog.show(this.getUI(),"Are you sure you Wanna delete ?",
                       (ConfirmDialog.Listener) dialog -> {
                           if (dialog.isConfirmed()) {
                               editedContentService.update(getTrashEntity(table.getValue().toString()));
                               getHome();
                           } else getHome();

                       });
           }else{
              Notification.show("Select content you wanna delete", Notification.Type.HUMANIZED_MESSAGE);
           }
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
        for (Source source : sourceService.findAll("org")) {
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
            table.removeAllItems();
            editedContentService.findAll("org").stream()
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
                editedContentService.update(getUpdateEntity(binder));
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
        Map<String,String> publishedVals = new HashMap<>();
        publishedVals.put("content",bean.getContent());
        publishedVals.put("category",bean.getCategory());
        publishedVals.put("creator",bean.getCreator());
        publishedVals.put("contentType",bean.getContentType());
        publishedVals.put("status",bean.getStatus());
        publishedVals.put("source",bean.getStatus());
        final PublishedContent publishedContent = PublishedContentFactory.getPublishedContent(publishedVals, new Date());
        return publishedContent;
    }
    private EditedContent getUpdateEntity(FieldGroup binder) {
        final ContentModel bean = ((BeanItem<ContentModel>) binder.getItemDataSource()).getBean();
        final EditedContent editedContent = new EditedContent
                .Builder()
                .title(bean.getTitle())
                .category(categoryService.findById(bean.getCategory()).getId())
                .content(bean.getContent())
                .contentType(bean.getContentType())
                .creator(bean.getCreator())
                .dateCreated(bean.getDateCreated())
                .source(bean.getSource())
                .state(bean.getState())
                .org(bean.getOrg())
                .status("Published")
                .id(table.getValue().toString())
                .build();
        return editedContent;
    }
    private EditedContent getTrashEntity(String val) {
       EditedContent content = editedContentService.findById("",val);
        final EditedContent editedContent = new EditedContent
                .Builder().copy(content)
                .state("Deleted")
                .build();
        return editedContent;
    }
    private ContentModel getModel(String val) {
        final ContentModel model = new ContentModel();
        final EditedContent editedContent = editedContentService.findById("",val.toString());
        model.setTitle(editedContent.getTitle());
        model.setCategory(editedContent.getCategory());
        model.setCreator(editedContent.getCreator());
        model.setContent(editedContent.getContent());
        model.setContentType(editedContent.getContentType());
        model.setSource(editedContent.getSource());
        model.setStatus(editedContent.getStatus());
        model.setState(editedContent.getState());
        model.setOrg(editedContent.getOrg());
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
        editedContentFilter.field.addTextChangeListener((FieldEvents.TextChangeListener) textChangeEvent -> refreshContacts(textChangeEvent.getText()));
    }

}
