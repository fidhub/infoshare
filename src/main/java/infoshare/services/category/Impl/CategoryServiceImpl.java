package infoshare.services.category.Impl;

import infoshare.restapi.RestApiConnectorClass;
import infoshare.restapi.UrlPath;
import infoshare.domain.Category;
import infoshare.services.category.CategoryService;

import java.util.List;

/**
 * Created by codex on 2015/06/29.
 */
public class CategoryServiceImpl implements CategoryService {

    @Override
    public Category find(String s) {
       return RestApiConnectorClass.read(UrlPath.CategoryLinks.GET_ID,s,Category.class);
    }

    @Override
    public Category save(Category entity) {
        return RestApiConnectorClass.create(UrlPath.CategoryLinks.POST, entity, Category.class);
    }
    @Override
    public Category merge(Category entity) {
        return RestApiConnectorClass.update(UrlPath.CategoryLinks.PUT,entity);
    }
    @Override
    public void remove(Category entity) {
    }

    @Override
    public List<Category> findAll() {
        return  RestApiConnectorClass.readAll(UrlPath.CategoryLinks.GETALL,Category.class);
    }
}
