package infoshare.services.location.Impl;


import infoshare.domain.organisation.Location;
import infoshare.restapi.common.location.LocationAPI;
import infoshare.services.location.LocationService;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by garran on 2015/09/06.
 */
public class LocationServiceImpl implements LocationService {

    @Override
    public Location findById(String id) {
        return  LocationAPI.findById(id);
    }

    @Override
    public Location save(Location entity) {
        return LocationAPI.save(entity);
    }

    @Override
    public Location update(Location entity) {
        return LocationAPI.save(entity);
    }

    @Override
    public void delete(Location entity) {
        LocationAPI.save(entity);

    }

    @Override
    public Set<Location> findAll() {
        return LocationAPI.findAll();
    }

    @Override
    public boolean hasChildren(String id) {
        final Predicate<Location> notNull = location -> location.getParentId() != null;
        final Predicate<Location> filtered = location -> location.getParentId().equalsIgnoreCase(id);
        return findAll().parallelStream().filter(notNull).filter(filtered).count() > 0;
    }

    @Override
    public long numberofChildred(String id) {
        final Predicate<Location> notNull = location -> location.getParentId() != null;
        final Predicate<Location> filtered = location -> location.getParentId().equalsIgnoreCase(id);
        return findAll().parallelStream().filter(notNull).filter(filtered).count();
    }
}
