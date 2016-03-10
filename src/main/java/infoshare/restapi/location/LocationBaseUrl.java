package infoshare.restapi.location;

import infoshare.app.conf.RestUtil;

/**
 * Created by user9 on 2016/03/03.
 */
public class LocationBaseUrl {

    public static class AddressType {
        public static final String POST = RestUtil.URL + "/api/location/addresstype/post"; //@controllers.location.AddressTypeController.createOrUpdate
        public static final String GET_ID = RestUtil.URL + "/api/location/addresstype/get/"; //@controllers.location.AddressTypeController.getById(id)
        public static final String GET_ALL = RestUtil.URL + "/api/location/addresstypes/get"; //@controllers.location.AddressTypeController.getAll
    }

    public static class ContactType {
        public static final String POST = RestUtil.URL + "/api/location/contacttype/post"; //@controllers.location.ContactTypeController.createOrUpdate
        public static final String GET_ID = RestUtil.URL + "/api/location/contacttype/get/"; //@controllers.location.ContactTypeController.getById(id)
        public static final String GET_ALL = RestUtil.URL + "/api/location/contacttypes/get"; //@controllers.location.ContactTypeController.getAll
    }

    public static class LocationType {
        public static final String POST = RestUtil.URL + "/api/location/locationtype/post"; //@controllers.location.LocationTypeController.createOrUpdate
        public static final String GET_ID = RestUtil.URL + "/api/location/locationtype/get/"; //@controllers.location.LocationTypeController.getById(id)
        public static final String GET_ALL = RestUtil.URL + "/api/location/locationtypes/get"; //@controllers.location.LocationTypeController.getAll
    }
}
