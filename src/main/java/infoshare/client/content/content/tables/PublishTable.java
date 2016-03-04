package infoshare.client.content.content.tables;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.CategoryFacade;
import infoshare.app.facade.ContentFacade;
import infoshare.client.content.MainLayout;
import infoshare.domain.content.PublishedContent;
import infoshare.services.ContentFiles.content.PublishedContentService;
import infoshare.services.ContentFiles.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/24.
 */
public class PublishTable extends Table{

    @Autowired
    private PublishedContentService publishedContentService = ContentFacade.publishedContentService;
    private CategoryService categoryService = CategoryFacade.categoryService;

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
            publishedContentService.findAll("org")
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
           String category = categoryService.findById(content.getCategory()).getName();
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
