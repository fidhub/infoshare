package infoshare.client.content.content.tables;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.CategoryFacade;
import infoshare.app.facade.ContentFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.client.content.MainLayout;
import infoshare.domain.content.EditedContent;
import infoshare.services.ContentFiles.category.CategoryService;
import infoshare.services.ContentFiles.content.EditedContentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/24.
 */
public class EditTable extends Table{

    @Autowired
    private EditedContentService editedContentService = ContentFacade.editedContentService;
    private CategoryService categoryService = CategoryFacade.categoryService;
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
                addContainerProperty("Date Created",String.class,null);
                try {
                    editedContentService.findAll(OrganisationUtil.getCompanyCode()) //TODO
                            .stream()
                            .filter(content -> content != null)
                            .collect(Collectors.toList())
                            .stream()
                            .filter(cont -> cont.getState().equalsIgnoreCase("Active"))
                            .collect(Collectors.toList())
                            .stream()
                            .filter(cont -> cont.getStatus().equalsIgnoreCase("Edited"))
                            .collect(Collectors.toList())
                            .forEach(this::loadTable);
                }catch (Exception e){
                }
                setNullSelectionAllowed(false);
                setSelectable(true);
                setImmediate(true);
            }

        public void loadTable(EditedContent editedContent) {
            DateFormat formatter = new SimpleDateFormat("dd MMMMMMM yyyy");
            String cat;
            if(!editedContent.getCategory().toLowerCase().equalsIgnoreCase("uncategorized")) {
                cat = categoryService.findById(editedContent.getCategory().toString().trim()).getName();
        }else cat = editedContent.getCategory().toString().toLowerCase();
        try {
            addItem(new Object[]{
                    editedContent.getTitle(),
                    cat,
                    editedContent.getCreator(),
                    formatter.format(editedContent.getDateCreated())
            }, editedContent.getId());
        } catch (Exception r) {
        }
    }

}
