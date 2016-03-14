package infoshare.services.location;

import infoshare.app.conf.RestUtil;
import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.location.AddressType;
import infoshare.domain.location.LocationType;
import infoshare.restapi.location.*;
import infoshare.restapi.location.LocationBaseUrl;
import infoshare.services.Services;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * Created by user9 on 2016/03/01.
 */
@Component
public interface LocationTypeService extends Services<LocationType,String> {

}
