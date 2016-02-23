package infoshare.app.facade;

import infoshare.services.Content.EditedContentService;
import infoshare.services.Content.Impl.EditedContentServiceImpl;
import infoshare.services.Content.Impl.PublishedContentServiceImpl;
import infoshare.services.Content.Impl.RawContentServiceImpl;
import infoshare.services.Content.PublishedContentService;
import infoshare.services.Content.RawContentService;

/**
 * Created by user9 on 2016/02/23.
 */
public class contentFacade {
    public final static RawContentService rawContentService = new RawContentServiceImpl();
    public final static EditedContentService editedContentService = new EditedContentServiceImpl();
    public final static PublishedContentService publishedContentService = new PublishedContentServiceImpl();
}
