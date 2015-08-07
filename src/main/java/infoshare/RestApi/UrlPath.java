package infoshare.RestApi;

import java.util.List;

/**
 * Created by codex on 2015/07/21.
 */
public class UrlPath {

    public static final  String URL ="http://10.47.2.247:9000";
    public static List<String> isEdited;
    public static class  AddressLinks {
        public static final String POST = URL + "/api/address/create"; // @controllers.AddressController.createAddress
        public static final String GET_ID = URL + "/api/address/get/";  //@controllers.AddressController.getAddress(id)
//        public static final String PUT = URL + "/api/cat/update";  //@controllers.CategoryController.update
//        public static final String GETALL = URL + "/api/cat/get/cats";  //@controllers.CategoryController.getAll
    }

    public static class  ContactLink{
        public static final String POST    = URL+"/api/contact/create"; // @controllers.ContactController.createContact
        public static final String GET_ID  = URL+"/api/contact/get/" ;  //@controllers.ContactController.getContact(id)
//        public static final String PUT     = URL+"/api/cat/update" ;  //@controllers.CategoryController.update
//        public static final String GETALL  = URL+"/api/cat/get/cats" ;  //@controllers.CategoryController.getAll
}

    public static class CategoryLinks {
        public static final String POST    = URL+"/api/cat/create" ;  //@controllers.CategoryController.create
        public static final String GET_ID  = URL+"/api/cat/" ;        //@controllers.CategoryController.getCategory(id)
        public static final String PUT     = URL+"/api/cat/update" ;  //@controllers.CategoryController.update
        public static final String GETALL  = URL+"/api/cat/get/cats" ;  //@controllers.CategoryController.getAll
    }

    public static class ContentLinks {
        public static final String POST    = URL+"/api/cont/create";    //@controllers.CategoryController.create
        public static final String GET_ID  = URL+"/api/cont/";          //@controllers.CategoryController.getCategory(id)
        public static final String PUT     = URL+"/api/cont/update";    //@controllers.CategoryController.update
        public static final String GETALL  = URL+"/api/cont/get/conts";      //@controllers.CategoryController.getAll
        public static final String isEditedAndPlublished  = URL+"/api/cont/edited/";   // @controllers.ContentController.isEditedAndPlublished(id)
        public static final String isPlublished  = URL+"/api/cont/published/";   // @controllers.ContentController.isEditedAndPlublished(id)
    }

     public static class ContentTypeLinks {
        public static final String POST    = URL+"/api/ctype/create"; // @controllers.ContntTypeController.create
        public static final String GET_ID  = URL+"/api/ctype/"; // @controllers.ContntTypeController.getContentType(id)
        public static final String PUT     = URL+"/api/ctype/update"; // @controllers.ContntTypeController.update
        public static final String GETALL  = URL+"/api/ctype/get/ctypes"; // @controllers.ContntTypeController.getAll
    }

     public static class RoleLinks {
        public static final String POST    = URL+"/api/role/create";   // @controllers.RoleController.create
        public static final String GET_ID  = URL+"/api/role/";   // @controllers.RoleController.getRoles(id)
        public static final String PUT     = URL+"/api/role/update";   // @controllers.RoleController.update
        public static final String GETALL  = URL+"/api/role/get/roles";   // @controllers.RoleController.getAll
    }

    public static class SourceLinks {
        public static final String POST    = URL+"/api/src/create";    //@controllers.SourceController.create
        public static final String GET_ID  = URL+"/api/src/";    //@controllers.SourceController.getSource(id)
        public static final String PUT     = URL+"/api/src/update";    //@controllers.SourceController.update
        public static final String GETALL  = URL+"/api/src/get/srcs";    //@controllers.SourceController.getAll
    }

    public static class UserLinks {
        public static final String POST    = URL+"/api/user/create";    //@controllers.UserController.create
        public static final String GET_ID  = URL+"/api/user/";    //@controllers.UserController.getUser(id)
        public static final String PUT     = URL+"/api/user/update";    //@controllers.UserController.update
        public static final String GETALL  = URL+"/api/user/get/users";    //@controllers.UserController.getAll
    }

}