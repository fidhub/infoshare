package infoshare.factories.content;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.content.Source;

import java.util.Map;

/**
 * Created by user9 on 2016/03/01.
 */
public class SourceFactory {

    public static Source getSource(Map<String,String> sourceVals){
        Source source = new Source.Builder()
                .id(KeyGenerator.getEntityId())
                .org(sourceVals.get("org"))
                .name(sourceVals.get("name"))
                .description(sourceVals.get("description"))
                .build();
        return source;
    }
}
