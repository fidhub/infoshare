package infoshare.services.location;

import infoshare.app.conf.RestUtil;
import infoshare.domain.location.AddressType;
import infoshare.restapi.location.LocationBaseUrl;
import infoshare.services.Services;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by user9 on 2016/03/01.
 */
@Component
public interface AddressTypeService extends Services<AddressType,String> {
}
