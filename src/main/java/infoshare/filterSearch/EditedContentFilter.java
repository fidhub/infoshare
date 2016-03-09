package infoshare.filterSearch;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.CategoryFacade;
import infoshare.app.facade.ContentFacade;
import infoshare.domain.content.EditedContent;
<<<<<<< HEAD
import infoshare.services.ContentFiles.category.CategoryService;
import infoshare.services.ContentFiles.content.EditedContentService;
=======
import infoshare.services.ContentFiles.content.EditedContentService;
import infoshare.services.ContentFiles.category.CategoryService;
>>>>>>> e5d284111b73c308d9cc6b33b7bed19f1a4d34b9

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by user9 on 2016/02/12.
 */
public class EditedContentFilter {
    private EditedContentService editedContentService = ContentFacade.editedContentService;
    private CategoryService categoryService = CategoryFacade.categoryService;
    public TextField field = new TextField();
    public EditedContentFilter() {
        getField();
    }
    public synchronized List<EditedContent> findAll(String stringFilter,String state) {
        DateFormat formatter = new SimpleDateFormat("dd MMMMMMM yyyy");
        ArrayList arrayList = new ArrayList();
        String cat;
        for (EditedContent EditedContent : editedContentService.findAll("org").stream()
                .filter(cont -> cont.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList()).stream()
                .filter(cont -> cont.getStatus().equalsIgnoreCase("Edited"))
                .collect(Collectors.toList())) {
            if(!EditedContent.getCategory().toLowerCase().equalsIgnoreCase("uncategorized")) {
                cat = categoryService.findById(EditedContent.getCategory().toString().trim()).getName();
            }else cat = EditedContent.getCategory().toString().toLowerCase();
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
                System.out.println(ex.getMessage());
            }
        }

        return arrayList;
    }
    private TextField getField(){
        field.setInputPrompt("Filter Edited ContentFiles ...");
        field.setWidth("260px");
        field.setIcon(FontAwesome.FILTER);
        field.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        field.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        return field;
    }

}
