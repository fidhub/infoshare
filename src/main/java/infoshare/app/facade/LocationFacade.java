package infoshare.app.facade;


import infoshare.services.Organisation.Impl.LocationServiceImpl;
import infoshare.services.Organisation.LocationService;
import infoshare.services.location.AddressTypeService;
import infoshare.services.location.ContactTypeService;
import infoshare.services.location.Impl.AddressTypeServiceImpl;
import infoshare.services.location.Impl.ContactTypeServiceImpl;
import infoshare.services.location.Impl.LocationTypeServiceImpl;
import infoshare.services.location.LocationTypeService;

/**
 * Created by hashcode on 2015/09/07.
 */
public class LocationFacade {

    private static ContactTypeService contactListService = new ContactTypeServiceImpl();
    private static LocationTypeService locationTypeService = new LocationTypeServiceImpl();
    private static LocationService locationService = new LocationServiceImpl();
    private static AddressTypeService addressTypeService = new AddressTypeServiceImpl();

    public static ContactTypeService getContactTypeServiceInstance(){return contactListService ;}
    public static LocationTypeService getLocationTypeServiceInstance(){return locationTypeService  ;}
    public static LocationService getLocationServiceInstance(){return  locationService;}
    public static AddressTypeService getAddressTypeServiceInstance(){return addressTypeService ;}

}
