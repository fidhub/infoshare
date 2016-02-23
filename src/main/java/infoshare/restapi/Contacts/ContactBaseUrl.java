package infoshare.restapi.Contacts;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContactBaseUrl {

    public static class Address {
        public static final String POST = "/api/address/create ";   //@controllers.AddressController.createAddress
        public static final String GET = "/api/address/get/";   //@controllers.AddressController.getAddress(id)
    }

    public static class Contact {
        public static final String GET = "/api/contact/get/";   //@controllers.AddressController.getContact(id)
        public static final String POST = "/api/contact/create ";   //@controllers.AddressController.createContact
    }
}