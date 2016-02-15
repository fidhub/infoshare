package infoshare.RestApi;

/**
 * Created by codex on 2015/07/21.
 */
public class UrlPath {

    public static final  String URL ="http://kujali.cput.ac.za";

    public static class  AddressLinks {
        public static final String POST = URL + "/api/address/create"; // @controllers.AddressController.createAddress
        public static final String GET_ID = URL + "/api/address/get/";  //@controllers.AddressController.getAddress(id)
    }

    public static class  ContactLink{
        public static final String POST    = URL+"/api/contact/create";  // @controllers.ContactController.createContact
        public static final String GET_ID  = URL+"/api/contact/get/" ;   //@controllers.ContactController.getContact(id)
    }

    public static class CategoryLinks {
        public static final String POST    = URL+"/api/cat/create" ;  //@controllers.CategoryController.create
        public static final String GET_ID  = URL+"/api/cat/";        //@controllers.CategoryController.getCategory(id)
        public static final String PUT     = URL+"/api/cat/update" ;  //@controllers.CategoryController.update
        public static final String GETALL  = URL+"/api/cat/get/cats" ;  //@controllers.CategoryController.getAll

    }

    public static class RawLinks{

       public static final String POST    = URL+"/api/cont/raw/create";     //@controllers.RawContentController.create
       public static final String GET_ID  = URL+"/api/cont/raw/";           //@controllers.RawContentController.getContent(id)
       public static final String PUT     = URL+"/api/cont/raw/update";     //@controllers.RawContentController.update
       public static final String GETALL  = URL+"/api/cont/raw/get/conts";  //@controllers.RawContentController.getAll
    }

    public static class EditedLinks{

        public static final String POST    = URL+"/api/cont/ed/create";  // @controllers.EditedContentController.create
        public static final String GET_ID  = URL+"/api/cont/ed/";  //@controllers.EditedContentController.getContent(id)
        public static final String PUT     = URL+"/api/cont/ed/update";  // @controllers.EditedContentController.update
        public static final String GETALL  = URL+"/api/cont/ed/get/conts";  // @controllers.EditedContentController.getAll
    }

    public static class PublishedLinks {
        public static final String POST   = URL + "/api/cont/pub/create"; // @controllers.PublishedContentController.create
        public static final String GET_ID = URL + "/api/cont/pub/"; // @controllers.PublishedContentController.getContent(id)
        public static final String PUT    = URL + "/api/cont/pub/update"; // @controllers.PublishedContentController.update
        public static final String GETALL = URL + "/api/cont/pub/get/conts"; // @controllers.PublishedContentController.getAll    }
    }

    public static class MediaLinks{


        public static final String POST   = URL + "/api/media/create"; // @controllers.MediaController.create
        public static final String GET_ID = URL + "/api/media/"; // @controllers.MediaController.getContent(contentId,id)
        public static final String PUT    = URL + "/api/media/update"; // @controllers.MediaController.update
        public static final String GETALL = URL + "/api/media/get/"; // @controllers.MediaController.getAll(contentId)
    }

    public static class MediaUploadLinks {
        public static final String POST   = URL + "/api/upload"; //@controllers.FilesController.upload
        public static final String GET_ID = URL + "/api/static/"; //@controllers.FilesController.getFile(id,filename)

    }
    public static class RoleLinks {
        public static final String POST    = URL+"/api/role/create";   // @controllers.RoleController.create
        public static final String GET_ID  = URL+"/api/role/";   // @controllers.RoleController.getRole(id)
        public static final String PUT     = URL+"/api/role/update";   // @controllers.RoleController.update
        public static final String GETALL  = URL+"/api/role/get/roles";   // @controllers.RoleController.getAll
    }

    public static class ContentLinks {
        public static final String POST    = URL+"/api/cont/create";    //@controllers.CategoryController.create
        public static final String GET_ID  = URL+"/api/cont/";          //@controllers.CategoryController.getCategory(id)
        public static final String PUT     = URL+"/api/cont/update";    //@controllers.CategoryController.update
        public static final String GETALL  = URL+"/api/cont/get/conts";      //@controllers.CategoryController.getAll
        public static final String Edited = URL+"/api/cont/edited/";   // @controllers.ContentController.Edited(id)
        public static final String Plublished  = URL+"/api/cont/published/";   // @controllers.ContentController.Edited(id)
    }

     public static class ContentTypeLinks {
        public static final String POST    = URL+"/api/ctype/create"; // @controllers.ContntTypeController.create
        public static final String GET_ID  = URL+"/api/ctype/"; // @controllers.ContntTypeController.getContentType(id)
        public static final String PUT     = URL+"/api/ctype/update"; // @controllers.ContntTypeController.update
        public static final String GETALL  = URL+"/api/ctype/get/ctypes"; // @controllers.ContntTypeController.getAll
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