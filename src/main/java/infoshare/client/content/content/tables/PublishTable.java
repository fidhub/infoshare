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
public class PublishTable extends Table{

    @Autowired
    private ContentService contentService = new ContentServiceImp();
    private final MainLayout main;

    public PublishTable(MainLayout mainApp){
        this.main = mainApp;
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Title",String.class,null);
        addContainerProperty("Category",String.class,null);
        addContainerProperty("Creator",String.class,null);
        addContainerProperty("Source",String.class,null);
        addContainerProperty("Date Created",Date.class,null);

        for (Content content: contentService.findAll()){
            loadTable(content);
        }
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }
    public void loadTable(Content content){
            if(content.getContentType().equalsIgnoreCase("published")) {
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
