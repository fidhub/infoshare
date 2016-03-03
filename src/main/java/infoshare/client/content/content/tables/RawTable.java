package infoshare.client.content.content.tables;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.ContentFacade;
import infoshare.client.content.MainLayout;
import infoshare.domain.content.RawContent;

import infoshare.services.ContentFiles.content.RawContentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/24.
 */
public class RawTable extends Table {

    @Autowired
    private RawContentService rawContentService = ContentFacade.rawContentService;
    private final MainLayout main;

    public RawTable(MainLayout mainApp){

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
            rawContentService.findAll()
                    .stream()
                    .filter(cont -> cont!= null)
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont->cont.getStatus().equalsIgnoreCase("raw"))
                    .collect(Collectors.toList())
                     .stream()
                    .filter(cont->cont.getState().equalsIgnoreCase("active"))
                    .collect(Collectors.toList())
                    .forEach(this::loadTable);
        }catch (Exception e){
        }
         setNullSelectionAllowed(false);
         setSelectable(true);
         setImmediate(true);
    }

    public void loadTable(RawContent rawContent) {
        DateFormat formatter = new SimpleDateFormat("dd MMMMMMM yyyy");
        try {
            addItem(new Object[]{
                    rawContent.getTitle(),
                    rawContent.getCategory(),
                    rawContent.getCreator(),
                    formatter.format(rawContent.getDateCreated())
            }, rawContent.getId());
        } catch (Exception r) {
        }
    }

}


