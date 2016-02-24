package infoshare.restapi.Content;

import infoshare.app.conf.RestUtil;
import infoshare.domain.EditedContent;

import java.time.format.ResolverStyle;
import java.util.Set;

/**
 * Created by user9 on 2016/02/23.
 */
public class EditedContentAPI {

    public static EditedContent save(EditedContent editedContent){
        return RestUtil.save(ContentBaseUrl.Edited.POST,editedContent,EditedContent.class);
    }
    public static EditedContent findById(String id){
        return RestUtil.getById(ContentBaseUrl.Edited.GET,id,EditedContent.class);
    }
    public static EditedContent update(EditedContent editedContent){
        return RestUtil.update(ContentBaseUrl.Edited.PUT,editedContent);
    }
    public static Set<EditedContent> findAll(){
        return RestUtil.getAll(ContentBaseUrl.Edited.GET_ALL,EditedContent.class);
    }
}
