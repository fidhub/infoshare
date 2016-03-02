package infoshare.factories.storage;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.storage.StorageUrl;

import java.util.Map;

/**
 * Created by user9 on 2016/03/02.
 */
public class StorageUrlFactory {
    public static StorageUrl getStorageUrl(Map<String,String> storageUrlVals)
    {
        StorageUrl storageUrl = new StorageUrl.Builder()
                .id(KeyGenerator.getEntityId())
                .name(storageUrlVals.get("name"))
                .url(storageUrlVals.get("url"))
                .build();
        return storageUrl;
    }
}
