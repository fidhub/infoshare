package infoshare.factories;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.Category;

import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class CategoryFactory {

    public static Category getCategory(Map<String,String> categoryVals){

        Category category = new Category.Builder(categoryVals.get("name"))
                .id(KeyGenerator.getEntityId())
                .description(categoryVals.get("description"))
                .build();

        return category;

    }
}
