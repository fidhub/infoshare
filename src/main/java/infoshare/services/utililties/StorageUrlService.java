package infoshare.services.utililties;



import infoshare.domain.StorageUrl;

import java.util.Set;

/**
 * Created by hashcode on 2016/01/06.
 */
public interface StorageUrlService {
    StorageUrl save(StorageUrl link);

    StorageUrl getById(String id);

    Set<StorageUrl> getAllLinks();
}
