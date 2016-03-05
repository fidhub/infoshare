package infoshare.factories.util;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.util.Status;

/**
 * Created by user9 on 2016/03/02.
 */
public class StatusFactory {
    public  static Status getStatus(String name, String value){
        Status status = new Status.Builder()
                .id(KeyGenerator.getEntityId())
                .name(name)
                .value(value)
                .state(DomainState.ACTIVE.name())
                .build();
        return status;
    }
}
