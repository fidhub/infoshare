package infoshare.restapi.ContentType;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContentTypeBaseUrl {

    public static class ContentType{
      public static final String POST="/api/ctype/create";  //@controllers.ContntTypeController.create
      public static final String GET ="/api/ctype/";  //@controllers.ContntTypeController.getContentType(id)
      public static final String PUT ="/api/ctype/update";  //@controllers.ContntTypeController.update
      public static final String GET_ALL ="/api/ctype/get/ctypes";  //@controllers.ContntTypeController.getAll
    }
}
