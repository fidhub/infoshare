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

    public final static ContactTypeService contactListService =  ContactTypeServiceImpl.getInstance();
    public final static LocationTypeService locationTypeService =  LocationTypeServiceImpl.getInstance();
    public final static LocationService locationService =  LocationServiceImpl.getInstance();
    public final static AddressTypeService addressTypeService =  AddressTypeServiceImpl.getInstance();

}
