package infoshare.app.facade;

import infoshare.services.ContentFiles.source.SourceService;
import infoshare.services.ContentFiles.source.Impl.SourceServiceImpl;

/**
 * Created by user9 on 2016/02/23.
 */
public class SourceFacade {
    public final static SourceService sourceService = new SourceServiceImpl();
}
