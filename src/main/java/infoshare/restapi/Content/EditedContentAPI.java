package infoshare.restapi.Content;

import infoshare.app.conf.RestUtil;
import infoshare.domain.content.EditedContent;

import java.util.Set;

/**
 * Created by user9 on 2016/02/23.
 */
public class EditedContentAPI {

    public static EditedContent save(EditedContent editedContent){
        return RestUtil.save(ContentBaseUrl.Edited.POST,editedContent,EditedContent.class);
    }
    public static EditedContent findById(String org,String id){
        String org_id = org+"/"+id;
        return RestUtil.getById(ContentBaseUrl.Edited.GET,org_id,EditedContent.class);
    }
    public static EditedContent update(EditedContent editedContent){
        return RestUtil.update(ContentBaseUrl.Edited.PUT,editedContent);
    }
    public static Set<EditedContent> findAll(String org){
        return RestUtil.getAll(ContentBaseUrl.Edited.GET_ALL+org,EditedContent.class);
    }
    /*
      public static PublishedContent save(PublishedContent publishedContent){
        return RestUtil.save(ContentBaseUrl.Published.POST, publishedContent, PublishedContent.class);
    }
    public static PublishedContent findById(String org, String id){
        String org_id = org+"/"+id;
        return RestUtil.getById(ContentBaseUrl.Published.GET,org_id,PublishedContent.class);
    }
    public static PublishedContent update(PublishedContent publishedContent){
        return RestUtil.update(ContentBaseUrl.Published.PUT,publishedContent);
    }
    public static Set<PublishedContent> findAll(String org){
        return RestUtil.getAll(ContentBaseUrl.Published.GET_ALL+org,PublishedContent.class);
     */
}
