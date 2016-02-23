package infoshare.restapi.Contacts;

import infoshare.app.conf.RestUtil;
import infoshare.domain.Address;

/**
 * Created by user9 on 2016/02/23.
 */
public class AddressAPI {

    public static Address save(Address address) {
        return RestUtil.save(ContactBaseUrl.Address.POST, address, Address.class);
    }
    public static Address findById(String id){
        return RestUtil.getById(ContactBaseUrl.Address.GET,id,Address.class);
    }
}
