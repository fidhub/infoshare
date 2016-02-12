package infoshare.client.content.content.tables;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.MainLayout;
import infoshare.domain.Content;
import infoshare.domain.EditedContent;
import infoshare.domain.RawContent;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;
import infoshare.services.EditedContent.EditedContentService;
import infoshare.services.EditedContent.Impl.EditedContentServiceImpl;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/24.
 */
public class EditTable extends Table{

    @Autowired
    private EditedContentService editedContentService = new EditedContentServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
    private final MainLayout main;

    public EditTable(MainLayout mainApp){
        this.main = mainApp;

        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Title",String.class,null);
        addContainerProperty("Category",String.class,null);
        addContainerProperty("Creator",String.class,null);
     //   addContainerProperty("Source",String.class,null);
        addContainerProperty("Date Created",String.class,null);

        try {
            editedContentService.findAll()
                    .stream()
                    .filter(content -> content != null)
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont-> !cont.getStatus().equalsIgnoreCase("Edited"))
                    .collect(Collectors.toList())
                    .forEach(this::loadTable);
        }catch (Exception e){
        }
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }

    public void loadTable(EditedContent editedContent) {
        DateFormat formatter = new SimpleDateFormat("dd-MMMMMMM-yyyy");
        String category = categoryService.find(editedContent.getCategory()).getName();
        try {
            addItem(new Object[]{
                    editedContent.getTitle(),
                    category,
                    editedContent.getCreator(),
                    formatter.format(editedContent.getDateCreated())
            }, editedContent.getId());
        } catch (Exception r) {
        }

    }

}
