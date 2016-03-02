package infoshare.factories.util;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.util.Status;

import java.util.Map;

/**
 * Created by user9 on 2016/03/02.
 */
public class StatusFactory {

    private static Status getStatus(Map<String, String> statusVals){
        Status status = new Status.Builder()
                .id(KeyGenerator.getEntityId())
                .name(statusVals.get("name"))
                .value(statusVals.get("value"))
                .state(statusVals.get("state"))
                .build();
        return status;
    }
}
