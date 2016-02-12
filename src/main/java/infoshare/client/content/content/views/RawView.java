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
import infoshare.client.content.content.models.ContentModel;
import infoshare.client.content.content.tables.RawTable;
import infoshare.domain.*;
import infoshare.filterSearch.RawContentFilter;
import infoshare.services.ContentType.ContentTypeService;
import infoshare.services.ContentType.Impl.ContentTypeServiceImpl;
import infoshare.services.RawContent.Impl.RawContentServiceImpl;
import infoshare.services.RawContent.RawContentService;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;
import infoshare.services.source.SourceService;
import infoshare.services.source.sourceServiceImpl.SourceServiceImpl;

import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/24.
 */
public class RawView extends VerticalLayout implements Button.ClickListener,Property.ValueChangeListener{

    private RawContentService rawContentService = new RawContentServiceImpl();
    private ContentTypeService contentTypeService = new ContentTypeServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
    private SourceService sourceService = new SourceServiceImpl();

    private final MainLayout main;
    private final RawTable table;
    private final RawAndEditForm form;
    private Window popUp ;
    private Button editBtn = new Button("EDIT");
    private RawContentFilter rawContentFilter = new RawContentFilter();
    public String tableId ;

    public RawView( MainLayout mainApp) {

        this.main = mainApp;
        this.table = new RawTable(main);
        this.form = new RawAndEditForm();
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
        editBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        editBtn.addStyleName(ValoTheme.BUTTON_SMALL);
        editBtn.setIcon(FontAwesome.EDIT);
        layout.addComponent(rawContentFilter.field);
        layout.addComponent(editBtn);
        return layout;
    }
    public void refreshContacts(String stringFilter ) {
        try {
            table.removeAllItems();
            rawContentFilter.findAll(stringFilter)
                    .stream()
                    .filter(cont -> cont!= null)
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont->cont.getStatus().equalsIgnoreCase("raw"))
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont->cont.getState().equalsIgnoreCase("active"))
                    .collect(Collectors.toList()).forEach(table::loadTable);
        }catch (Exception e){
        }
    }
    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

        final Button source = clickEvent.getButton();
        if(source==editBtn){
            try {
                tableId = table.getValue().toString();
                EditButton();
            }catch (Exception e){
            }
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
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            editBtn.setVisible(true);
        }
    }
    private Window modelWindow(){
        final Window popup = new Window();
        popup.setWidth(80.0f, Unit.PERCENTAGE);
        popup.setClosable(false);
        popup.setResizable(false);
        loadComboBoxs();
        popup.setContent(form);
        return popup;
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
        main.content.setSecondComponent(new ContentMenu(main, "LANDING"));
    }
    public void EditButton(){
        try {
            final RawContent rawContent = rawContentService.find(tableId);
            final ContentModel bean = getModel(rawContent);
            form.binder.setItemDataSource(new BeanItem<>(bean));
              UI.getCurrent().addWindow(popUp);
                popUp.setModal(true);
            getHome();
        }catch (Exception e){
            Notification.show("Select the row you wanna edit",
                    Notification.Type.HUMANIZED_MESSAGE);
        }
    }
    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            rawContentService.save(getNewEntity(binder));
            popUp.setModal(false);
            table.setValue(null);
            UI.getCurrent().removeWindow(popUp);
            getHome();
            Notification.show("Record edited!!", Notification.Type.HUMANIZED_MESSAGE);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Fill in all Fields!!", Notification.Type.HUMANIZED_MESSAGE);
            getHome();
        }
    }
    private RawContent getNewEntity(FieldGroup binder) {

        try {
            final ContentModel bean = ((BeanItem<ContentModel>) binder.getItemDataSource()).getBean();
            bean.setDateCreated(rawContentService.find(table.getValue().toString()).getDateCreated());
            final RawContent rawContent = new RawContent
                    .Builder(bean.getTitle())
                    .category(categoryService.find(bean.getCategory()).getId())
                    .content(bean.getContent())
                    .contentType(bean.getContentType())
                    .creator(bean.getCreator())
                    .dateCreated(bean.getDateCreated())
                    .source(table.getValue().toString())
                    .build();
            return rawContent;
        }catch (Exception e){
            return null;
        }finally {
            tableId = null;
        }
    }

    private ContentModel getModel(RawContent val) {

        final ContentModel model = new ContentModel();
        final RawContent rawContent = rawContentService.find(val.getId());
        model.setTitle(rawContent.getTitle());
        model.setCategory(rawContent.getCategory());
        model.setCreator(rawContent.getCreator());
        model.setContent(rawContent.getContent());
        model.setContentType(rawContent.getContentType());
        model.setSource(rawContent.getSource());
        return model;
    }
    public void addListeners(){

        form.popUpUpdateBtn.addClickListener((Button.ClickListener)this);
        form.popUpCancelBtn.addClickListener((Button.ClickListener) this);
        table.addValueChangeListener((Property.ValueChangeListener) this);
        editBtn.addClickListener((Button.ClickListener) this);
        rawContentFilter.field.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent textChangeEvent) {
                refreshContacts(textChangeEvent.getText());
            }
        });


    }

}
