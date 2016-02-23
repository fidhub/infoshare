package infoshare.restapi.common.util;


import infoshare.app.conf.RestUtil;
import infoshare.domain.StorageUrl;
import infoshare.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2016/01/06.
 */
public class StorageUrlAPI {
    public static StorageUrl save(StorageUrl status) {
        RestUtil.save(CommonBaseURI.StorageUrl.POST, status, StorageUrl.class);
        return status;
    }

    public static StorageUrl getById(String id) {
        return RestUtil.getById(CommonBaseURI.StorageUrl.GET_ID, id, StorageUrl.class);
    }

    public static Set<StorageUrl> getAllLinks() {
        return RestUtil.getAll(CommonBaseURI.StorageUrl.GETALL, StorageUrl.class);
    }
}
