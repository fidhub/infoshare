package infoshare.restapi.Content;

import infoshare.app.conf.RestUtil;
import infoshare.domain.content.PublishedContent;

import java.util.Set;

/**
 * Created by user9 on 2016/02/23.
 */
public class PublishedContentAPI {
    public static PublishedContent save(PublishedContent publishedContent){
        return RestUtil.save(ContentBaseUrl.Published.POST, publishedContent, PublishedContent.class);
    }
    public static PublishedContent findById(String id){
        return RestUtil.getById(ContentBaseUrl.Published.GET,id,PublishedContent.class);
    }
    public static PublishedContent update(PublishedContent publishedContent){
        return RestUtil.update(ContentBaseUrl.Published.PUT,publishedContent);
    }
    public static Set<PublishedContent> findAll(){
        return RestUtil.getAll(ContentBaseUrl.Published.GET_ALL,PublishedContent.class);
    }
}
