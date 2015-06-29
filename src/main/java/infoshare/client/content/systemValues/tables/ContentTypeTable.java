package infoshare.client.content.systemValues.tables;

import com.vaadin.ui.Table;
import infoshare.client.content.MainLayout;
import infoshare.domain.ContentType;
import infoshare.services.ContentType.ContentTypeService;
import infoshare.services.ContentType.Impl.ContentTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by codex on 2015/06/25.
 */
public class ContentTypeTable extends Table {

    @Autowired
    private ContentTypeService contentTypeService = new ContentTypeServiceImpl();

    private MainLayout main;

    public ContentTypeTable(MainLayout mainApp) {
        this.main = mainApp;
        setSizeFull();
        addContainerProperty("Name",String.class,null);
        addContainerProperty("Description",String.class,null);
        List<ContentType> contentTypes = contentTypeService.findAll();

        for(ContentType contentType: contentTypes){
            addItem(new Object[]{
                    contentType.getContentTyeName(),
                    contentType.getContentTyeDescription()
            },contentType.getId());
        }

        setSelectable(true);
        setImmediate(true);
        setNullSelectionAllowed(false);
    }
}
