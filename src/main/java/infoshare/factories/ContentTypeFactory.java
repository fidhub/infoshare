package infoshare.factories;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.content.ContentType;

import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class ContentTypeFactory {

    public static ContentType getContentType(Map<String,String> contentTypeVals){
        ContentType contentType = new ContentType.Builder(contentTypeVals.get("contentName"))
                .id(KeyGenerator.getEntityId())
                .description(contentTypeVals.get("description"))
                .build();
        return contentType;
    }
}
