package infoshare.restapi.common.util;


import infoshare.app.conf.RestUtil;
import infoshare.domain.util.Status;
import infoshare.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/12/10.
 */
public class StatusAPI {

    public static Status save(Status status) {
        RestUtil.save(CommonBaseURI.Status.POST, status, Status.class);
        return status;
    }

    public static Status findById(String id) {
        return RestUtil.getById(CommonBaseURI.Status.GET_ID, id, Status.class);

    }

    public static Set<Status> findAll() {
        return RestUtil.getAll(CommonBaseURI.Status.GETALL, Status.class);
    }
}
