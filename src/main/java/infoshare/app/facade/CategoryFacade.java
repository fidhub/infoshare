package infoshare.app.facade;

import infoshare.domain.Category;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;

/**
 * Created by user9 on 2016/02/23.
 */
public class CategoryFacade {
    public final static CategoryService categoryService = new CategoryServiceImpl();
}
