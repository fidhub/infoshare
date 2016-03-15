package infoshare.services.Organisation.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.organisation.Location;
import infoshare.restapi.Organisation.LocationAPI;
import infoshare.services.Organisation.LocationService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/03/03.
 */
@Service
@SpringComponent
public class LocationServiceImpl implements LocationService {
    private static LocationServiceImpl locationService =null;

    private LocationServiceImpl(){}

    public  static LocationServiceImpl getInstance(){
        if(locationService ==null) {
            return new LocationServiceImpl();
        }
        return locationService;
    }
    @Override
    public Location save(Location location) {
        return LocationAPI.save(location);
    }

    @Override
    public Location findById(String org, String id) {
        return LocationAPI.findById(org, id);
    }

    @Override
    public Set<Location> findAll(String org) {
        return LocationAPI.findAll(org);
    }

    @Override
    public Location numberofChildred(String id) {
        return null;
    }

}
