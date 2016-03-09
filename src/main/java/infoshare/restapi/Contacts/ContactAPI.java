package infoshare.restapi.Contacts;

import infoshare.app.conf.RestUtil;
import infoshare.domain.location.ContactType;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContactAPI {

    public static ContactType save(ContactType contact){
        return RestUtil.save(ContactBaseUrl.Contact.POST,contact,ContactType.class);
    }
    public static ContactType findById(String id){
        return RestUtil.getById(ContactBaseUrl.Contact.GET,id,ContactType.class);
    }
}
