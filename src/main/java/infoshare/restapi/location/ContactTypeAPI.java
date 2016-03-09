package infoshare.restapi.location;

import infoshare.app.conf.RestUtil;
<<<<<<< HEAD
import infoshare.domain.location.ContactType;

=======
import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.location.AddressType;
import infoshare.domain.location.ContactType;

import java.util.Map;
>>>>>>> e5d284111b73c308d9cc6b33b7bed19f1a4d34b9
import java.util.Set;

/**
 * Created by user9 on 2016/03/01.
 */
public class ContactTypeAPI {
    public static ContactType save(ContactType entity){
        return RestUtil.save(LocationBaseUrl.ContactType.POST, entity, ContactType.class);
    }
    public static ContactType findById(String id){
        return RestUtil.getById(LocationBaseUrl.ContactType.GET_ID,id,ContactType.class);
    }
    public static Set<ContactType> findAll(){
        return RestUtil.getAll(LocationBaseUrl.ContactType.GET_ALL,ContactType.class);
    }
}
