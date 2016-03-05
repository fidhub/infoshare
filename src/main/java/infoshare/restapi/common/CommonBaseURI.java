package infoshare.restapi.common;


import infoshare.app.conf.RestUtil;

/**
 * Created by hashcode on 2015/11/10.
 */
public class CommonBaseURI {

    public static class Role {
        public static final String POST = RestUtil.URL + "/api/demographics/role/post";
        public static final String GET_ID = RestUtil.URL + "/api/demographics/role/get/";
        public static final String GETALL = RestUtil.URL + "/api/demographics/roles/get";

    }

    public static class Mail {
        public static final String POST = RestUtil.URL + "/api/util/mail/post";
        public static final String GET_ID = RestUtil.URL + "/api/util/mail/get/";
        public static final String GETALL = RestUtil.URL + "/api/util/mails/get";

    }

    public static class StorageUrl {
        public static final String POST = RestUtil.URL + "/api/util/storageurl/post";
        public static final String GET_ID = RestUtil.URL + "/api/util/storageurl/get/";
        public static final String GETALL = RestUtil.URL + "/api/util/storageurls/get";

    }
    public static class AddressType {
        public static final String POST = RestUtil.URL + "/api/location/addresstype/post";
        public static final String GET_ID = RestUtil.URL + "/api/location/addresstype/get/";
        public static final String GETALL = RestUtil.URL + "/api/location/addresstypes/get";

    }

    public static class ContactType {
        public static final String POST = RestUtil.URL + "/api/location/contacttype/post";
        public static final String GET_ID = RestUtil.URL + "/api/location/contacttype/get/";
        public static final String GETALL = RestUtil.URL + "/api/location/contacttypes/get";

    }

    public static class LocationType {
        public static final String POST = RestUtil.URL + "/api/location/locationtype/post";
        public static final String GET_ID = RestUtil.URL + "/api/location/locationtype/get/";
        public static final String GETALL = RestUtil.URL + "/api/location/locationtypes/get";

    }


    public static class Status {
        public static final String POST = RestUtil.URL + "/api/util/status/post";
        public static final String GET_ID = RestUtil.URL + "/api/util/status/get/";
        public static final String GETALL = RestUtil.URL + "/api/util/statuses/get";

    }

    public static class Location {
        public static final String POST = RestUtil.URL + "/api/location/post";
        public static final String GET_ID = RestUtil.URL + "/api/location/get/";
        public static final String GETALL = RestUtil.URL + "/api/locations/get";

    }


}
