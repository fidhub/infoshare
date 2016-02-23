package infoshare.client.content.content.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.*;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.ContentMenu;
import infoshare.client.content.content.forms.RawForm;
import infoshare.client.content.content.models.ContentModel;
import infoshare.client.content.content.tables.RawTable;
import infoshare.domain.*;
import infoshare.filterSearch.RawContentFilter;
import infoshare.services.Content.EditedContentService;
import infoshare.services.Content.Impl.EditedContentServiceImpl;
import infoshare.services.Content.Impl.RawContentServiceImpl;
import infoshare.services.Content.RawContentService;

import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/24.
 */
public class RawView extends VerticalLayout implements Button.ClickListener,Property.ValueChangeListener{

    private RawContentService rawContentService = new RawContentServiceImpl();
    private EditedContentService editedContentService = new EditedContentServiceImpl();

    private final MainLayout main;
    private final RawTable table;
    private final RawForm form;
    private Window popUp ;
    private RawContentFilter rawContentFilter = new RawContentFilter();
    public String tableId;

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
        if (source ==form.putEdited){
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
        popup.setContent(form);
        return popup;
    }
    private void getHome() {
        main.content.setSecondComponent(new ContentMenu(main, "LANDING"));
    }
    public void EditButton(){
        try {
            tableId = table.getValue().toString();
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
            editedContentService.save(getNewEntity(binder));
            rawContentService.merge(getUpdateEntity(binder));
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
    private EditedContent getNewEntity(FieldGroup binder) {
        try {
            final ContentModel bean = ((BeanItem<ContentModel>) binder.getItemDataSource()).getBean();
            bean.setDateCreated(rawContentService.find(table.getValue().toString()).getDateCreated());
            final EditedContent editedContent = new EditedContent
                    .Builder(bean.getTitle())
                    .category(bean.getCategory())
                    .content(bean.getContent())
                    .contentType(bean.getContentType())
                    .creator(bean.getCreator())
                    .dateCreated(bean.getDateCreated())
                    .source(bean.getSource())
                    .state(bean.getState())
                    .status("Edited")
                    .build();
            return editedContent;
        }catch (Exception e){
            return null;
        }finally {
            tableId = null;
        }
    }
    private RawContent getUpdateEntity(FieldGroup binder) {
        try {
            final ContentModel bean = ((BeanItem<ContentModel>) binder.getItemDataSource()).getBean();
            bean.setDateCreated(rawContentService.find(table.getValue().toString()).getDateCreated());
            final RawContent rawContent = new RawContent
                    .Builder(bean.getTitle())
                    .category(bean.getCategory())
                    .content(bean.getContent())
                    .contentType(bean.getContentType())
                    .creator(bean.getCreator())
                    .dateCreated(bean.getDateCreated())
                    .source(bean.getSource())
                    .state(bean.getState())
                    .status("Edited")
                    .id(table.getValue().toString())
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
        model.setStatus(rawContent.getStatus());
        model.setState(rawContent.getState());
        return model;
    }
    public void addListeners(){
        form.putEdited.addClickListener(this);
        form.backBtn.addClickListener(this);
        table.addValueChangeListener(this);
        table.addItemClickListener(item -> {
            boolean flag = true;
            if (item.isDoubleClick()) {
                if(flag) {
                    EditButton();
                    flag=false;
                }
            }
        });
        rawContentFilter.field.addTextChangeListener((FieldEvents.TextChangeListener) textChangeEvent -> refreshContacts(textChangeEvent.getText()));


    }

}
