package infoshare.restapi.Category;

/**
 * Created by user9 on 2016/02/23.
 */
public class CategoryBaseUrl {

    public static class Category {
      public static final String POST ="/api/cat/create";   //@controllers.CategoryController.create
      public static final String GET ="/api/cat/";          //@controllers.CategoryController.getCategory(id)
      public static final String PUT ="/api/cat/update";    //@controllers.CategoryController.update
      public static final String GET_ALL ="/api/cat/get/cats";  //@controllers.CategoryController.getAll
    }
}
