package infoshare.app.facade;


import infoshare.services.ContentFiles.category.CategoryService;
import infoshare.services.ContentFiles.category.Impl.CategoryServiceImpl;

/**
 * Created by user9 on 2016/02/23.
 */
public class CategoryFacade {
    public final static CategoryService categoryService = new CategoryServiceImpl();
}
