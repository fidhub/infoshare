package infoshare.factories.storage;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.storage.StorageUrl;

/**
 * Created by user9 on 2016/03/02.
 */
public class StorageUrlFactory {
    public static StorageUrl getStorageUrl(String name,String url)
    {
        StorageUrl storageUrl = new StorageUrl.Builder()
                .id(KeyGenerator.getEntityId())
                .name(name)
                .url(url)
                .build();
        return storageUrl;
    }
}
