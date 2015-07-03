package infoshare.services.category.Impl;

import infoshare.domain.Category;
import infoshare.services.category.CategoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by codex on 2015/06/29.
 */
public class CategoryServiceImpl implements CategoryService {
    static Map<String,Category> categories = null;

    public CategoryServiceImpl() {
        if(categories == null){
            categories = new HashMap<>();
            Category category1 = new Category.Builder("Pregnancy").description("What pregnant people should eat").id("1").build();
            Category category2 = new Category.Builder("Emergency").description("How to attend to emergency patients").id("2").build();
            Category category3 = new Category.Builder("Pharmacy").description("How patients should be given treatment").id("3").build();
            Category category4 = new Category.Builder("Dental Care").description("How to handle patients with sore teeth").id("4").build();
            Category category5 = new Category.Builder("Circumcision").description("How Long must the patients stay to complete the process").id("5").build();

            categories.put(category1.getId(),category1);
            categories.put(category2.getId(),category2);
            categories.put(category3.getId(),category3);
            categories.put(category4.getId(),category4);
            categories.put(category5.getId(),category5);
        }
    }

    @Override
    public Category find(String s) {
       return categories.get(s);
    }

    @Override
    public Category save(Category entity) {
        categories.put(entity.getId(),entity);
        return categories.get(entity.getId());
    }

    @Override
    public Category merge(Category entity) {
        categories.put(entity.getId(),entity);
        return categories.get(entity.getId());
    }

    @Override
    public void remove(Category entity) {
        categories.remove(entity.getId());
    }

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(categories.values());
    }
}
