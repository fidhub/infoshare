package infoshare.restapi.common.location;

import hashwork.app.conf.RestUtil;
import hashwork.domain.ui.location.ContactType;
import hashwork.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/12/10.
 */
public class ContactTypeAPI {
    public static ContactType save(ContactType contactType) {
        RestUtil.save(CommonBaseURI.ContactType.POST, contactType, ContactType.class);
        return contactType;
    }

    public static ContactType findById(String id) {
        return RestUtil.getById(CommonBaseURI.ContactType.GET_ID, id, ContactType.class);

    }

    public static Set<ContactType> findAll() {
        return RestUtil.getAll(CommonBaseURI.ContactType.GETALL, ContactType.class);
    }
}
