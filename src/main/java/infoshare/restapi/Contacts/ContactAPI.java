package infoshare.restapi.Contacts;

import infoshare.app.conf.RestUtil;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContactAPI {

    public static Contact save(Contact contact){
        return RestUtil.save(ContactBaseUrl.Contact.POST,contact,Contact.class);
    }
    public static Contact findById(String id){
        return RestUtil.getById(ContactBaseUrl.Contact.GET,id,Contact.class);
    }
}
