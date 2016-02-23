package infoshare.factories;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.Source;

import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class SourceFactory {
    public static Source getSource(Map<String,String> sourceVals){
        Source source = new Source.Builder(sourceVals.get("name"))
                .description(sourceVals.get("description"))
                .id(KeyGenerator.getEntityId())
                .build();
        return source;
    }
}
