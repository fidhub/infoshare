package infoshare.factories.content;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.content.EditedContent;

import java.util.Date;
import java.util.Map;

/**
 * Created by user9 on 2016/03/01.
 */
public class EditedContentFactory {

    public static EditedContent getEditedContent(Map<String,String> editedContentVals,Date date){
        EditedContent editedContent = new EditedContent.Builder()
                .id(KeyGenerator.getEntityId())
                .dateCreated(date)
                .source(editedContentVals.get("source"))
                .category(editedContentVals.get("category"))
                .title(editedContentVals.get("title"))
                .content(editedContentVals.get("content"))
                .contentType(editedContentVals.get("contentType"))
                .status(editedContentVals.get("status"))
                .state(editedContentVals.get("state"))
                .org(editedContentVals.get("org"))
                .build();
        return editedContent;
    }
}
