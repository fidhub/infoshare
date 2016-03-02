package infoshare.factories.storage;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.storage.FileResults;
import jdk.nashorn.internal.runtime.options.Option;

import java.util.Map;

/**
 * Created by user9 on 2016/03/02.
 */
public class FileResultsFactory {
    public static FileResults getFileResults(Map<String, String> fileResultsVals, Option<String> size){
        FileResults fileResults = new FileResults.Builder()
                .id(KeyGenerator.getEntityId())
                .url(fileResultsVals.get("url"))
                .size(size)
                .mime(fileResultsVals.get("mime"))
                .build();
        return fileResults;
    }
}
