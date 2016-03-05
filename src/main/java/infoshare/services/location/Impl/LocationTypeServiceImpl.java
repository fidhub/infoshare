package infoshare.services.location.Impl;


import infoshare.domain.location.LocationType;
import infoshare.restapi.common.location.LocationTypeAPI;
import infoshare.services.location.LocationTypeService;

import java.util.Set;

/**
 * Created by garran on 2015/09/06.
 */
public class LocationTypeServiceImpl implements LocationTypeService {

    @Override
    public LocationType findById(String id) {
        return LocationTypeAPI.findById(id);
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
