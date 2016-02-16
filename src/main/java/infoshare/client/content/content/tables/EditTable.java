package infoshare.client.content.content.tables;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.MainLayout;
import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;
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
    private ContentService contentService = new ContentServiceImp();
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
            contentService.findAll()
                    .stream()
                    .filter(content -> content != null)
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont-> !cont.getSource().equalsIgnoreCase("mobile"))
                    .collect(Collectors.toList())
                    .forEach(this::loadTable);
        }catch (Exception e){
        }
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }

    public void loadTable(Content content) {
        DateFormat formatter = new SimpleDateFormat("dd - MMMMMMM - yyyy");
        String category = categoryService.find(content.getCategory()).getName();
               if(!Check(content).contains(true)) {
                   try {
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

    public List<Boolean> Check( Content content ) {
        List<Boolean> check = new ArrayList<>();
        List<Content> contents =  contentService.findAll()
                .stream()
                .filter(cont -> cont != null)
                .collect(Collectors.toList())
                .stream()
                .filter(cont -> !cont.getSource().equalsIgnoreCase("mobile"))
                .collect(Collectors.toList());

        for (int i = 0; i < contents.size(); i++) {

                if (content.getId().equalsIgnoreCase(contents.get(i).getSource())) {
                    for (int j=0; j<contents.size(); j++) {
                        if (contents.get(i).getSource().equalsIgnoreCase(contents.get(j).getId())) {
                            check.add(false);
                        }else check.add(true);
                    }
                }
            }
        return check;
    }
}
