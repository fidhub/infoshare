package infoshare.app.facade;


import infoshare.services.location.AddressTypeService;
import infoshare.services.location.ContactListService;
import infoshare.services.location.Impl.AddressTypeServiceImpl;
import infoshare.services.location.Impl.ContactListServiceImpl;
import infoshare.services.location.Impl.LocationServiceImpl;
import infoshare.services.location.Impl.LocationTypeServiceImpl;
import infoshare.services.location.LocationService;
import infoshare.services.location.LocationTypeService;

/**
 * Created by hashcode on 2015/09/07.
 */
public class LocationFacade {

    public final static ContactListService contactListService = new ContactListServiceImpl();
    public final static LocationTypeService locationTypeService = new LocationTypeServiceImpl();
    public final static LocationService locationService = new LocationServiceImpl();
    public final static AddressTypeService addressTypeService = new AddressTypeServiceImpl();

}
