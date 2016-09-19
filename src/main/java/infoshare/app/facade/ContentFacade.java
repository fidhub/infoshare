package infoshare.app.facade;


import infoshare.services.ContentFiles.content.EditedContentService;
import infoshare.services.ContentFiles.content.Impl.EditedContentServiceImpl;
import infoshare.services.ContentFiles.content.Impl.PublishedContentServiceImpl;
import infoshare.services.ContentFiles.content.Impl.RawContentServiceImpl;
import infoshare.services.ContentFiles.content.PublishedContentService;
import infoshare.services.ContentFiles.content.RawContentService;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContentFacade {
    public final static RawContentService rawContentService =  RawContentServiceImpl.getInstance();
    public final static EditedContentService editedContentService =  EditedContentServiceImpl.getInstance();
    public final static PublishedContentService publishedContentService =  PublishedContentServiceImpl.getInstance();
}
