package infoshare.restapi.Organisation;

import infoshare.app.conf.RestUtil;
import infoshare.domain.organisation.Location;

import java.util.Set;

/**
 * Created by user9 on 2016/03/03.
 */
public class LocationAPI {
    public static Location save(Location location){
        return RestUtil.save(OrganisationBaseUrl.Location.POST, location, Location.class);
    }
    public static Location findById(String org ,String id){
        return RestUtil.getById(OrganisationBaseUrl.Location.GET_ID,org+"/"+id,Location.class);
    }
    public static Set<Location> findAll(String org){
        return RestUtil.getAll(OrganisationBaseUrl.Location.GET_ALL+org,Location.class);
    }
}
