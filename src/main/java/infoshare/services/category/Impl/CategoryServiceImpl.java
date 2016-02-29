package infoshare.services.category.Impl;

import infoshare.domain.content.Category;
import infoshare.restapi.Category.CategoryAPI;
import infoshare.services.category.CategoryService;

import java.util.Set;

/**
 * Created by codex on 2015/06/29.
 */
public class CategoryServiceImpl implements CategoryService {

    @Override
    public Category findById(String s) {
        return CategoryAPI.findById(s);
    }

    @Override
    public Category save(Category entity) {
        return CategoryAPI.save(entity);
    }

    @Override
    public Category update(Category entity) {
        return CategoryAPI.update(entity);
    }

    @Override
    public void delete(Category entity) {
        CategoryAPI.save(entity);
    }

    @Override
    public Set<Category> findAll() {
        return CategoryAPI.findAll();
    }
}
