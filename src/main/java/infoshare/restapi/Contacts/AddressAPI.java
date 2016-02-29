package infoshare.restapi.Contacts;

import infoshare.app.conf.RestUtil;
import infoshare.domain.person.PersonAddress;

/**
 * Created by user9 on 2016/02/23.
 */
public class AddressAPI {

    public static PersonAddress save(PersonAddress personAddress) {
        return RestUtil.save(ContactBaseUrl.Address.POST, personAddress, PersonAddress.class);
    }
    public static PersonAddress findById(String id){
        return RestUtil.getById(ContactBaseUrl.Address.GET,id,PersonAddress.class);
    }
}
