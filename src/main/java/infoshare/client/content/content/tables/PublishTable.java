package infoshare.client.content.content.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.domain.PublishedContent;
import infoshare.services.PublishedContent.Impl.PublishedContentServiceImpl;
import infoshare.services.PublishedContent.PublishedContentService;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/24.
 */
public class PublishTable extends Table{

    @Autowired
    private PublishedContentService publishedContentService = new PublishedContentServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

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
        addContainerProperty("Date Created",String.class,null);

        try {
            publishedContentService.findAll()
                    .stream()
                    .filter(cont -> cont != null)
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont -> cont.getStatus().equalsIgnoreCase("Published"))
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont -> cont.getState().equalsIgnoreCase("active"))
                    .collect(Collectors.toList())
                    .forEach(this::loadTable);
        }catch (Exception e){

        }
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }
    public void loadTable(PublishedContent content) {
        DateFormat formatter = new SimpleDateFormat("dd MMMMMMM yyyy");
           String category = categoryService.find(content.getCategory()).getName();
              try{
                  addItem(new Object[]{
                            content.getTitle(),
                            category,
                            content.getCreator(),
                            formatter.format(content.getDateCreated())
                    }, content.getId());
                } catch (Exception r) {
                }

    }


}
