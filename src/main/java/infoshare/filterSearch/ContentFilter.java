package infoshare.filterSearch;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by codex on 2015/07/14.
 */
public class ContentFilter {
    private ContentService contacts = new ContentServiceImp();
    private CategoryService categoryService = new CategoryServiceImpl();
    public TextField field = new TextField();
    public ContentFilter() {
        getField();
    }
    public synchronized List<Content> findAll(String stringFilter) {
        DateFormat formatter = new SimpleDateFormat("dd - MMMMMMM - yyyy");
        ArrayList arrayList = new ArrayList();
        String cat;
        for (Content content : contacts.findAll()) {
            if(!content.getCategory().equalsIgnoreCase("uncategorized"))
                cat = categoryService.find(content.getCategory().toString()).getName().toLowerCase();
                else cat = content.getCategory().toString().toLowerCase();

            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || content.getTitle().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        ||cat.contains(stringFilter.toLowerCase())
                        || content.getCreator().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        || content.getSource().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        ||formatter.format(content.getDateCreated()).toString().toLowerCase()
                        .contains(stringFilter.toLowerCase());

                if (passesFilter) {
                    arrayList.add(content);
                }
            } catch (Exception ex) {
                Logger.getLogger(ex.getLocalizedMessage());
            }
        }

        return arrayList;
    }
    private TextField getField(){
        field.setInputPrompt("Filter content ...");
        field.setWidth("260px");
        field.setIcon(FontAwesome.FILTER);
        field.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        field.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        return field;
    }


}
