package infoshare.app.facade;
import infoshare.services.ContentFiles.ContentType.ContentTypeService;
import infoshare.services.ContentFiles.ContentType.Impl.ContentTypeServiceImpl;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContentTypeFacade {
    private final static ContentTypeService contentTypeService = new ContentTypeServiceImpl();
    public  static ContentTypeService getContentTypeInstance(){return contentTypeService;}
}
