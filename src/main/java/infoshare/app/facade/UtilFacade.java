package infoshare.app.facade;


import infoshare.services.utililties.Impl.MailServiceImpl;
import infoshare.services.utililties.Impl.StatusServiceImpl;
import infoshare.services.utililties.Impl.StorageUrlServiceImpl;
import infoshare.services.utililties.MailService;
import infoshare.services.utililties.StatusService;
import infoshare.services.utililties.StorageUrlService;

/**
 * Created by garran on 2015/09/13.
 */
public class UtilFacade {


    public final static MailService mailService = new MailServiceImpl();
    public final static StorageUrlService storageUrlService = new StorageUrlServiceImpl();
    public final static StatusService statusService = new StatusServiceImpl();
}
