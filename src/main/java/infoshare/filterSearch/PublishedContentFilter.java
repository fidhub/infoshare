package infoshare.filterSearch;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.domain.EditedContent;
import infoshare.domain.PublishedContent;
import infoshare.services.EditedContent.Impl.EditedContentServiceImpl;
import infoshare.services.PublishedContent.Impl.PublishedContentServiceImpl;
import infoshare.services.PublishedContent.PublishedContentService;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by user9 on 2016/02/12.
 */
public class PublishedContentFilter {
    private PublishedContentService publishedContentService = new PublishedContentServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
    public TextField field = new TextField();
    public PublishedContentFilter() {
        getField();
    }
    public synchronized List<PublishedContent> findAll(String stringFilter) {
        DateFormat formatter = new SimpleDateFormat("dd-MMMMMMM-yyyy");
        ArrayList arrayList = new ArrayList();
        String cat;
        for (PublishedContent publishedContent : publishedContentService.findAll().stream()
                .filter(cont -> cont.getState().equalsIgnoreCase("active"))
                .collect(Collectors.toList())) {
            if(!publishedContent.getCategory().equalsIgnoreCase("uncategorized"))
                cat = categoryService.find(publishedContent.getCategory().toString()).getName().toLowerCase();
            else cat = publishedContent.getCategory().toString().toLowerCase();

            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || publishedContent.getTitle().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        ||cat.contains(stringFilter.toLowerCase())
                        || publishedContent.getCreator().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        || publishedContent.getSource().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        ||formatter.format(publishedContent.getDateCreated()).toString().toLowerCase()
                        .contains(stringFilter.toLowerCase());

                if (passesFilter) {
                    arrayList.add(publishedContent);
                }
            } catch (Exception ex) {
                Logger.getLogger(ex.getLocalizedMessage());
            }
        }

        return arrayList;
    }
    private TextField getField(){
        field.setInputPrompt("Filter EditedContent ...");
        field.setWidth("260px");
        field.setIcon(FontAwesome.FILTER);
        field.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        field.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        return field;
    }
}
