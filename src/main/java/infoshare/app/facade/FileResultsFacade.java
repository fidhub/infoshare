package infoshare.app.facade;

import infoshare.services.storage.FileResultsService;
import infoshare.services.storage.Impl.FileResultsServiceImpl;

/**
 * Created by THULEH on 2016/03/24.
 */
public class FileResultsFacade {
    public static FileResultsService fileResultsService =  FileResultsServiceImpl.getInstance();
}
