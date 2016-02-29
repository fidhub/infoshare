package infoshare.filterSearch;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.CategoryFacade;
import infoshare.app.facade.ContentFacade;
import infoshare.domain.content.PublishedContent;
import infoshare.services.Content.PublishedContentService;
import infoshare.services.category.CategoryService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by user9 on 2016/02/12.
 */
public class PublishedContentFilter {
    private PublishedContentService publishedContentService = ContentFacade.publishedContentService;
    private CategoryService categoryService = CategoryFacade.categoryService;
    public TextField field = new TextField();
    public PublishedContentFilter() {
        getField();
    }
    public synchronized List<PublishedContent> findAll(String stringFilter) {
        DateFormat formatter = new SimpleDateFormat("dd MMMMMMM yyyy");
        ArrayList arrayList = new ArrayList();
        String cat;
        for (PublishedContent publishedContent : publishedContentService.findAll().stream()
                .filter(cont -> cont.getState().equalsIgnoreCase("active"))
                .collect(Collectors.toList())) {
            if(!publishedContent.getCategory().equalsIgnoreCase("uncategorized"))
                cat = categoryService.findById(publishedContent.getCategory().toString()).getName().toLowerCase();
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
