package infoshare.restapi.Source;

import infoshare.app.conf.RestUtil;
import infoshare.domain.Role;
import infoshare.domain.Source;
import infoshare.restapi.Roles.RolesBaseUrl;

import java.util.Set;

/**
 * Created by user9 on 2016/02/23.
 */
public class SourceAPI {
    public static Source save(Source source){
        return RestUtil.save(SourceBaseUrl.Source.POST, source, Source.class);
    }
    public static Source findById(String id){
        return RestUtil.getById(SourceBaseUrl.Source.GET,id,Source.class);
    }
    public static Source update(Source source){
        return RestUtil.update(SourceBaseUrl.Source.PUT,source);
    }
    public static Set<Source> findAll(){
        return RestUtil.getAll(SourceBaseUrl.Source.GET_ALL,Source.class);
    }
}
