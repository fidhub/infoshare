package infoshare.app.facade;


import infoshare.services.location.AddressTypeService;
import infoshare.services.location.ContactTypeService;
import infoshare.services.location.Impl.AddressTypeServiceImpl;
import infoshare.services.location.Impl.ContactTypeServiceImpl;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContactFacade {
    public final static AddressTypeService addressService =  AddressTypeServiceImpl.getInstance();
    public final static ContactTypeService contactService =  ContactTypeServiceImpl.getInstance();
}
