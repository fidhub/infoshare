package infoshare.services.Organisation;

import infoshare.domain.organisation.Location;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by user9 on 2016/03/03.
 */
@Component
public interface LocationService {
    Location save(Location location);
    Location findById(String org ,String id);
    Set<Location> findAll(String org);
<<<<<<< HEAD

    Location numberofChildred(String id);
=======
>>>>>>> e5d284111b73c308d9cc6b33b7bed19f1a4d34b9
}
