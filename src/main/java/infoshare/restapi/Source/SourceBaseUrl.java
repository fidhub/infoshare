package infoshare.restapi.Source;

/**
 * Created by user9 on 2016/02/23.
 */
public class SourceBaseUrl {
    public static class Source{
      public static final String  POST=" /api/src/create";    //@controllers.SourceController.create
      public static final String  GET =" /api/src/";    //@controllers.SourceController.getSource(id)
      public static final String  PUT =" /api/src/update";    //@controllers.SourceController.update
      public static final String  GET_ALL =" /api/src/get/srcs";    //@controllers.SourceController.getAll
    }
}
