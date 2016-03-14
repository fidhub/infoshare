package infoshare.restapi.common.util;

import infoshare.app.conf.RestUtil;
import infoshare.domain.util.Status;
import infoshare.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by user9 on 2016/03/09.
 */
public class StatusAPI {
    public static Status save(Status status) {
      return RestUtil.save(CommonBaseURI.Status.POST, status, Status.class);
    }

    public static Status findById(String id) {
        return RestUtil.getById(CommonBaseURI.Status.GET_ID, id, Status.class);

    }

    public static Set<Status> findAll() {
        return RestUtil.getAll(CommonBaseURI.Status.GETALL, Status.class);
    }
}
