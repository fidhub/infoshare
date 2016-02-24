package infoshare.restapi.Content;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContentBaseUrl {

    public static class Raw {

        public static final String POST = "/api/cont/raw/create";  //@controllers.RawContentController.create
        public static final String GET = "/api/cont/raw/";  //@controllers.RawContentController.getContent(id)
        public static final String PUT = "/api/cont/raw/update";  //@controllers.RawContentController.update
        public static final String GET_ALL = "/api/cont/raw/get/conts";  //@controllers.RawContentController.getAll
    }

    public static class Edited {

        public static final String POST = "/api/cont/ed/create";    //@controllers.EditedContentController.create
        public static final String GET = "/api/cont/ed//";    //@controllers.EditedContentController.getContent(id)
        public static final String PUT = "/api/cont/ed/update";    //@controllers.EditedContentController.update
        public static final String GET_ALL = "/api/cont/ed/get/conts";    //@controllers.EditedContentController.getAll
    }
    public static class Published {

        public static final String POST = "/api/cont/pub/create";      //@controllers.PublishedContentController.create
        public static final String GET = "/api/cont/pub/";      //@controllers.PublishedContentController.getContent(id)
        public static final String PUT = "/api/cont/pub/update";      //@controllers.PublishedContentController.update
        public static final String GET_ALL = "/api/cont/pub/get/conts";      //@controllers.PublishedContentController.getAll
    }
}
