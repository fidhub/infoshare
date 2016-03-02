package infoshare.restapi.ContentType;

import infoshare.app.conf.RestUtil;
import infoshare.domain.content.ContentType;

import java.util.Set;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContentTypeAPI {

    public static ContentType save(ContentType contentType){
        return RestUtil.save(ContentTypeBaseUrl.ContentType.POST, contentType, ContentType.class);
    }
    public static ContentType findById(String id){
        return RestUtil.getById(ContentTypeBaseUrl.ContentType.GET,id,ContentType.class);
    }
    public static ContentType update(ContentType contentType){
        return RestUtil.update(ContentTypeBaseUrl.ContentType.PUT,contentType);
    }
    public static Set<ContentType> findAll(){
        return RestUtil.getAll(ContentTypeBaseUrl.ContentType.GET_ALL,ContentType.class);
    }
}
