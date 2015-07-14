package infoshare.client.content.content.tables;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by hashcode on 2015/06/24.
 */
public class EditTable extends Table{

    @Autowired
    private ContentService contentService = new ContentServiceImp();
    private final MainLayout main;

    public EditTable(MainLayout mainApp){
        this.main = mainApp;

        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addContainerProperty("Title",String.class,null);
        addContainerProperty("Category",String.class,null);
        addContainerProperty("Creator",String.class,null);
        addContainerProperty("Source",String.class,null);
        addContainerProperty("Date Created",Date.class,null);

       for (Content content:contentService.findAll()) {
           loadTable(content);
       }
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }

    public void loadTable(Content content) {
        if (content.getContentType().equalsIgnoreCase("edited")) {
            addItem(new Object[]{
                    content.getTitle(),
                    content.getCategory(),
                    content.getCreator(),
                    content.getSource(),
                    content.getDateCreated()
            }, content.getId());
        }
    }

}
