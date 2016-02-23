package infoshare.factories;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.EditedContent;


import java.util.Date;
import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class EditedContentFacory {

    public static EditedContent getEditedContent(Map<String, String> editedVals, Date dateCreated){
        EditedContent editedContent = new EditedContent.Builder(editedVals.get("title"))
                .id(KeyGenerator.getEntityId())
                .content(editedVals.get("content"))
                .category(editedVals.get("category"))
                .dateCreated(dateCreated)
                .creator(editedVals.get("creator"))
                .contentType(editedVals.get("contentType"))
                .state(DomainState.ACTIVE.name())
                .status(editedVals.get("status"))
                .source(editedVals.get("source"))
                .build();
        return editedContent;
    }
}
