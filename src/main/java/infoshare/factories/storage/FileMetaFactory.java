package infoshare.factories.storage;

import infoshare.domain.storage.FileMeta;

import java.util.Map;

/**
 * Created by user9 on 2016/03/02.
 */
public class FileMetaFactory {

    public static FileMeta getFileMeta(Map<String,String> fileMetaVals){
        FileMeta fileMeta = new FileMeta.Builder()
                .fileName(fileMetaVals.get("fileName"))
                .contentType(fileMetaVals.get("contentType"))
                .build();
        return fileMeta;
    }
}
