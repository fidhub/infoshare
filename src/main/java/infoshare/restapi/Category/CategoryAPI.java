package infoshare.restapi.Category;

import infoshare.app.conf.RestUtil;
import infoshare.domain.Category;

import java.util.Set;

/**
 * Created by user9 on 2016/02/23.
 */
public class CategoryAPI {
    public static Category save(Category category){
        return RestUtil.save(CategoryBaseUrl.Category.POST,category,Category.class);
    }
    public static Category findById(String id){
        return RestUtil.getById(CategoryBaseUrl.Category.GET,id,Category.class);
    }
    public static Category update(Category category){
        return RestUtil.update(CategoryBaseUrl.Category.PUT,category);
    }
    public static Set<Category> findAll(){return RestUtil.getAll(CategoryBaseUrl.Category.GET_ALL,Category.class); }
}
