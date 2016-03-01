package infoshare.factories.content;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.content.ContentType;

import java.util.Map;

/**
 * Created by user9 on 2016/03/01.
 */
public class ContentTypeFactory {

    public static ContentType getContentType(Map<String,String> contentTypeVals){
        ContentType contentType = new ContentType.Builder()
                .id(KeyGenerator.getEntityId())
                .name(contentTypeVals.get("name"))
                .description(contentTypeVals.get("description"))
                .build();
        return contentType;
    }
}
