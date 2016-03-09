package infoshare.services.ContentFiles.category.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.content.Category;
import infoshare.restapi.ContentFiles.Category.CategoryAPI;
import infoshare.services.ContentFiles.category.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by codex on 2015/06/29.
 */
@Service
@SpringComponent
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
