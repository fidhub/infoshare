package infoshare.filterSearch;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.CategoryFacade;
import infoshare.app.facade.ContentFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.domain.content.RawContent;
import infoshare.services.ContentFiles.category.CategoryService;
import infoshare.services.ContentFiles.content.RawContentService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by codex on 2015/07/14.
 */
public class RawContentFilter {
    private RawContentService rawContentService = ContentFacade.rawContentService;
    private CategoryService categoryService = CategoryFacade.categoryService;
    public TextField field = new TextField();
    public RawContentFilter() {
        getField();
    }
    public synchronized List<RawContent> findAll(String stringFilter) {
        DateFormat formatter = new SimpleDateFormat("dd MMMMMMM yyyy");
        ArrayList arrayList = new ArrayList();
        String cat;
        for (RawContent rawContent : rawContentService.findAll(OrganisationUtil.getCompanyCode()).stream()
                .filter(cont -> cont.getState().equalsIgnoreCase("active"))
                .collect(Collectors.toList()).stream()
                .filter(cont -> cont.getStatus().equalsIgnoreCase("raw"))
                .collect(Collectors.toList())) {
            if(!rawContent.getCategory().toLowerCase().equalsIgnoreCase("uncategorized"))
                cat = categoryService.findById(rawContent.getCategory().toString().trim()).getName();
                else cat = rawContent.getCategory().toString().toLowerCase();

            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || rawContent.getTitle().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        ||cat.contains(stringFilter.toLowerCase())
                        || rawContent.getCreator().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        || rawContent.getSource().toString().toLowerCase()
                        .contains(stringFilter.toLowerCase())
                        ||formatter.format(rawContent.getDateCreated()).toString().toLowerCase()
                        .contains(stringFilter.toLowerCase());

                if (passesFilter) {
                    arrayList.add(rawContent);
                }
            } catch (Exception ex) {
                Logger.getLogger(ex.getLocalizedMessage());
            }
        }

        return arrayList;
    }
    private TextField getField(){
        field.setInputPrompt("Filter rawContent ...");
        field.setWidth("260px");
        field.setIcon(FontAwesome.FILTER);
        field.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        field.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        return field;
    }


}
