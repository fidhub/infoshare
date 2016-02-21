package infoshare.filterSearch;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.domain.EditedContent;
import infoshare.services.EditedContent.EditedContentService;
import infoshare.services.EditedContent.Impl.EditedContentServiceImpl;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by user9 on 2016/02/12.
 */
public class EditedContentFilter {
    private EditedContentService editedContentService = new EditedContentServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
    public TextField field = new TextField();
    public EditedContentFilter() {
        getField();
    }

    public synchronized List<EditedContent> findAll(String stringFilter,String state) {
        DateFormat formatter = new SimpleDateFormat("dd - MMMMMMM - yyyy");
        ArrayList arrayList = new ArrayList();
        String cat;
        for (EditedContent EditedContent : editedContentService.findAll()
                .stream().filter(cont ->cont.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList())
                .stream().filter(cont ->cont.getStatus().equalsIgnoreCase("edited"))
                .collect(Collectors.toList())
                ) {
            if(!EditedContent.getCategory().equalsIgnoreCase("uncategorized"))
                cat = categoryService.find(EditedContent.getCategory().toString()).getName().toLowerCase();
            else cat = EditedContent.getCategory().toString().toLowerCase();

            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || EditedContent.getTitle().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        ||cat.contains(stringFilter.toLowerCase())
                        || EditedContent.getCreator().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        || EditedContent.getSource().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        ||formatter.format(EditedContent.getDateCreated()).toString().toLowerCase()
                        .contains(stringFilter.toLowerCase());

                if (passesFilter) {
                    arrayList.add(EditedContent);
                }
            } catch (Exception ex) {
                Logger.getLogger(ex.getLocalizedMessage());
            }
        }

        return arrayList;
    }
    private TextField getField(){
        field.setInputPrompt("Filter Edited Content ...");
        field.setWidth("260px");
        field.setIcon(FontAwesome.FILTER);
        field.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        field.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        return field;
    }

}
