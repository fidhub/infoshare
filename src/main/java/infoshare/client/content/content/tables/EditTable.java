package infoshare.client.content.content.tables;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.models.EditModel;
import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hashcode on 2015/06/24.
 */
public class EditTable extends Table{

    @Autowired
    private ContentService contentService = new ContentServiceImp();
    private final MainLayout main;
    public Button editBtn = new Button();
    public Button deleteBtn = new Button();

    public EditTable(MainLayout mainApp){
        this.main = mainApp;
        setSizeFull();
        addContainerProperty("Title",String.class,null);
        addContainerProperty("Content Type",String.class,null);
        addContainerProperty("Category",String.class,null);
        addContainerProperty("Creator",String.class,null);
        addContainerProperty("Description",String.class,null);
        addContainerProperty("Date Created",Date.class,null);
        addContainerProperty("",Component.class,null);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        List<Content> contents = contentService.findAll();

       for (Content content: contents){
            addItem(new Object[]{
                    content.getTitle(),
                    content.getContentType(),"Category",
                    content.getCreator(),
                    content.getDescription(),
                    content.getDateCreated(),
                    buttons()
                },content.getId());
       }
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }
    private Component buttons(){
        final HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);

        editBtn.setIcon(FontAwesome.EDIT);
        editBtn.addStyleName(ValoTheme.BUTTON_TINY);
        editBtn.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        editBtn.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        deleteBtn.setIcon(FontAwesome.TRASH_O);
        deleteBtn.addStyleName(ValoTheme.BUTTON_TINY);
        deleteBtn.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        deleteBtn.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        //deleteBtn.addClickListener();

        buttonsLayout.addComponent(editBtn);
        buttonsLayout.addComponent(deleteBtn);

        return buttonsLayout;

    }

}
