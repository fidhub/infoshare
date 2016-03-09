package infoshare.restapi.ContentFiles.content;

import infoshare.app.conf.RestUtil;
import infoshare.domain.content.RawContent;

import java.util.Set;

/**
 * Created by user9 on 2016/02/23.
 */
public class RawContentAPI {
    public static RawContent save(RawContent rawContent){
        return RestUtil.save(ContentBaseUrl.Raw.POST, rawContent, RawContent.class);
    }
    public static RawContent findById(String org, String id){

        return RestUtil.getById(ContentBaseUrl.Raw.GET,org+"/"+id,RawContent.class);
    }
    public static RawContent update(RawContent rawContent){
        return RestUtil.update(ContentBaseUrl.Raw.PUT,rawContent);
    }
    public static Set<RawContent> findAll(String org){
        return RestUtil.getAll(ContentBaseUrl.Raw.GET_ALL+org,RawContent.class);
    }
}
