package infoshare.services.category.Impl;

import infoshare.domain.Category;
import infoshare.services.category.CategoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codex on 2015/06/29.
 */
public class CategoryServiceImpl implements CategoryService {
    List<Category> categories = new ArrayList<>();
    @Override
    public Category find(String s) {
        Category category = null;
        for (Category category1: categories)
             if(category1.getId().equalsIgnoreCase(s))
                 category = category1;
        return category ;
    }


    @Override
    public Category save(Category entity) {
        return new Category.Builder(entity.getName())
                    .id(entity.getId())
                    .description(entity.getDescription()).build();
    }

    @Override
    public Category merge(Category entity) {
        return null;
    }

    @Override
    public void remove(Category entity) {

    }

    @Override
    public List<Category> findAll() {

        Category category1 = new Category.Builder("Pregnancy").description("What pregnant people should eat").id("1").build();
        Category category2 = new Category.Builder("Emergency").description("How to attend to emergency patients").id("2").build();
        Category category3 = new Category.Builder("Pharmacy").description("How patients should be given treatment").id("3").build();
        Category category4 = new Category.Builder("Dental Care").description("How to handle patients with sore teeth").id("4").build();
        Category category5 = new Category.Builder("Circumcision").description("How Long must the patients stay to complete the process").id("5").build();

        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        categories.add(category5);

        return categories;
    }
}
