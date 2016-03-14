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

    private static MailService mailService = new MailServiceImpl();
    private static StorageUrlService storageUrlService = new StorageUrlServiceImpl();
    private static StatusService statusService = new StatusServiceImpl();

    public static MailService getMailServiceInstance(){return mailService;}
    public static StorageUrlService getStorageUrlServiceInstance(){return storageUrlService;}
    public static StatusService getStatusServiceInstance(){return statusService;}


}
