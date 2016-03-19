package infoshare.client.content.common.systemValues.tables;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.CategoryFacade;
import infoshare.client.content.MainLayout;
import infoshare.domain.content.Category;
import infoshare.services.ContentFiles.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by codex on 2015/06/26.
 */
public class CategoryTable extends Table {

    @Autowired
    private CategoryService categoryService = CategoryFacade.categoryService;

    private MainLayout mainLayout;

    public CategoryTable(MainLayout mainApp){
        this.mainLayout = mainApp;
        setSizeFull();
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Category", String.class, null);
        addContainerProperty("Description", String.class,null);

        for (Category category:  categoryService.findAll()){
            addItem(new Object[]{
                    category.getName(),
                    category.getDescription()
            },category.getId());
        }

        setSelectable(true);
        setImmediate(true);
        setNullSelectionAllowed(false);
    }

}
