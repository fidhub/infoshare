package infoshare.app.facade;


import infoshare.services.location.AddressTypeService;
import infoshare.services.location.ContactTypeService;
import infoshare.services.location.Impl.AddressTypeServiceImpl;
import infoshare.services.location.Impl.ContactTypeServiceImpl;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContactFacade {
    private final static AddressTypeService addressService = new AddressTypeServiceImpl();
    private final static ContactTypeService contactService = new ContactTypeServiceImpl();
    public static AddressTypeService getAddressInstance(){return addressService;}
    public static ContactTypeService getContactInstance(){return contactService;}

}
