package infoshare.client.content.content.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.*;
import infoshare.app.facade.ContentFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.ContentMenu;
import infoshare.client.content.content.forms.RawForm;
import infoshare.client.content.content.models.ContentModel;
import infoshare.client.content.content.tables.RawTable;
import infoshare.client.header.Header;
import infoshare.domain.content.*;
import infoshare.factories.content.EditedContentFactory;
import infoshare.filterSearch.RawContentFilter;
import infoshare.services.ContentFiles.content.EditedContentService;
import infoshare.services.ContentFiles.content.RawContentService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static infoshare.app.facade.CategoryFacade.categoryService;
import static infoshare.app.facade.ContentTypeFacade.contentTypeService;
import static infoshare.app.facade.SourceFacade.sourceService;

/**
 * Created by hashcode on 2015/06/24.
 */
public class  RawView extends VerticalLayout implements Button.ClickListener,Property.ValueChangeListener{

    private RawContentService rawContentService = ContentFacade.rawContentService;
    private EditedContentService editedContentService = ContentFacade.editedContentService;

    private final MainLayout main;
    private final RawTable table;
    private final RawForm form;
    private Window popUp ;
    private RawContentFilter rawContentFilter = new RawContentFilter();
    public static String tableId;

    public RawView( MainLayout mainApp) {

        this.main = mainApp;
        this.table = new RawTable(main);
        this.form = new RawForm();
        this.popUp = modelWindow();
        setSizeFull();
        setSpacing(true);
        addComponent(rawContentFilter.field);
        addComponent(table);
        addListeners();
    }
    public void refreshContacts(String stringFilter ) {
        try {
            table.removeAllItems();
            rawContentFilter.findAll(stringFilter)
                    .stream()
                    .filter(cont -> cont!= null)
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont -> cont.getStatus().equalsIgnoreCase("raw"))
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont -> cont.getState().equalsIgnoreCase("active"))
                    .collect(Collectors.toList()).forEach(table::loadTable);
        }catch (Exception e){
        }
    }
    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        final Button source = clickEvent.getButton();
        if (source ==form.saveBtn){
            saveEditedForm(form.binder);
        }else if (source ==form.backBtn){
            popUp.setModal(false);
            UI.getCurrent().removeWindow(popUp);
            table.setValue(null);
            getHome();
        }
    }
    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
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
        for (Source source : sourceService.findAll(OrganisationUtil.getCompanyCode())) {
            form.popUpSourceCmb.addItem(source.getId());
            form.popUpSourceCmb.setItemCaption(source.getId(), source.getName());
        }
    }
    private void getHome() {
        main.content.setSecondComponent(new ContentMenu(main, "LANDING"));
    }
    public void EditButton(){
        try {
            final RawContent rawContent = rawContentService.findById(OrganisationUtil.getCompanyCode(),tableId);
            final ContentModel bean = getModel(rawContent);
            if (popUp != null && popUp.getUI() !=null) {
                popUp.close();
            }
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
            editedContentService.save(getNewEntity(binder));
            rawContentService.update(getUpdateEntity(binder));
            popUp.setModal(false);
            table.setValue(null);
            UI.getCurrent().removeWindow(popUp);
            Header.refreshNotification();
            getHome();
            Notification.show("Record edited!!", Notification.Type.HUMANIZED_MESSAGE);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Fill in all Fields!!", Notification.Type.HUMANIZED_MESSAGE);
        }catch (Exception e){getHome();}
    }
    private EditedContent getNewEntity(FieldGroup binder) {
        try {
            final ContentModel bean = ((BeanItem<ContentModel>) binder.getItemDataSource()).getBean();
            bean.setDateCreated(rawContentService.findById(OrganisationUtil.getCompanyCode(), table.getValue().toString()).getDateCreated());
            Map<String,String> editedVals = new HashMap<>();
            editedVals.put("content",bean.getContent());
            editedVals.put("category",form.popUpCategoryCmb.getValue().toString());
            editedVals.put("creator",bean.getCreator());
            editedVals.put("title",bean.getTitle());
            editedVals.put("contentType",form.popUpContentTypeCmb.getValue().toString());
            editedVals.put("status","Edited");
            editedVals.put("source",bean.getSource());
            editedVals.put("org",bean.getOrg());
            final EditedContent editedContent = EditedContentFactory.getEditedContent(editedVals, bean.getDateCreated());
            return editedContent;
        }catch (Exception exception) {
            Notification.show("Missing Value", Notification.Type.HUMANIZED_MESSAGE);
            return null;
        }
    }
    private RawContent getUpdateEntity(FieldGroup binder) {
        try {
            final RawContent bean = rawContentService.findById(OrganisationUtil.getCompanyCode(),table.getValue().toString());
            final RawContent rawContent = new RawContent.Builder()
                    .id(table.getValue().toString())
                    .copy(bean)
                    .status("Edited")
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
        final RawContent rawContent =
                rawContentService.findById(OrganisationUtil.getCompanyCode(),val.getId());
        model.setTitle(rawContent.getTitle());
        model.setCategory(rawContent.getCategory());
        model.setCreator(rawContent.getCreator());
        model.setContent(rawContent.getContent());
        model.setContentType(rawContent.getContentType());
        model.setSource(rawContent.getSource());
        model.setStatus(rawContent.getStatus());
        model.setState(rawContent.getState());
        model.setOrg(rawContent.getOrg());
        return model;
    }
    public void addListeners(){
        form.saveBtn.addClickListener(this);
        form.backBtn.addClickListener(this);
        table.addValueChangeListener(this);
        table.addItemClickListener(item -> {
            boolean flag = true;
            if (item.isDoubleClick()) {
                if(flag) {
                    tableId = table.getValue().toString();
                    EditButton();
                    flag=false;
                }
            }
        });
        rawContentFilter.field.addTextChangeListener((FieldEvents.TextChangeListener) textChangeEvent -> refreshContacts(textChangeEvent.getText()));
    }

}
