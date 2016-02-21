package infoshare.factories;


import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.StorageUrl;

/**
 * Created by hashcode on 2016/01/05.
 */
public class StorageUrlFactory {
    public static StorageUrl getStorageUrl(String name, String url) {
        StorageUrl storageUrl = new StorageUrl
                .Builder()
                .id(KeyGenerator.getEntityId())
                .name(name)
                .url(url)
                .build();
        return storageUrl;
    }
}
