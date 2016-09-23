package infoshare.services.location.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.location.LocationType;
import infoshare.restapi.location.LocationTypeAPI;
import infoshare.services.location.LocationTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/03/01.
 */
@SpringComponent
@Service
public class LocationTypeServiceImpl implements LocationTypeService {
    private static LocationTypeServiceImpl locationTypeService =null;

    private LocationTypeServiceImpl(){}

    public  static LocationTypeServiceImpl getInstance(){
        if(locationTypeService ==null) {
            return new LocationTypeServiceImpl();
        }
        return locationTypeService;
    }
    @Override
    public LocationType findById(String s) {
        return LocationTypeAPI.findById(s);
    }

    @Override
    public LocationType save(LocationType entity) {
        return LocationTypeAPI.save(entity);
    }

    @Override
    public LocationType update(LocationType entity) {
        return LocationTypeAPI.save(entity);
    }

    @Override
    public void delete(LocationType entity) {
        LocationTypeAPI.save(entity);
    }

    @Override
    public Set<LocationType> findAll() {
        return LocationTypeAPI.findAll();
    }
}
