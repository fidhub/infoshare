package infoshare.client.content.content.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.CategoryFacade;
import infoshare.app.facade.ContentFacade;
import infoshare.app.util.DomainState;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.ContentMenu;
import infoshare.client.header.Header;
import infoshare.domain.content.EditedContent;
import infoshare.domain.content.PublishedContent;
import infoshare.restapi.ContentFiles.content.EditedContentAPI;
import infoshare.restapi.ContentFiles.content.PublishedContentAPI;
import infoshare.services.ContentFiles.category.CategoryService;
import infoshare.services.ContentFiles.content.PublishedContentService;
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
    private Button delete = new Button("Delete");
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
        addContainerProperty("Delete", Button.class, null);
        try {
            publishedContentService.findAll(OrganisationUtil.getCompanyCode()) //TODO
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
        delete.setData(content.getId());
        delete.setImmediate(true);
        delete.addClickListener(event -> {
            PublishedContent raw = new PublishedContent.Builder()
                    .copy(content)
                    .state(DomainState.RETIRED.name())
                    .build();
            PublishedContentAPI.save(raw);
            Header.refreshNotification();
            getHome();
        });
        String category = categoryService.findById(content.getCategory()).getName();
        try {
            addItem(new Object[]{
                    content.getTitle(),
                    category,
                    content.getCreator(),
                    formatter.format(content.getDateCreated()),
                    delete
            }, content.getId());
        } catch (Exception r) {
        }

    }


    public void getHome() {
        main.content.setSecondComponent(new ContentMenu(main, "PUBLISHER"));
    }
}
