package infoshare.filterSearch;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        ArrayList arrayList = new ArrayList();
        for (Content contact : contacts.findAll()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || contact.getTitle().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        ||categoryService.find(contact.getCategory().toString()).getName().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        ||contact.getCreator().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        ||contact.getSource().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        ||contact.getDateCreated().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase());

                if (passesFilter) {
                    arrayList.add(contact);
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
