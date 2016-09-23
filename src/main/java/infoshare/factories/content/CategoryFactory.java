package infoshare.factories.content;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.content.Category;

import java.util.Map;

/**
 * Created by user9 on 2016/03/01.
 */
public class CategoryFactory {

    public static Category getCategory(Map<String,String > categoryVals){
        Category category = new Category.Builder()
                .id(KeyGenerator.getEntityId())
                .name(categoryVals.get("name"))
                .description(categoryVals.get("description"))
                .build();
        return category;
    }
}
