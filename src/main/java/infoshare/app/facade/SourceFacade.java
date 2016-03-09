package infoshare.app.facade;

<<<<<<< HEAD
import infoshare.services.ContentFiles.source.Impl.SourceServiceImpl;
import infoshare.services.ContentFiles.source.SourceService;
=======
import infoshare.services.ContentFiles.source.SourceService;
import infoshare.services.ContentFiles.source.Impl.SourceServiceImpl;
>>>>>>> e5d284111b73c308d9cc6b33b7bed19f1a4d34b9

/**
 * Created by user9 on 2016/02/23.
 */
public class SourceFacade {
    public final static SourceService sourceService = new SourceServiceImpl();
}
