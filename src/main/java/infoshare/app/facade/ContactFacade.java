package infoshare.app.facade;

import infoshare.services.Contact.AddressService;
import infoshare.services.Contact.ContactService;
import infoshare.services.Contact.Impl.AddressServiceImpl;
import infoshare.services.Contact.Impl.ContactServiceImpl;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContactFacade {
    public final static AddressService addressService = new AddressServiceImpl();
    public final static ContactService contactService = new ContactServiceImpl();
}
