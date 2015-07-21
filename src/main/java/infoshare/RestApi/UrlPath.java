package infoshare.RestApi;

/**
 * Created by codex on 2015/07/21.
 */
public class UrlPath {

    public static final  String URL ="http://10.47.2.247:9000";

    public static class ContentLinks {
        public static final String POST    = URL+"/api/cat/create";    //@controllers.CategoryController.create
        public static final String GET_ID  = URL+"/api/cat/:id   ";    //@controllers.CategoryController.getCategory(id)
        public static final String PUT     = URL+"/api/cat/update";    //@controllers.CategoryController.update
        public static final String GETALL  = URL+"/api/cat/cats";      //@controllers.CategoryController.getAll
    }
     public static class CategoryLinks {
        public static final String POST    = URL+"/api/cat/create " ;  //@controllers.CategoryController.create
        public static final String GET_ID  = URL+"/api/cat/:id    " ;  //@controllers.CategoryController.getCategory(id)
        public static final String PUT     = URL+"/api/cat/update " ;  //@controllers.CategoryController.update
        public static final String GETALL  = URL+"/api/cat/cats   " ;  //@controllers.CategoryController.getAll
    }
     public static class ContentTypeLinks {
        public static final String POST    = URL+"/api/ctype/create "; // @controllers.ContntTypeController.create
        public static final String GET_ID  = URL+"/api/ctype/:id    "; // @controllers.ContntTypeController.getContentType(id)
        public static final String PUT     = URL+"/api/ctype/update "; // @controllers.ContntTypeController.update
        public static final String GETALL  = URL+"/api/ctype/ctypes "; // @controllers.ContntTypeController.getAll
    }
     public static class RoleLinks {
        public static final String POST    = URL+"/api/role/create ";   // @controllers.RoleController.create
        public static final String GET_ID  = URL+"/api/role/:id    ";   // @controllers.RoleController.getRole(id)
        public static final String PUT     = URL+"/api/role/update ";   // @controllers.RoleController.update
        public static final String GETALL  = URL+"/api/role/roles  ";   // @controllers.RoleController.getAll
    }
 public static class SourceLinks {
        public static final String POST    = URL+"/api/src/create  ";    //@controllers.SourceController.create
        public static final String GET_ID  = URL+"/api/src/:id     ";    //@controllers.SourceController.getSource(id)
        public static final String PUT     = URL+"/api/src/update  ";    //@controllers.SourceController.update
        public static final String GETALL  = URL+"/api/src/srcs    ";    //@controllers.SourceController.getAll
    }
 public static class UserLinks {
        public static final String POST    = URL+"/api/user/create ";    //@controllers.UserController.create
        public static final String GET_ID  = URL+"/api/user/:id    ";    //@controllers.UserController.getUser(id)
        public static final String PUT     = URL+"/api/user/update ";    //@controllers.UserController.update
        public static final String GETALL  = URL+"/api/user/users  ";    //@controllers.UserController.getAll
    }

}