package infoshare.client.content.systemValues.tables;

import com.vaadin.ui.Table;
import infoshare.client.content.MainLayout;
import infoshare.domain.Category;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by codex on 2015/06/26.
 */
public class CategoryTable extends Table {

    @Autowired
    private CategoryService categoryService = new CategoryServiceImpl();

    private MainLayout mainLayout;

    public CategoryTable(MainLayout mainApp){
        this.mainLayout = mainApp;
        setSizeFull();
        addContainerProperty("Tip Category", String.class, null);
        addContainerProperty("Description", String.class,null);

        List<Category> categories = categoryService.findAll();

        for (Category category: categories){
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
