package infoshare.factories;

import infoshare.app.util.DomainState;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.EditedContent;
import infoshare.domain.RawContent;

import java.util.Date;
import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class RawContentFactory {

        public static RawContent getRawContent(Map<String, String> editedVals, Date dateCreated){
            RawContent rawContent = new RawContent.Builder(editedVals.get("title"))
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
            return rawContent;
        }
}
