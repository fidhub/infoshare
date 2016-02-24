package infoshare.restapi.Contacts;

import infoshare.app.conf.RestUtil;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContactBaseUrl {

    public static class Address {
        public static final String POST = RestUtil.URL+ "/api/address/create ";   //@controllers.AddressController.createAddress
        public static final String GET = RestUtil.URL+ "/api/address/get/";   //@controllers.AddressController.getAddress(id)
    }

    public static class Contact {
        public static final String GET = RestUtil.URL+"/api/contact/get/";   //@controllers.AddressController.getContact(id)
        public static final String POST =RestUtil.URL+ "/api/contact/create ";   //@controllers.AddressController.createContact
    }
}